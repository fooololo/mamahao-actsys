package com.mamahao.actsys.api.configuration.cache.properties;

import com.mamahao.actsys.api.configuration.redis.schema.RedisPoolSchema;
import com.mamahao.actsys.api.configuration.redis.schema.RedisSentinelSchema;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/6
 * Time           :   14:11
 * Description    :
 */
@RefreshScope
@ConfigurationProperties(prefix = "cache.redis")
public class RedisCacheProperties  extends CacheProperties{
    private int database = 0;
    private String host;
    private int port;
    private String password;
    private int timeout = 5;
    private int expireSeconds = 1800;
    private RedisPoolSchema pool;
    private RedisSentinelSchema sentinel;

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public RedisPoolSchema getPool() {
        return pool;
    }

    public void setPool(RedisPoolSchema pool) {
        this.pool = pool;
    }

    public RedisSentinelSchema getSentinel() {
        return sentinel;
    }

    public void setSentinel(RedisSentinelSchema sentinel) {
        this.sentinel = sentinel;
    }
}
