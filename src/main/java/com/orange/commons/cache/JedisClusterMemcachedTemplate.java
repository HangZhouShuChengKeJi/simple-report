package com.orange.commons.cache;

import net.spy.memcached.MemcachedClientIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * jedis cluster 重写 MemcachedTemplate
 *
 * @author 小天
 * @date 2018/12/17 15:35
 */
public class JedisClusterMemcachedTemplate extends MemcachedTemplate {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JedisCluster jedisCluster;

    /**
     * 默认缓存过期时间，单位：秒
     */
    private int defaultExpire;

    private String keyPrefix;


    public JedisClusterMemcachedTemplate(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    @Override
    public void setMemcachedClient(MemcachedClientIF memcachedClient) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setApp(String app) {
        setKeyPrefix(app);
    }

    @Override
    public void setExpir(int expir) {
        setDefaultExpire(expir);
    }

    public void setDefaultExpire(int defaultExpire) {
        super.setExpir(defaultExpire);
        this.defaultExpire = defaultExpire;
    }

    public void setKeyPrefix(String keyPrefix) {
        super.setApp(keyPrefix);
        this.keyPrefix = keyPrefix;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public int getDefaultExpire() {
        return defaultExpire;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    private byte[] getKey(String key) {
        if (keyPrefix == null) {
            return key.getBytes(StandardCharsets.UTF_8);
        }
        return (keyPrefix + key).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 反序列化
     */
    protected Object deserialize(byte[] valueBytes) {
        if (valueBytes == null) {
            return null;
        }
        return SerializationUtils.deserialize(valueBytes);
    }

    /**
     * 序列化
     */
    protected byte[] serialize(Object value) {
        return SerializationUtils.serialize(value);
    }

    @Override
    public Object get(String key) {
        try {
            return deserialize(jedisCluster.get(getKey(key)));
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("get 操作出现异常，key={}", key).getMessage(), e);
            return null;
        }
    }

    /**
     * 缓存KEY 和 value
     *
     * @param key   缓存的key
     * @param value 缓存的值
     *
     * @return 缓存结果
     */
    @Override
    public boolean set(String key, Object value) {
        try {
            String response = jedisCluster.setex(getKey(key), defaultExpire, serialize(value));
            return "OK".equals(response);
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("set 操作出现异常，key={},value={}", key, value).getMessage(), e);
            return false;
        }
    }

    /**
     * <pre>
     * 请使用 set(String key,Object o),每个业务配置一个模版，配置一个超时时间
     * </pre>
     *
     * @param key   缓存的KEY
     * @param expir 超时时间，单位：秒
     * @param value 缓存对象
     *
     * @return 缓存是否成功的Future对象
     */
    @Deprecated
    @Override
    public boolean set(String key, int expir, Object value) {
        try {
            String response = jedisCluster.setex(getKey(key), expir, serialize(value));
            return "OK".equals(response);
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("set 操作出现异常，key={},value={}", key, value).getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        try {
            return jedisCluster.del(getKey(key)) == 1;
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("delete 操作出现异常，key={}", key).getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean add(String key, int expir, Object value) {
        try {
            String response = jedisCluster.set(getKey(key), serialize(value), "nx".getBytes(), "ex".getBytes(), expir);
            return "OK".equals(response);
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("add 操作出现异常，key={},value={}", key, value).getMessage(), e);
            return false;
        }
    }

    /**
     * 自增长
     *
     * @param key      缓存的key
     * @param defValue 如果该key不存在，则默认初始值
     * @param inc      自增长步长
     *
     * @return 变化后的数值
     */
    @Override
    public long incr(String key, int defValue, int inc) {
        try {
            return jedisCluster.incrBy(getKey(key), inc);
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("incr 操作出现异常，key={},inc={}", key, inc).getMessage(), e);
            return -1;
        }
    }

    /**
     * 自减
     *
     * @param key      缓存的key
     * @param defValue 如果该key不存在，则默认初始值
     * @param decr     自减步长
     *
     * @return 变化后的数值
     */
    @Override
    public long decr(String key, int defValue, int decr) {
        try {
            return jedisCluster.decrBy(getKey(key), decr);
        } catch (JedisException e) {
            logger.error(MessageFormatter.format("decr 操作出现异常，key={},decr={}", key, decr).getMessage(), e);
            return -1;
        }
    }

    @Override
    public Future<Boolean> setAsync(String key, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<Boolean> setAsync(String key, int expir, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<Boolean> deleteAsync(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<Boolean> addAsync(String key, int expir, Object value) {
        throw new UnsupportedOperationException();
    }
}
