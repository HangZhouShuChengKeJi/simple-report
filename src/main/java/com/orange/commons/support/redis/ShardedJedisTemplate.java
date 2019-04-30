package com.orange.commons.support.redis;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * jedis 连接 redis 集群工具类（分片模式）
 *
 * @author 小天
 * @date 2018/12/14 11:09
 */
public class ShardedJedisTemplate extends AbstractJedisTemplate implements Closeable {

    private ShardedJedisPool shardedJedisPool;

    public ShardedJedisTemplate(ShardedJedisPool shardedJedisPool) {
        super(null, 5 * 60);
        this.shardedJedisPool = shardedJedisPool;
    }

    public ShardedJedisTemplate(ShardedJedisPool shardedJedisPool, String keyPrefix, int defaultExpire) {
        super(keyPrefix, defaultExpire);
        this.shardedJedisPool = shardedJedisPool;
    }

    public ShardedJedis getShardedJedis() {
        return shardedJedisPool.getResource();
    }

    @Override
    public void close() throws IOException {
        if (shardedJedisPool != null) {
            shardedJedisPool.close();
        }
    }

    @Override
    public JedisCommands getJedisCommands(String key) {
        return shardedJedisPool.getResource();
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
