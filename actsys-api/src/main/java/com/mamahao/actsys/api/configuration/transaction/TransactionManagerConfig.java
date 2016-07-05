package com.mamahao.actsys.api.configuration.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   15:54
 * Description    :
 */
@Configuration
public class TransactionManagerConfig {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
