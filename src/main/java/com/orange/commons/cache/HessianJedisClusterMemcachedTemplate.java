package com.orange.commons.cache;

import com.orange.commons.utils.Hessian2SerializerUtils;
import redis.clients.jedis.JedisCluster;

/**
 * 使用 hessian2 协议序列化的 jedis 工具类
 *
 * @author 小天
 * @date 2019/2/13 9:31
 */
public class HessianJedisClusterMemcachedTemplate extends JedisClusterMemcachedTemplate {

    public HessianJedisClusterMemcachedTemplate(JedisCluster jedisCluster) {
        super(jedisCluster);
    }

    @Override
    protected Object deserialize(byte[] valueBytes) {
        if (valueBytes == null) {
            return null;
        }
        return Hessian2SerializerUtils.deserialize(valueBytes);
    }

    @Override
    protected byte[] serialize(Object value) {
        return Hessian2SerializerUtils.serialize(value);
    }
}
