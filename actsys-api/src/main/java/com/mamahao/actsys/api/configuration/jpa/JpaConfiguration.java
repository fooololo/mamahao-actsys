package com.mamahao.actsys.api.configuration.jpa;

import com.mamahao.actsys.api.configuration.datasource.DynamicDataSource;
import com.mamahao.actsys.api.configuration.jpa.properties.JpaApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huluohu on 2016/7/12.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@ConditionalOnBean(name = "dataSource")
@AutoConfigureAfter(value = {DynamicDataSource.class,JpaApplicationProperties.class})
@EnableJpaRepositories(
        basePackages = {"com.mamahao.actsys.api.dao.sql"},
        transactionManagerRef = "jpaTransactionManager")
@EnableTransactionManagement
public class JpaConfiguration {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JpaApplicationProperties jpaApplicationProperties;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateDdlAuto;
    @Value("${spring.jpa.show-sql}")
    private boolean showSql;
    @Value("${spring.jpa.openInView}")
    private boolean openInView;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Bean(name = "jpaApplicationProperties")
    @ConditionalOnMissingBean
    public JpaApplicationProperties jpaApplicationProperties(){
        return new JpaApplicationProperties();
    }

    @Bean(name = "entityManagerFactory")
    @ConditionalOnMissingBean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(jpaApplicationProperties.getEntityPackages().toArray(new String[]{}));

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        factory.setJpaPropertyMap(properties);
        return factory;
    }


    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
