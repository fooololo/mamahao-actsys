package com.mamahao.actsys.api.configuration.cache;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.providers.xmemcached.XMemcachedConfiguration;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;
import com.mamahao.actsys.api.configuration.cache.properties.MemcachedCacheProperties;
import com.mamahao.actsys.api.configuration.memcached.schema.MemcachedAddr;
import net.rubyeye.xmemcached.auth.AuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/8
 * Time           :   10:07
 * Description    :
 */
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnProperty(name = "cache.memcached.enabled.aaa",havingValue = "true")
@ImportResource("classpath:simplesm-context.xml")
public class MemcachedCacheConfig extends CachingConfigurerSupport{
    @Bean(name = "memcachedCacheProperties")
    @ConditionalOnMissingBean
    public MemcachedCacheProperties memcachedCacheProperties(){
        return new MemcachedCacheProperties();
    }
    @Autowired
    private MemcachedCacheProperties memcachedCacheProperties;


    @Bean
    @Override
    public CacheManager cacheManager() {
        Set<SSMCache> ssmCacheSet = new HashSet();
        SSMCache ssmCache = new SSMCache(defaultCache(), 300, false);
        ssmCacheSet.add(ssmCache);
        SSMCacheManager ssmCacheManager = new SSMCacheManager();
        ssmCacheManager.setCaches(ssmCacheSet);
        return ssmCacheManager;
    }

    @Bean
    @DependsOn("cacheBase")
    public CacheFactory cacheFactory() {
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheName(memcachedCacheProperties.getDefaultCacheName());
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());

        MemcachedAddr addr = memcachedCacheProperties.getAddrs().get(0);
        String servers = addr.getHost() + "" + addr.getPort();
        XMemcachedConfiguration cacheConfiguration = createCacheConfiguration(servers);
        cacheConfiguration.setConsistentHashing(true);
        cacheFactory.setAddressProvider(new DefaultAddressProvider(servers));
        cacheFactory.setConfiguration(cacheConfiguration);
        return cacheFactory;
    }

    @Bean
    public Cache defaultCache() {
        try {
            return cacheFactory().getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private XMemcachedConfiguration createCacheConfiguration(final String servers) {
        XMemcachedConfiguration cacheConfiguration = new XMemcachedConfiguration();
        cacheConfiguration.setConsistentHashing(true);
        cacheConfiguration.setUseBinaryProtocol(true);
        String username = memcachedCacheProperties.getUsername();
        String password = memcachedCacheProperties.getPassword();
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            Map<InetSocketAddress, AuthInfo> authInfoMap = new HashMap();
            authInfoMap.put(getInetSocketAddress(servers), AuthInfo.plain(username, password));
            cacheConfiguration.setAuthInfoMap(authInfoMap);
        }

        return cacheConfiguration;
    }

    private InetSocketAddress getInetSocketAddress(final String server) {
        String[] split = server.split("\\.");
        String hostName = split[0];
        int port = Integer.parseInt(split[1]);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, port);
        return inetSocketAddress;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("_").append(method.getName());
                for (Object obj : params) {
                    sb.append("_").append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
