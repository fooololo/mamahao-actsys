package com.mamahao.actsys.api.mapper;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.po.Demo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   16:13
 * Description    :
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class DemoMapperTest extends TestRunner {
    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void testSave(){
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("sss");
        int id = demoMapper.insert(demo);
        System.out.println(id);
    }
}
