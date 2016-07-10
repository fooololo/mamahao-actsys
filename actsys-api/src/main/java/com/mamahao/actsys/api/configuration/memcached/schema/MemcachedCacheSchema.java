package com.mamahao.actsys.api.configuration.memcached.schema;

/**
 * Created by huluohu on 2016/7/10.
 */
public class MemcachedCacheSchema {
    private String name;
    private int expire = 1800;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
