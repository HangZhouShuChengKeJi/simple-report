package com.orange.commons.support.redis;

import java.lang.reflect.Type;

/**
 * @author 小天
 * @date 2018/12/13 14:21
 */
public interface RedisTemplate {

    /**
     * 设置 key 的过期时间，单位：秒
     */
    String SET_OPTION_EX = "ex";
    /**
     * 设置 key 的过期时间，单位：毫秒
     */
    String SET_OPTION_PX = "px";
    /**
     * 只有 key 不存在时，才会设置key的值
     */
    String SET_OPTION_NX = "nx";
    /**
     * 只有 key 存在时，才会设置key的值
     */
    String SET_OPTION_XX = "xx";

    String REPLY_STRING_OK = "OK";

    int REPLY_INTEGER_0 = 0;
    int REPLY_INTEGER_1 = 1;

    /**
     * 指定的 key 是否存在
     *
     * @param key key
     *
     * @return true： 存在
     */
    boolean exist(String key);

    /**
     * 删除指定的 key
     *
     * @param key key
     *
     * @return true: 删除成功
     */
    boolean del(String key);

    /**
     * 删除指定的 key
     *
     * @param key      key
     * @param oldValue 旧值
     *
     * @return true: 删除成功
     */
    boolean del(String key, String oldValue);

    /**
     * 设置指定的 key 的有效时间
     *
     * @param key    key
     * @param expire 过期时间，单位：秒
     *
     * @return true: 设置成功
     */
    boolean expire(String key, int expire);

    /**
     * 刷新指定 key 的最后访问时间
     *
     * @param key key
     *
     * @return true: 设置成功
     */
    boolean touch(String key);

    /**
     * 获取指定的 key 的有效时间。<br>
     * 2.6 及之前版本，key 不存在或已过期时，返回 -1；<br>
     * 2.8 版本开始，key 不存在或已过期时，返回 -2；key 未设置过期时间（永久有效），返回 -1<br>
     *
     * @param key key
     *
     * @return 有效时间（单位：秒）。
     */
    long ttl(String key);

    /**
     * 设置字符串
     *
     * @param key   key
     * @param value value
     *
     * @return true： 设置成功
     */
    boolean set(String key, String value);

    /**
     * 设置字符串
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间，单位：秒
     *
     * @return true： 设置成功
     */
    boolean set(String key, String value, int expire);

    /**
     * 当指定的 key 不存在时，设置值
     *
     * @param key   key
     * @param value value
     *
     * @return true： 设置成功
     */
    boolean setIfAbsent(String key, String value);

    /**
     * 当指定的key 不存在时，设置值
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间，单位：秒
     *
     * @return true： 设置成功
     */
    boolean setIfAbsent(String key, String value, int expire);


    /**
     * 替换指定 key 的值
     *
     * @param key      key
     * @param oldValue 旧值
     * @param value    新值
     *
     * @return true：成功
     */
    boolean replace(String key, String oldValue, String value);

    /**
     * 替换指定 key 的值
     *
     * @param key      key
     * @param oldValue 旧值
     * @param value    新值
     * @param expire   过期时间，单位：秒
     *
     * @return true：成功
     */
    boolean replace(String key, String oldValue, String value, int expire);

    /**
     * 设置新的值，并返回旧值
     *
     * @param key   key
     * @param value 新值
     *
     * @return 旧值
     */
    String getAndSet(String key, String value);

    /**
     * 获取 {@link String} 类型的值
     *
     * @param key key
     *
     * @return 缓存值
     */
    String get(String key);

    /**
     * 原子自增
     *
     * @param key key
     *
     * @return 变化后的数值
     */
    long incr(String key);

    /**
     * 原子自减
     *
     * @param key key
     *
     * @return 变化后的数值
     */
    long decr(String key);

    /**
     * 原子自增
     *
     * @param key       key
     * @param increment 增量
     *
     * @return 变化后的数值
     */
    long incrBy(String key, int increment);

    /**
     * 原子自减
     *
     * @param key       key
     * @param decrement 减量
     *
     * @return 变化后的数值
     */
    long decrBy(String key, int decrement);

    /**
     * 设置一个对象到redis 里，该对应会作为json字符串保存进去
     * 与{@link #getObject(String, Type)} 方法对应使用
     *
     * @param key    键
     * @param object json字符串
     *
     * @return 是否设置成功
     */
    boolean setObject(String key, Object object);

    /**
     * @param key  键
     * @param type 反序列化类型
     *
     * @return 缓存中的对象
     */
    <T> T getObject(String key, Type type);

}
