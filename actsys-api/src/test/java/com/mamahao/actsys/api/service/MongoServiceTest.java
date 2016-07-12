package com.mamahao.actsys.api.service;

import com.mamahao.actsys.api.TestRunner;
import com.mamahao.actsys.api.framework.mongo.MongoService;
import com.mamahao.actsys.api.mongo.Sss;
import com.mamahao.actsys.api.po.SSS;
import com.mamahao.actsys.api.dao.mongo.MSSSDao;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private MSSSDao msssDao;

    @Test
    public void testFindById(){
        String id = "566fe2a66c988720f188569e";
        Sss sss = mongoService.findById(new ObjectId(id), Sss.class);
        System.out.println(sss);
    }

    @Test
    public void testFindByIdWithName(){
        String id = "5783b8114d39e69382620624";
        SSS sss = msssDao.findOne(id);
        System.out.println(sss);
    }

    @Test
    public void testSaveWithName(){
        SSS sss = new SSS();
        sss.setShopId(123L);
        sss.setShopName("测试店铺");
        sss.setGps(Arrays.asList(123.344D,23.3342D));
        SSS save = msssDao.save(sss);
        System.out.println(save);
    }

    @Test
    public void testFindPage(){
        Long shopId = 12312L;
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        PageRequest pageRequest = new PageRequest(1,10,sort);
        List<SSS> ssses = msssDao.findByShopId(shopId, pageRequest);
        System.out.println(ssses);
    }
}
