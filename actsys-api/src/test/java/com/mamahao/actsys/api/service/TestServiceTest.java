package com.mamahao.actsys.api.service;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.service.test.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/8
 * Time           :   9:37
 * Description    :
 */
public class TestServiceTest extends TestRunner {
    @Autowired
    private TestService testService;

    @Test
    public void testFindById(){
        com.mamahao.actsys.api.po.Test t = testService.findByPrimaryKey(1L);
        System.out.println(t);
    }
}
