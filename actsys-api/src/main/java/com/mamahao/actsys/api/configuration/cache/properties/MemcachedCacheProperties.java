package com.mamahao.actsys.api.configuration.cache.properties;

import com.mamahao.actsys.api.configuration.memcached.schema.MemcachedAddr;
import com.mamahao.actsys.api.configuration.memcached.schema.MemcachedCacheSchema;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/8
 * Time           :   10:12
 * Description    :
 */
@RefreshScope
@ConfigurationProperties(prefix = "cache.memcached")
public class MemcachedCacheProperties extends CacheProperties{
    private List<MemcachedAddr> addrs;
    private String defaultCacheName;
    private List<MemcachedCacheSchema> caches;
    private int sessionIdleTimeout;
    private int defaultExpireSeconds = 1800;
    private String username;
    private String password;

    public List<MemcachedAddr> getAddrs() {
        return addrs;
    }

    public void setAddrs(List<MemcachedAddr> addrs) {
        this.addrs = addrs;
    }

    public List<MemcachedCacheSchema> getCaches() {
        return caches;
    }

    public void setCaches(List<MemcachedCacheSchema> caches) {
        this.caches = caches;
    }

    public String getDefaultCacheName() {
        return defaultCacheName;
    }

    public void setDefaultCacheName(String defaultCacheName) {
        this.defaultCacheName = defaultCacheName;
    }

    public int getSessionIdleTimeout() {
        return sessionIdleTimeout;
    }

    public void setSessionIdleTimeout(int sessionIdleTimeout) {
        this.sessionIdleTimeout = sessionIdleTimeout;
    }

    public int getDefaultExpireSeconds() {
        return defaultExpireSeconds;
    }

    public void setDefaultExpireSeconds(int defaultExpireSeconds) {
        this.defaultExpireSeconds = defaultExpireSeconds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
