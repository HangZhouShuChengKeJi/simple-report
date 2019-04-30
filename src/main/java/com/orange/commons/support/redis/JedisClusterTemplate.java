package com.orange.commons.support.redis;

import com.google.gson.Gson;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.io.Closeable;
import java.io.IOException;

/**
 * jedis 连接 redis 集群工具类
 *
 * @author 小天
 * @date 2018/12/14 17:17
 */
public class JedisClusterTemplate extends AbstractJedisTemplate implements Closeable {

    private JedisCluster jedisCluster;
    private Gson         gson;

    public JedisClusterTemplate(String keyPrefix, JedisCluster jedisCluster) {
        super(keyPrefix);
        this.jedisCluster = jedisCluster;
    }

    public JedisClusterTemplate(String keyPrefix, int defaultExpire, JedisCluster jedisCluster) {
        super(keyPrefix, defaultExpire);
        this.jedisCluster = jedisCluster;
    }

    @Override
    public JedisCommands getJedisCommands(String key) {
        return jedisCluster;
    }


    @Override
    public void close() throws IOException {
        if (jedisCluster != null) {
            jedisCluster.close();
        }
    }

    @Override
    public boolean del(String key, String oldValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(String key, String oldValue, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(String key, String oldValue, String value, int expire) {
        throw new UnsupportedOperationException();
    }
}
