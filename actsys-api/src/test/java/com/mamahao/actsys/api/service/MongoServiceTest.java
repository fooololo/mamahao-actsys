package com.mamahao.actsys.api.service;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.framework.mongo.MongoService;
import com.mamahao.actsys.api.mongo.Sss;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   19:20
 * Description    :
 */
public class MongoServiceTest extends TestRunner {
    @Autowired
    private MongoService mongoService;

    @Test
    public void testFindById(){
        String id = "566fe2a66c988720f188569e";
        Sss sss = mongoService.findById(new ObjectId(id), Sss.class);
        System.out.println(sss);
    }
}
