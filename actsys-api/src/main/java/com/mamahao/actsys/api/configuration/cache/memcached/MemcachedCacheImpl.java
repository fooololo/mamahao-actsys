package com.mamahao.actsys.api.configuration.cache.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * Created by huluohu on 2016/7/10.
 */
public class MemcachedCacheImpl implements Cache {
    private final String name;
    private final MemCache memCache;

    public MemcachedCacheImpl(String name, MemcachedClient memcachedClient, int expire) {
        this.name = name;
        this.memCache = new MemCache(name,expire,memcachedClient);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.memCache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object cacheValue = memCache.get(key.toString());
        ValueWrapper vw = (cacheValue != null) ? new SimpleValueWrapper(cacheValue):null;
        return vw;
    }

    @Override
    public <T> T get(Object key, Class<T> clazz) {
        Object cacheValue = memCache.get(key.toString());
        Object value = (cacheValue != null)?cacheValue : null;
        if(clazz != null && !clazz.isInstance(value)){
            throw new IllegalStateException("Cached value is not of required type [" + clazz.getName() + "]: " + value);
        }
        return (T)value;
    }

    @Override
    public void put(Object key, Object value) {
        memCache.set(key.toString(),value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        put(key,value);
        return new SimpleValueWrapper(value);
    }

    @Override
    public void evict(Object key) {
        memCache.delete(key.toString());
    }

    @Override
    public void clear() {
        memCache.clear();
    }
}
