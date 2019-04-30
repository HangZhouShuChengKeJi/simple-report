package com.orange.heart.config;

import com.orange.heart.dao.JdbcTemplateDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * 数据源配置示例
 *
 * @author 小天
 * @date 2019/1/30 10:09
 */
@PropertySource({"classpath:/datasource.properties"})
@Configuration
public class SpringDataSourceConfig {

    /**
     * 数据源
     *
     * @param driverClassName 数据库驱动类名称
     * @param url             连接 url
     * @param username        用户名
     * @param password        密码
     * @param initialSize     初始化数量
     * @param maxTotal        连接池最大数量
     * @param maxIdle         最大空闲数量
     * @param minIdle         最小空闲数量
     * @param maxWaitMills    从连接池获取一个连接时，最大的等待时间，单位：毫秒
     */
    @Bean(name = "mainDataSource")
    public DataSource mainDataSource(@Value("${datasource.main.driver-class-name}") String driverClassName,
                                     @Value("${datasource.main.url}") String url,
                                     @Value("${datasource.main.username}") String username,
                                     @Value("${datasource.main.password}") String password,
                                     @Value("${datasource.main.initialSize:0}") int initialSize,
                                     @Value("${datasource.main.maxTotal:8}") int maxTotal,
                                     @Value("${datasource.main.maxIdle:8}") int maxIdle,
                                     @Value("${datasource.main.minIdle:0}") int minIdle,
                                     @Value("${datasource.main.maxWaitMills:5000}") int maxWaitMills
    ) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWaitMillis(maxWaitMills);

        // 一个连接的最大存活时间，单位：毫秒
        dataSource.setMaxConnLifetimeMillis(-1);

        // 执行连接池回收的时间间隔，单位：毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);

        // 符合驱逐条件的对象在池中最小空闲时间，单位：毫秒
        dataSource.setMinEvictableIdleTimeMillis(30 * 60 * 1000);

        // 借出连接时检查
        dataSource.setTestOnBorrow(false);
        // 空闲时检查
        dataSource.setTestWhileIdle(true);
        // 连接创建后，检查有效性
        dataSource.setTestOnCreate(true);
        dataSource.setTestOnReturn(false);
        // 每次运行空闲对象逐出线程时要检查的对象数量
        dataSource.setNumTestsPerEvictionRun(10);

        return dataSource;
    }

    @Bean("mainJdbcTemplateDao")
    public JdbcTemplateDao jdbcTemplateDao(@Qualifier("mainDataSource") DataSource mainDataSource) {
        return new JdbcTemplateDao(new NamedParameterJdbcTemplate(mainDataSource));
    }
}
