package com.mamahao.actsys.api.framework.cache.impl;

import com.mamahao.actsys.api.framework.cache.CacheService;
import com.mamahao.actsys.api.framework.redis.AbstractRedisService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   10:25
 * Description    :
 */
@Component
@ConditionalOnBean(name = {"redisCacheTemplate"})
public class RedisCacheService extends AbstractRedisService implements CacheService {
    @Resource(name = "redisCacheTemplate")
    private RedisTemplate redisTemplate;
    @Override
    protected RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public void setCache(String key, Object value) {
        set(key,value);
    }

    @Override
    public void setCache(String key, Object value, int expire, TimeUnit timeUnit) {
        set(key,value,expire,timeUnit);
    }

    @Override
    public <T> T getCache(String key) {
        return get(key);
    }

    @Override
    public void deleteCache(String key) {
        delete(key);
    }
}
