package com.mamahao.actsys.api.configuration.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamahao.actsys.api.configuration.redis.properties.PersistRedisProperties;
import com.mamahao.actsys.api.configuration.redis.schema.RedisSentinelNode;
import com.mamahao.actsys.api.configuration.redis.schema.RedisSentinelSchema;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   15:46
 * Description    :
 */
@Configuration
public class PersistRedisConfiguration {
    @Bean(name = "persistRedisProperties")
    @ConditionalOnMissingBean
    public PersistRedisProperties persistRedisProperties(){
        return new PersistRedisProperties();
    }
    @Autowired
    private PersistRedisProperties persistRedisProperties;

    @Bean(name = "persistRedisConnectionFactory")
    public JedisConnectionFactory persistRedisConnectionFactory(){
        JedisConnectionFactory factory;
        if(persistRedisProperties.getSentinel() != null
                && StringUtils.isNotBlank(persistRedisProperties.getSentinel().getMaster())){
            RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
            RedisSentinelSchema sentinel = persistRedisProperties.getSentinel();
            sentinelConfiguration.setMaster(sentinel.getMaster());
            List<RedisSentinelNode> nodes = sentinel.getNodes();
            for (RedisSentinelNode node : nodes) {
                sentinelConfiguration.addSentinel(new RedisNode(node.getHost(), node.getPort()));
            }
            factory = new JedisConnectionFactory(sentinelConfiguration);
        }else{
            factory = new JedisConnectionFactory();
            factory.setHostName(persistRedisProperties.getHost());
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(persistRedisProperties.getPool().getMaxIdle());
        poolConfig.setMaxWaitMillis(persistRedisProperties.getPool().getMaxWaitMillis());
        poolConfig.setMinIdle(persistRedisProperties.getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestOnReturn(true);
        factory.setPoolConfig(poolConfig);

        factory.setDatabase(persistRedisProperties.getDatabase());
        factory.setTimeout(persistRedisProperties.getTimeout());
        if(StringUtils.isNotBlank(persistRedisProperties.getPassword())){
            factory.setPassword(persistRedisProperties.getPassword());
        }
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean(name = "persistRedisTemplate")
    public StringRedisTemplate redisTemplate(){
        StringRedisTemplate template = new StringRedisTemplate(persistRedisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setEnableDefaultSerializer(false);
        template.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.afterPropertiesSet();
        return template;
    }
}
