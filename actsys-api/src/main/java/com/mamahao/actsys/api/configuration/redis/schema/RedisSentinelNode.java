package com.mamahao.actsys.api.configuration.redis.schema;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   9:36
 * Description    :
 */
public class RedisSentinelNode {
    private String name;
    private String host;
    private int port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
