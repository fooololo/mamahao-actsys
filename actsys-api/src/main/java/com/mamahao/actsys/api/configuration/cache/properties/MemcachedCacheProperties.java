package com.mamahao.actsys.api.configuration.cache.properties;

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
    private String addrs;
    private String defaultCacheName;
    private List<String> cacheNames;
    private int sessionIdleTimeout;
    private String username;
    private String password;

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }

    public String getDefaultCacheName() {
        return defaultCacheName;
    }

    public void setDefaultCacheName(String defaultCacheName) {
        this.defaultCacheName = defaultCacheName;
    }

    public List<String> getCacheNames() {
        return cacheNames;
    }

    public void setCacheNames(List<String> cacheNames) {
        this.cacheNames = cacheNames;
    }

    public int getSessionIdleTimeout() {
        return sessionIdleTimeout;
    }

    public void setSessionIdleTimeout(int sessionIdleTimeout) {
        this.sessionIdleTimeout = sessionIdleTimeout;
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
