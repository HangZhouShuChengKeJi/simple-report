package com.orange.commons.support.redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * jedis 连接池工具类
 *
 * @author 小天
 * @date 2018/12/13 14:49
 */
public class JedisPoolTemplate extends AbstractJedisTemplate implements Closeable {

    private JedisPool jedisPool;

    public JedisPoolTemplate(JedisPool jedisPool) {
        super(null, 5 * 60);
        this.jedisPool = jedisPool;
    }

    public JedisPoolTemplate(JedisPool jedisPool, String keyPrefix, int defaultExpire) {
        super(keyPrefix, defaultExpire);
        this.jedisPool = jedisPool;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    @Override
    public JedisCommands getJedisCommands(String key) {
        return jedisPool.getResource();
    }

    @Override
    public void close() throws IOException {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    @Override
    public boolean del(String key, String oldValue) {
        if (oldValue == null) {
            throw new IllegalArgumentException("oldValue can not be null");
        }
        Jedis jedis = getJedis();
        String realKey = wrapKey(key);
        jedis.watch(realKey);

        String actualOldValue = jedis.get(realKey);
        if (actualOldValue == null) {
            return true;
        }
        if (!StringUtils.equals(oldValue, actualOldValue)) {
            return false;
        }

        Transaction multi = jedis.multi();
        multi.del(realKey);
        List<Object> res = multi.exec();
        if (res == null) {
            return false;
        }
        return (Integer) res.get(0) == 1;
    }

    @Override
    public boolean replace(String key, String oldValue, String value) {
        return replace(key, oldValue, value, getDefaultExpire());
    }

    @Override
    public boolean replace(String key, String oldValue, String value, int expire) {
        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }
        if (oldValue == null) {
            throw new IllegalArgumentException("oldValue can not be null");
        }
        Jedis jedis = getJedis();
        String realKey = wrapKey(key);
        jedis.watch(realKey);

        String actualOldValue = jedis.get(realKey);

        if (!StringUtils.equals(oldValue, actualOldValue)) {
            jedis.unwatch();
            return false;
        }

        Transaction multi = jedis.multi();
        multi.set(realKey, value, SET_OPTION_XX, SET_OPTION_EX, expire);
        List<Object> res = multi.exec();
        if (res == null) {
            return false;
        }
        return REPLY_STRING_OK.equals(res.get(0));
    }
}
