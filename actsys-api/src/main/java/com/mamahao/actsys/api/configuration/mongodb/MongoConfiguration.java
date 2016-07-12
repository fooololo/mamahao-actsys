package com.mamahao.actsys.api.configuration.mongodb;

import com.mamahao.actsys.api.configuration.mongodb.properties.MongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.TaggableReadPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   14:10
 * Description    :
 */
@Configuration
@ConditionalOnProperty(name = "mongo.config.enabled",havingValue = "true")
public class MongoConfiguration {
    @Autowired
    private MongoProperties mongoProperties;
    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private MongoClientOptions options;

    @Bean(name = "mongoProperties")
    @ConditionalOnMissingBean
    public MongoProperties mongoProperties(){
        return new MongoProperties();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(name = "mongoClient")
    public MongoClient mongoClient() throws UnknownHostException {
        MongoClient mongoClient = mongoProperties.createMongoClient(this.options);
        return mongoClient;
    }
    @Bean
    @ConditionalOnMissingBean(name = "mongoClient4Read")
    public MongoClient mongoClient4Read() throws UnknownHostException {
        MongoClient mongoClient = mongoProperties.createMongoClient(this.options);
        mongoClient.setReadPreference(TaggableReadPreference.secondaryPreferred());
        return mongoClient;
    }


    @Bean
    @Primary
    @ConditionalOnMissingBean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(),mongoProperties.getDatabase());
        return mongoTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(name = "mongoTemplate4Read")
    public MongoTemplate mongoTemplate4Read() throws Exception {
        final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient4Read(),mongoProperties.getDatabase());
        return mongoTemplate;
    }
}
