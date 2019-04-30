package com.orange.commons.cache;

import net.spy.memcached.MemcachedClientIF;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * 缓存模版方法
 *
 * @author wuyajun
 */
public class MemcachedTemplate {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MemcachedClientIF memcachedClient;

    /**
     * 应用APP
     */
    private String app;
    /**
     * 缓存超时时间
     */
    private int    expir;

    public void setMemcachedClient(MemcachedClientIF memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public void setExpir(int expir) {
        this.expir = expir;
    }

    private String getKey(String key) {
        return DigestUtils.md5Hex(app + key);
    }

    public Object get(String key) {
        return memcachedClient.get(getKey(key));
    }

    /**
     * 缓存KEY 和 value
     *
     * @param key 缓存的key
     * @param o   缓存的值
     *
     * @return 缓存结果
     */
    public boolean set(String key, Object o) {
        try {
            return memcachedClient.set(getKey(key), expir, o).get();
        } catch (Throwable e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * <pre>
     * 请使用 set(String key,Object o),每个业务配置一个模版，配置一个超时时间
     * </pre>
     *
     * @param key   缓存的KEY
     * @param expir 超时时间
     * @param o     缓存对象
     *
     * @return 缓存是否成功的Future对象
     */
    @Deprecated
    public boolean set(String key, int expir, Object o) {
        try {
            return memcachedClient.set(getKey(key), expir, o).get();
        } catch (Throwable e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 缓存KEY 和 value
     *
     * @param key 缓存的key
     * @param o   缓存的值
     *
     * @return 缓存结果
     */
    @Deprecated
    public Future<Boolean> setAsync(String key, Object o) {
        Future<Boolean> f = memcachedClient.set(getKey(key), expir, o);
        return f;
    }

    /**
     * <pre>
     * 请使用 set(String key,Object o),每个业务配置一个模版，配置一个超时时间
     * </pre>
     *
     * @param key   缓存的KEY
     * @param expir 超时时间
     * @param o     缓存对象
     *
     * @return 缓存是否成功的Future对象
     */
    @Deprecated
    public Future<Boolean> setAsync(String key, int expir, Object o) {
        Future<Boolean> f = memcachedClient.set(getKey(key), expir, o);
        return f;
    }

    @Deprecated
    public Future<Boolean> deleteAsync(String key) {
        return memcachedClient.delete(getKey(key));
    }

    public boolean delete(String key) {
        try {
            return memcachedClient.delete(getKey(key)).get();
        } catch (Throwable e) {
            logger.error("", e);
        }
        return false;
    }

    @Deprecated
    public Future<Boolean> addAsync(String key, int expir, Object value) {
        return memcachedClient.add(getKey(key), expir, value);
    }

    public boolean add(String key, int expir, Object value) {
        try {
            return memcachedClient.add(getKey(key), expir, value).get();
        } catch (Throwable e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * memcached 自增长
     *
     * @param key      缓存的key
     * @param defValue 如果该key不存在，则默认初始值
     * @param inc      自增长步长
     *
     * @return 变化后的数值
     */
    public long incr(String key, int defValue, int inc) {
        return this.memcachedClient.incr(key, inc, defValue, expir);
    }

    /**
     * memcached 自减
     *
     * @param key      缓存的key
     * @param defValue 如果该key不存在，则默认初始值
     * @param decr     自减步长
     *
     * @return 变化后的数值
     */
    public long decr(String key, int defValue, int decr) {
        return this.memcachedClient.decr(key, decr, expir);
    }
}
