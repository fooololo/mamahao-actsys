package com.mamahao.actsys.api.configuration.cache;

import com.google.common.collect.Lists;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.CompositeCacheManager;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   13:49
 * Description    :   可以实现不同的缓存方式，暂时不用
 */
public class MultiCacheManager {
    public CacheManager cacheManager(){
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        cacheManager.setFallbackToNoOpCache(true);
        //设置其他的缓存管理器
        // 如：redisCacheManger、ehcacheManager、jcacheManager、memcachedManger等等
        cacheManager.setCacheManagers(Lists.<CacheManager>newArrayList());
        return cacheManager;
    }
}
