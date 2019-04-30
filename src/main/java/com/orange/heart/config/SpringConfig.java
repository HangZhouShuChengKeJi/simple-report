package com.orange.heart.config;

import com.orange.commons.cache.HessianJedisClusterMemcachedTemplate;
import com.orange.commons.cache.MemcachedTemplate;
import com.orange.commons.support.redis.JedisClusterFactoryBean;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * spring 基本配置文件
 *
 * @author 小天
 * @date 2019/2/15 15:47
*/
@Configuration
public class SpringConfig {

    @Bean
    public Scheduler scheduler() {
        return new SchedulerFactoryBean().getObject();
    }

    @Primary
    @Bean("defaultJedisCluster")
    public JedisCluster jedisCluster(@Value("${redis.main.server}") String address,
                                     @Value("${redis.main.connectionTimeout}") int connectionTimeout,
                                     @Value("${redis.main.socketTimeout}") int socketTimeout,
                                     @Value("${redis.main.maxTotal}") int maxTotal,
                                     @Value("${redis.main.minIdle}") int minIdle,
                                     @Value("${redis.main.maxIdle}") int maxIdle,
                                     @Value("${redis.main.maxWaitMillis}") int maxWaitMillis) throws Exception {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(20);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(18000000);

        jedisPoolConfig.setTestOnCreate(true);
        jedisPoolConfig.setTestWhileIdle(true);

        return new JedisClusterFactoryBean(address, connectionTimeout, socketTimeout, jedisPoolConfig).getObject();
    }

    @Bean("defaultRedisTemplate")
    public MemcachedTemplate redisTemplate(JedisCluster jedisCluster) {
        HessianJedisClusterMemcachedTemplate memcachedTemplate = new HessianJedisClusterMemcachedTemplate(jedisCluster);
        memcachedTemplate.setKeyPrefix("heart_");
        memcachedTemplate.setDefaultExpire(180000);
        return memcachedTemplate;
    }
}
