package com.mamahao.actsys.api.service;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.po.Demo;
import com.mamahao.actsys.api.service.demo.DemoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

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

//    @Autowired
//    private DataSourceProperties dataSourceProperties;
//    @Resource(name = "redisCacheTemplate")
//    private RedisTemplate redisCacheTemplate;
//    @Resource(name = "redisCacheTemplate")
//    private ValueOperations valueOperations;
//    @Resource(name = "redisCacheTemplate")
//    private HashOperations hashOperations;
//    @Resource(name = "redisCacheTemplate")
//    private ListOperations listOperations;
//    @Resource(name = "redisCacheTemplate")
//    private SetOperations setOperationsl;
//    @Resource(name = "redisCacheTemplate")
//    private ZSetOperations zSetOperations;
//
//    @Autowired
//    private CacheService cacheService;
//    @Resource(name = "persistRedisTemplate")
//    private RedisTemplate persistRedisTemplate;
//    @Resource(name = "persistRedisService")
//    private RedisService redisService;

    @Test
    public void testGetById(){
        Long id = 1L;
        Demo demo = demoService.findByPrimaryKey(id);
        System.out.println(demo);
    }



    @Test
    @Rollback(false)
    public void testSave(){
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("111");
        int i = demoService.save(demo);
        System.out.println(i);
    }

    @Test
    @Rollback(false)
    public void testDelete(){
        demoService.delete(4L);
    }
}
