package com.mamahao.actsys.api.configuration.mongodb;

import com.mamahao.actsys.api.configuration.mongodb.properties.MongoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   14:10
 * Description    :
 */
@Configuration
@ConditionalOnProperty(name = "mongo.config.enable",havingValue = "true")
public class MongoConfiguration {
    @Bean(name = "mongoProperties")
    @ConditionalOnMissingBean
    public MongoProperties mongoProperties(){
        return new MongoProperties();
    }


    @PreDestroy
    public void destroy(){

    }
}
