package com.mamahao.actsys.api.configuration.cache.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Created by huluohu on 2016/7/10.
 */
public class MemCache {
    private Set<String> keySet = new HashSet<String>();
    private final String name;
    private final int expire;
    private final MemcachedClient memcachedClient;

    public MemCache(String name, int expire, MemcachedClient memcachedClient) {
        this.name = name;
        this.expire = expire;
        this.memcachedClient = memcachedClient;
    }

    public Object get(String key){
        Object value = null;
        try {
            key = buildKey(key);
            value = memcachedClient.get(key);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void set(String key,Object value){
        if(value == null){
            return;
        }
        try {
            key = buildKey(key);
            memcachedClient.setWithNoReply(key,expire,value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public void delete(String key){
        try {
            key = buildKey(key);
            memcachedClient.deleteWithNoReply(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        for (String key: keySet) {
            try {
                memcachedClient.deleteWithNoReply(buildKey(key));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MemcachedException e) {
                e.printStackTrace();
            }
        }
    }

    private  String buildKey(String key){
        return String.format("%s_%s",name,key);
    }
}
