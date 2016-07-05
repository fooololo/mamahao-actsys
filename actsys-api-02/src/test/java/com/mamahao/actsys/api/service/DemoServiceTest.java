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


    @Test
    public void testGetById(){
        Long id = 1L;
        demoService.findByPrimaryKey(id);
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
    }
}
