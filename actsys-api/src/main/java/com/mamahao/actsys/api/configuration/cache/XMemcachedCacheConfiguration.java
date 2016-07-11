package com.mamahao.actsys.api.configuration.cache;

import com.mamahao.actsys.api.configuration.cache.memcached.MemcacheCacheManger;
import com.mamahao.actsys.api.configuration.cache.properties.MemcachedCacheProperties;
import com.mamahao.actsys.api.configuration.memcached.schema.MemcachedAddr;
import com.mamahao.actsys.api.configuration.memcached.schema.MemcachedCacheSchema;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存框架配置（memcached），支持事务
 */
@Configuration
@ConditionalOnProperty(name = "cache.memcached.enabled",havingValue = "true")
//@ImportResource("classpath:simplesm-context.xml")
public class XMemcachedCacheConfiguration {
    @Bean(name = "memcachedCacheProperties")
    @ConditionalOnMissingBean
    public MemcachedCacheProperties memcachedCacheProperties(){
        return new MemcachedCacheProperties();
    }
    @Autowired
    private MemcachedCacheProperties memcachedCacheProperties;

    public MemcachedClientBuilder clientBuilder(){
        List<InetSocketAddress> addresses = buildInetSocketAddress();
        XMemcachedClientBuilder clientBuilder = new XMemcachedClientBuilder(addresses);
        clientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator());

        String username = memcachedCacheProperties.getUsername();
        String password = memcachedCacheProperties.getPassword();
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            Map<InetSocketAddress, AuthInfo> authInfoMap = new HashMap();
            for (InetSocketAddress address : addresses) {
                authInfoMap.put(address, AuthInfo.plain(username, password));
            }
            clientBuilder.setAuthInfoMap(authInfoMap);
        }
        clientBuilder.getConfiguration().setSessionIdleTimeout(memcachedCacheProperties.getSessionIdleTimeout());

        return clientBuilder;
    }

    @Bean(name = "memcachedClient")
    public MemcachedClient memcachedClient(){
        MemcachedClient client = null;
        try {
            client = clientBuilder().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Bean
    public CacheManager cacheManager(){
        MemcacheCacheManger cacheManger = new MemcacheCacheManger();
        cacheManger.setMemcachedClient(memcachedClient());
        List<MemcachedCacheSchema> caches = memcachedCacheProperties.getCaches();
        Map<String,Integer> cacheExpires = new HashMap<String, Integer>();
        for (MemcachedCacheSchema cache : caches) {
            cacheExpires.put(cache.getName(),cache.getExpireSeconds());
        }
        cacheManger.setCacheExpires(cacheExpires);
        return cacheManger;
    }

    private List<InetSocketAddress> buildInetSocketAddress() {
        List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>();
        List<MemcachedAddr> addrs = memcachedCacheProperties.getAddrs();
        for (MemcachedAddr addr : addrs) {
            InetSocketAddress address = new InetSocketAddress(addr.getHost(),addr.getPort());
            addresses.add(address);
        }
        return addresses;
    }

}
