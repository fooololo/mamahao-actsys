package com.mamahao.actsys.api.configuration.cache.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by huluohu on 2016/7/10.
 */
public class MemcacheCacheManger extends AbstractTransactionSupportingCacheManager {
    private ConcurrentMap<String,Cache> caches = new ConcurrentHashMap<String, Cache>();
    private Map<String,Integer> cacheExpires = new HashMap<String, Integer>();
    private MemcachedClient memcachedClient;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        Collection<Cache> values = caches.values();
        return values;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = caches.get(name);
        if(cache == null){
            Integer expire = cacheExpires.get(name);
            if(expire == null){
                expire = 0;
                cacheExpires.put(name,expire);
            }
            cache = new MemcachedCacheImpl(name,memcachedClient,expire);
        }
        return cache;
    }
    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public void setCacheExpires(Map<String, Integer> cacheExpires) {
        this.cacheExpires = cacheExpires;
    }
}
