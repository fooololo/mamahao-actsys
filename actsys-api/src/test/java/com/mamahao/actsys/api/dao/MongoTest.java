package com.mamahao.actsys.api.dao;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.configuration.mongodb.MongoConfiguration;
import com.mamahao.actsys.api.dao.mongo.SSSRepository;
import com.mamahao.actsys.api.po.SSS;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/12
 * Time           :   11:54
 * Description    :
 */
@ContextConfiguration(classes = {MongoConfiguration.class})
public class MongoTest extends TestRunner {
    @Autowired
    private SSSRepository sssRepository;

    @Test
    public void testFindOne(){
        String id = "5783b8114d39e69382620624";
        SSS sss = sssRepository.findOne(id);
        System.out.println(sss);
    }
}
