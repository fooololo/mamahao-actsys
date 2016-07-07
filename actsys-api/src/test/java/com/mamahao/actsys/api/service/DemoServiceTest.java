package com.mamahao.actsys.api.service;

import com.google.common.collect.Lists;
import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.configuration.datasource.properties.DataSourceConfigGroup;
import com.mamahao.actsys.api.configuration.datasource.properties.DataSourceProperties;
import com.mamahao.actsys.api.framework.cache.CacheService;
import com.mamahao.actsys.api.framework.redis.RedisService;
import com.mamahao.actsys.api.po.Demo;
import com.mamahao.actsys.api.service.demo.DemoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   14:43
 * Description    :
 */
@SuppressWarnings("ALL")
public class DemoServiceTest extends TestRunner {
    @Autowired
    private DemoService demoService;
    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Resource(name = "redisCacheTemplate")
    private RedisTemplate redisCacheTemplate;
    @Resource(name = "redisCacheTemplate")
    private ValueOperations valueOperations;
    @Resource(name = "redisCacheTemplate")
    private HashOperations hashOperations;
    @Resource(name = "redisCacheTemplate")
    private ListOperations listOperations;
    @Resource(name = "redisCacheTemplate")
    private SetOperations setOperationsl;
    @Resource(name = "redisCacheTemplate")
    private ZSetOperations zSetOperations;

    @Autowired
    private CacheService cacheService;
    @Resource(name = "persistRedisTemplate")
    private RedisTemplate persistRedisTemplate;
    @Resource(name = "persistRedisService")
    private RedisService redisService;

    @Test
    public void testGetById(){
        Long id = 1L;
        Demo demo = demoService.findByPrimaryKey(id);
        System.out.println(demo.getName());
//        Object s = redisCacheTemplate.execute(new RedisCallback() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                byte[] bytes = connection.get("com.mamahao.actsys.api.service.demo.impl.DemoServiceImpl#findByPrimaryKey#1".getBytes());
//                return redisCacheTemplate.getValueSerializer().deserialize(bytes);
//            }
//        });
//        Demo d = cacheService.get("com.mamahao.actsys.api.service.demo.impl.DemoServiceImpl#findByPrimaryKey#2");
        List<Demo> demos = Lists.newArrayList();
        demos.add(new Demo(1L,"111"));
        demos.add(new Demo(2L,"222"));
        demos.add(new Demo(3L,"333"));
        String key = "test_demos";
//        cacheService.set(key,demos);
        List<Demo> ds1 = cacheService.getCache(key);
        System.out.println("result:" + ds1);
        List<Demo> ds2 = redisService.get(key);
        System.out.println("result:" + ds2);
    }

    @Test
    @Rollback(true)
    public void testSave(){
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("sss");
        int i = demoService.save(demo);
        System.out.println(i);
    }

    @Test
    public void testNoArgs() {
        demoService.test();
        List<DataSourceConfigGroup> groups = dataSourceProperties.getGroups();
        for (DataSourceConfigGroup dsc : groups) {
            System.out.println(dsc.getGroupName());
        }
    }
}
