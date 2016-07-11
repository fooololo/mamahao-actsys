package com.mamahao.actsys.api.framework.cache.impl;

import com.mamahao.actsys.api.configuration.cache.properties.MemcachedCacheProperties;
import com.mamahao.actsys.api.framework.cache.CacheService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   9:41
 * Description    :
 */
@ConditionalOnBean(name = "memcachedClient")
@Component
public class MemcachedCacheService implements CacheService {
    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private MemcachedCacheProperties memcachedCacheProperties;

    @Override
    public void setCache(String key, Object value) {
        try {
            memcachedClient.set(key,memcachedCacheProperties.getDefaultExpireSeconds(),value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCache(String key, Object value, int expire, TimeUnit timeUnit) {
        try {
            memcachedClient.set(key,expire,value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getCache(String key) {
        try {
            T t = memcachedClient.get(key);
            return t;
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCache(String key) {
        try {
            memcachedClient.delete(key);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }
}
