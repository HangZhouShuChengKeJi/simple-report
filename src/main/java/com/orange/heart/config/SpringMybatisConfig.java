package com.orange.heart.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis 配置
 *
 * @author 小天
 * @date 2019/1/30 11:02
 */
@Configuration
public class SpringMybatisConfig {

    @DependsOn({"mainDataSource"})
    @Bean(name = "mainSqlSessionFactory")
    public SqlSessionFactory mainSqlSessionFactoryBean(@Qualifier("mainDataSource") DataSource mainDataSource, ResourcePatternResolver resourcePatternResolver) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("sqlMapConfig.xml"));
        sqlSessionFactoryBean.setDataSource(mainDataSource);

        // 这里应当返回 FactoryBean 的结果，而不应该返回 FactoryBean 本身，否则就是一个大坑
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mainMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.orange.heart.dao");
        return mapperScannerConfigurer;
    }

}
