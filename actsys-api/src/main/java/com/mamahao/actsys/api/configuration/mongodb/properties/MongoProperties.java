package com.mamahao.actsys.api.configuration.mongodb.properties;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   14:11
 * Description    :
 */
public class MongoProperties {
    public static final int DEFAULT_PORT = 27017;
    private boolean enable = false;
    private String username;
    private char[] password;
    private int connectionPerHost = 100;
    private int commectionTimeout = 30000;
    private int maxWaitTime = 30000;
    private List<MongoAddr> addrs;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public int getConnectionPerHost() {
        return connectionPerHost;
    }

    public void setConnectionPerHost(int connectionPerHost) {
        this.connectionPerHost = connectionPerHost;
    }

    public int getCommectionTimeout() {
        return commectionTimeout;
    }

    public void setCommectionTimeout(int commectionTimeout) {
        this.commectionTimeout = commectionTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public List<MongoAddr> getAddrs() {
        return addrs;
    }

    public void setAddrs(List<MongoAddr> addrs) {
        this.addrs = addrs;
    }
}
