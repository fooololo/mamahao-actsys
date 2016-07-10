package com.mamahao.actsys.api.configuration.memcached.schema;

/**
 * Created by huluohu on 2016/7/10.
 */
public class MemcachedAddr {
    private String host;
    private int port;

    public MemcachedAddr() {
    }

    public MemcachedAddr(String host, int port) {
        this.host = host;
        this.port = port;
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
}
