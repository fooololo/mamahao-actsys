package com.mamahao.actsys.api.framework.cache;

import java.util.concurrent.TimeUnit;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   10:25
 * Description    :
 */
public interface CacheService{
    void setCache(String key,Object value);
    void setCache(String key, Object value, long expire, TimeUnit timeUnit);
    <T> T getCache(String key);
}
