package com.orange.commons.support.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 小天
 * @date 2018/12/17 16:32
 */
public class JedisClusterFactoryBean implements FactoryBean<JedisCluster> {

    private Set<HostAndPort>        hostAndPortSet;
    private int                     connectionTimeout;
    private int                     socketTimeout;
    /**
     * 最大尝试连接次数，最小为 1
     */
    private int                     maxAttempts;
    private GenericObjectPoolConfig poolConfig;

    public JedisClusterFactoryBean(String address, int connectionTimeout, int socketTimeout, final GenericObjectPoolConfig poolConfig) {
        this(address, connectionTimeout, socketTimeout, 3, poolConfig);
    }

    /**
     * 构建
     *
     * @param address           redis 集群地址。格式：host1:port1,host2:port2,host3:port3,...
     * @param connectionTimeout 集群连接超时时间
     * @param socketTimeout     socket 超时时间
     * @param maxAttempts       最大尝试连接次数，最小为 1
     * @param poolConfig        连接池配置
     */
    public JedisClusterFactoryBean(String address, int connectionTimeout, int socketTimeout, int maxAttempts, final GenericObjectPoolConfig poolConfig) {
        if (StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("address can not be blank");
        }
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("maxAttempts must greater or equal to 0");
        }
        hostAndPortSet = new HashSet<>();
        String[] arr = StringUtils.split(address, ",");
        for (String s : arr) {
            String[] t = StringUtils.split(s, ":");
            hostAndPortSet.add(new HostAndPort(t[0], Integer.parseInt(t[1])));
        }
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
        this.maxAttempts = maxAttempts;
        this.poolConfig = poolConfig;
    }

    @Override
    public JedisCluster getObject() throws Exception {
        return new JedisCluster(hostAndPortSet, connectionTimeout, socketTimeout, maxAttempts, poolConfig);
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCluster.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
