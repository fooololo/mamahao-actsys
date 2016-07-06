package com.mamahao.actsys.api.service.demo.impl;

import com.mamahao.actsys.api.configuration.datasource.annotation.TargetDataSource;
import com.mamahao.actsys.api.mapper.DemoMapper;
import com.mamahao.actsys.api.po.Demo;
import com.mamahao.actsys.api.service.AbstractService;
import com.mamahao.actsys.api.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   14:39
 * Description    :
 */
@Service
public class DemoServiceImpl extends AbstractService<Demo,Long> implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public BaseMapper getMapper() {
        return demoMapper;
    }

    @TargetDataSource(name = "ms1_ds1")
    @Cacheable(value = "demo_cache",keyGenerator = "wiselyKeyGenerator")
    public Demo findByPrimaryKey(Long id) {
        System.out.println("没有走缓存...");
        return super.findByPrimaryKey(id);
    }

    @TargetDataSource(name = "ms0_ds1")
    public int save(Demo record) {
        return super.save(record);
    }

    @Override
    public int saveOne(Demo demo) {
        return demoMapper.insert(demo);
    }

    @TargetDataSource(name = "ms2_ds0")
    @Override
    public void test() {
        System.out.println("nothing to do.");
    }
}
