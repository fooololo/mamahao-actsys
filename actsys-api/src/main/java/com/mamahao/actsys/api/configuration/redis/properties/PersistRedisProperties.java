package com.mamahao.actsys.api.configuration.redis.properties;

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
@ConfigurationProperties(prefix = "redis.persist")
public class PersistRedisProperties {
    private int database = 0;
    private String host;
    private int port;
    private String password;
    private int timeout = 5;
    private int expire = 30 * 24 * 60 * 60;
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

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
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
