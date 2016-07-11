package com.mamahao.actsys.api.configuration.jpa;

import com.mamahao.actsys.api.configuration.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by huluohu on 2016/7/12.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@ConditionalOnBean(name = "dataSource")
@ConditionalOnProperty(name = "spring.data.jpa.repositories.enabled",havingValue = "true")
@AutoConfigureAfter(DynamicDataSource.class)
@EnableJpaRepositories(basePackages = "com.mamahao.actsys.api.dao.db",
        transactionManagerRef = "jpaTransactionManager")
@EnableTransactionManagement
public class JpaConfiguration {
    @Resource(name = "dataSource")
    private DataSource dataSource;
    @Autowired
    private JpaProperties jpaProperties;
    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        EntityManager entityManager = entityManagerFactory(builder).getObject().createEntityManager();
        return entityManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = builder
                .dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                .packages(new String[]{"com.mamahao.actsys.api.po"}) //设置实体类所在位置
                .persistenceUnit("datasource")
                .build();
        return managerFactoryBean;
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }


    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());
        return transactionManager;
    }
}
