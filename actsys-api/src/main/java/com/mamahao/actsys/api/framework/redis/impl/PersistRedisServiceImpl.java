package com.mamahao.actsys.api.framework.redis.impl;

import com.mamahao.actsys.api.framework.redis.AbstractRedisService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   15:54
 * Description    :
 */
@Component(value = "persistRedisService")
@ConditionalOnBean(name = {"persistRedisTemplate"})
public class PersistRedisServiceImpl extends AbstractRedisService {
    @Resource(name = "persistRedisTemplate")
    private RedisTemplate redisTemplate;
    @Override
    protected RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
}
