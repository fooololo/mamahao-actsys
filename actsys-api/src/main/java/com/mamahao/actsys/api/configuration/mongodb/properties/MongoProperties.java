package com.mamahao.actsys.api.configuration.mongodb.properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   14:11
 * Description    :
 */
@RefreshScope
@ConfigurationProperties(prefix = "mongo.config")
@ConditionalOnProperty(name = "mongo.config.enable",havingValue = "true")
public class MongoProperties {
    public static final int DEFAULT_PORT = 27017;
    private boolean enable = false;
    private String database;
    private String username;
    private char[] password;
    private int connectionPerHost = 100;
    private int commectionTimeout = 30000;
    private int maxWaitTime = 30000;
    private List<MongoAddr> addrs;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

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

    public MongoClient createMongoClient(MongoClientOptions options){
        MongoClient client = null;
        boolean needAuth = false;
        if(options == null){
            options = MongoClientOptions.builder().build();
        }
        if(StringUtils.isNotBlank(username) && password != null){
            needAuth = true;
        }
        List<ServerAddress> adresses = new ArrayList<>();
        List<MongoCredential> mongoCredentials = new ArrayList<>();
        for (MongoAddr addr : addrs) {
            try {
                ServerAddress adress = new ServerAddress(addr.getHost(),addr.getPort());
                adresses.add(adress);
//                if(needAuth){
//                    MongoCredential credential = MongoCredential.createCredential(username,null,password);
//                    mongoCredentials.add(credential);
//                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        client = new MongoClient(adresses,mongoCredentials,options);
        return client;
    }
}
