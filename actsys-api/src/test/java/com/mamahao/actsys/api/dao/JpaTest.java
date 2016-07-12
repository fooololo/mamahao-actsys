package com.mamahao.actsys.api.dao;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.configuration.jpa.JpaConfiguration;
import com.mamahao.actsys.api.dao.repository.DemoRepository;
import com.mamahao.actsys.api.po.Demo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/12
 * Time           :   10:38
 * Description    :
 */
@ContextConfiguration(classes = {JpaConfiguration.class})
public class JpaTest extends TestRunner {
    @Autowired
    private DemoRepository demoRepository;


    @Test
    public void testFind(){
        Demo demo = demoRepository.findOne(1L);
        System.out.println(demo);
    }
}
