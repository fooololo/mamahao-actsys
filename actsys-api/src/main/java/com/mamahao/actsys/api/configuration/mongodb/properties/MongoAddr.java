package com.mamahao.actsys.api.configuration.mongodb.properties;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   14:38
 * Description    :
 */
public class MongoAddr {
    private String host;
    private int port;

    public MongoAddr() {
    }

    public MongoAddr(String host, int port) {
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
