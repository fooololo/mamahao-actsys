package com.mamahao.actsys.api.service.demo.impl;

import com.mamahao.actsys.api.configuration.datasource.annotation.TargetDataSource;
import com.mamahao.actsys.api.mapper.DemoMapper;
import com.mamahao.actsys.api.po.Demo;
import com.mamahao.actsys.api.service.AbstractService;
import com.mamahao.actsys.api.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
@CacheConfig(cacheNames = {"demo_cache"})
public class DemoServiceImpl extends AbstractService<Demo,Long> implements DemoService {
    @Autowired
    private DemoMapper demoMapper;


    @Override
    public BaseMapper getMapper() {
        return demoMapper;
    }

    @TargetDataSource(name = "ms1_ds1")
    @Cacheable(key = "'demo_' + #id", unless = "#result == null")//unless=true 表示不更新缓存
    public Demo findByPrimaryKey(Long id) {
        System.out.println("没有走缓存...");
        return super.findByPrimaryKey(id);
    }

    @TargetDataSource(name = "ms0_ds1")
    public int save(Demo demo) {
        return super.save(demo);
    }

    @Override
//    @CacheEvict(key = "#root.targetClass.name + '_findByPrimaryKey_' + #demo.id")
    @CacheEvict(key = "'demo_' + #demo.id")
    public Demo update(Demo demo) {
        super.updateByPrimaryKey(demo);
        return demo;
    }

    @Override
    @CacheEvict(key = "'demo_' + #id")
    public void delete(Long id) {
        super.deleteByPrimaryKey(id);
    }

    @TargetDataSource(name = "ms2_ds0")
    @Override
    public void test() {
        System.out.println("nothing to do.");
    }
}
