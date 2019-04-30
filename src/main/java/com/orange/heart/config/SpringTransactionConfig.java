package com.orange.heart.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;

/**
 * 事物配置
 *
 * @author 小天
 * @date 2019/1/25 17:54
 */
@Configuration
public class SpringTransactionConfig {

    /**
     * 配置数据源事物管理器
     *
     * @param dataSource 数据源
     */
    @Bean("mainDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("mainDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 事物拦截器
     *
     * @param transactionManager 事物管理器
     */
    @Bean(name = "mainTransactionInterceptor")
    public TransactionInterceptor transactionInterceptor(@Qualifier("mainDataSourceTransactionManager") PlatformTransactionManager transactionManager) {
        // 事物属性
        DefaultTransactionAttribute requiredAttribute = new DefaultTransactionAttribute();
        requiredAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 事物属性 readonly
        DefaultTransactionAttribute readonlyAndRequiredAttribute = new DefaultTransactionAttribute();
        readonlyAndRequiredAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        readonlyAndRequiredAttribute.setReadOnly(true);

        NameMatchTransactionAttributeSource attributeSource = new NameMatchTransactionAttributeSource();
        attributeSource.addTransactionalMethod("add*", requiredAttribute);
        attributeSource.addTransactionalMethod("save*", requiredAttribute);
        attributeSource.addTransactionalMethod("update*", requiredAttribute);
        attributeSource.addTransactionalMethod("del*", requiredAttribute);
        attributeSource.addTransactionalMethod("do*", requiredAttribute);
        attributeSource.addTransactionalMethod("insert*", requiredAttribute);
        attributeSource.addTransactionalMethod("*", readonlyAndRequiredAttribute);

        return new TransactionInterceptor(transactionManager, attributeSource);
    }

    /**
     * 配置 aop
     *
     * @param transactionInterceptor 事物拦截器
     */
    @Bean(name = "mainTransactionAdvisor")
    public Advisor txAdviceAdvisor(@Qualifier("mainTransactionInterceptor") TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.orange.heart.service..*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }
}
