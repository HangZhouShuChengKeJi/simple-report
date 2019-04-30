package com.orange.commons.support.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCommands;

import java.lang.reflect.Type;

/**
 * @author 小天
 * @date 2018/12/13 14:49
 */
public abstract class AbstractJedisTemplate implements RedisTemplate {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 默认缓存过期时间
     */
    private int defaultExpire = 2 * 60;

    private String keyPrefix;

    private Gson gson;

    AbstractJedisTemplate(String keyPrefix) {
        this(keyPrefix, null);
    }

    AbstractJedisTemplate(String keyPrefix, Integer defaultExpire) {
        if (defaultExpire != null) {
            if (defaultExpire <= 0) {
                throw new IllegalArgumentException("defaultExpire can not less than 0");
            }
            this.defaultExpire = defaultExpire;
        }

        this.keyPrefix = keyPrefix;
        GsonBuilder builder = new GsonBuilder();
//        builder.setDateFormat("yyyyMMddHHmmssSSS");
        this.gson = builder.create();
    }

    public int getDefaultExpire() {
        return defaultExpire;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public abstract JedisCommands getJedisCommands(String key);

    String wrapKey(String key) {
        if (keyPrefix == null) {
            return key;
        }
        return keyPrefix + key;
    }

    @Override
    public boolean exist(String key) {
        return getJedisCommands(key).exists(wrapKey(key));
    }

    @Override
    public boolean del(String key) {
        return getJedisCommands(key).del(wrapKey(key)) == 1;
    }

    @Override
    public boolean expire(String key, int expire) {
        return getJedisCommands(key).expire(wrapKey(key), expire) == REPLY_INTEGER_1;
    }

    @Override
    public boolean touch(String key) {
        return getJedisCommands(key).touch(wrapKey(key)) == 1;
    }

    @Override
    public long ttl(String key) {
        return getJedisCommands(key).ttl(wrapKey(key));
    }

    @Override
    public boolean set(String key, String value) {
        return REPLY_STRING_OK.equals(getJedisCommands(key).set(wrapKey(key), value, SET_OPTION_EX, getDefaultExpire()));
    }

    @Override
    public boolean set(String key, String value, int expire) {
        return REPLY_STRING_OK.equals(getJedisCommands(key).set(wrapKey(key), value, SET_OPTION_EX, expire));
    }

    @Override
    public boolean setIfAbsent(String key, String value) {
        return REPLY_STRING_OK.equals(getJedisCommands(key).set(wrapKey(key), value, SET_OPTION_NX, SET_OPTION_EX, getDefaultExpire()));
    }

    @Override
    public boolean setIfAbsent(String key, String value, int expire) {
        return REPLY_STRING_OK.equals(getJedisCommands(key).set(wrapKey(key), value, SET_OPTION_NX, SET_OPTION_EX, expire));
    }

    @Override
    public String getAndSet(String key, String value) {
        return getJedisCommands(key).getSet(wrapKey(key), value);
    }

    @Override
    public String get(String key) {
        return getJedisCommands(key).get(wrapKey(key));
    }

    @Override
    public long incr(String key) {
        return getJedisCommands(key).incr(wrapKey(key));
    }

    @Override
    public long decr(String key) {
        return getJedisCommands(key).decr(wrapKey(key));
    }

    @Override
    public long incrBy(String key, int increment) {
        return getJedisCommands(key).incrBy(wrapKey(key), increment);
    }

    @Override
    public long decrBy(String key, int decrement) {
        return getJedisCommands(key).decrBy(wrapKey(key), decrement);
    }

    /**
     * 设置一个对象到redis 里，该对应会作为json字符串保存进去
     * 与{@link #getObject(String, Type)} 方法对应使用
     *
     * @param key    键
     * @param object json字符串
     *
     * @return 是否设置成功
     */
    @Override
    public boolean setObject(String key, Object object) {
        return set(key, gson.toJson(object));
    }

    /**
     * @param key  键
     * @param type 反序列化类型
     *
     * @return 缓存中的对象
     */
    @Override
    public <T> T getObject(String key, Type type) {
        String json = get(key);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            logger.warn("JSON 反序列化异常：" + json, e);
            return null;
        }
    }
}
