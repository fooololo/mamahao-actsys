package com.mamahao.actsys.api.configuration.memcached.schema;

/**
 * Created by huluohu on 2016/7/10.
 */
public class MemcachedCacheSchema {
    private String name;
    private int expireSeconds = 1800;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
