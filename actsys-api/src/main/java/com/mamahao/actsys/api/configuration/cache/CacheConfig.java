package com.mamahao.actsys.api.configuration.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamahao.actsys.api.configuration.cache.properties.RedisCacheProperties;
import com.mamahao.actsys.api.configuration.redis.schema.RedisSentinelNode;
import com.mamahao.actsys.api.configuration.redis.schema.RedisSentinelSchema;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/6
 * Time           :   14:10
 * Description    :     缓存框架配置
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {
    @Bean(name = "redisCacheProperties")
    @ConditionalOnMissingBean
    public RedisCacheProperties redisCacheProperties(){
        return new RedisCacheProperties();
    }
    @Autowired
    private RedisCacheProperties redisCacheProperties;

    @Bean(name = "redisCacheFactory")
    @Primary
    public JedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory factory;
        if(redisCacheProperties.getSentinel() != null
                && StringUtils.isNotBlank(redisCacheProperties.getSentinel().getMaster())){
            RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
            RedisSentinelSchema sentinel = redisCacheProperties.getSentinel();
            sentinelConfiguration.setMaster(sentinel.getMaster());
            List<RedisSentinelNode> nodes = sentinel.getNodes();
            for (RedisSentinelNode node : nodes) {
                sentinelConfiguration.addSentinel(new RedisNode(node.getHost(), node.getPort()));
            }
            factory = new JedisConnectionFactory(sentinelConfiguration);
        }else{
            factory = new JedisConnectionFactory();
            factory.setHostName(redisCacheProperties.getHost());
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisCacheProperties.getPool().getMaxIdle());
        poolConfig.setMaxWaitMillis(redisCacheProperties.getPool().getMaxWaitMillis());
        poolConfig.setMinIdle(redisCacheProperties.getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestOnReturn(true);
        factory.setPoolConfig(poolConfig);

        factory.setDatabase(redisCacheProperties.getDatabase());
        factory.setTimeout(redisCacheProperties.getTimeout());
        if(StringUtils.isNotBlank(redisCacheProperties.getPassword())){
            factory.setPassword(redisCacheProperties.getPassword());
        }
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
        cacheManager.setDefaultExpiration(redisCacheProperties.getExpire());
        return cacheManager;
    }

    @Bean(name = "redisCacheTemplate")
    public StringRedisTemplate redisTemplate(){
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory());
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

    /**
     * 自定义key生成器
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("#").append(method.getName());
                for (Object obj : params) {
                    sb.append("#").append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
