package com.mamahao.actsys.api.service.test.impl;

import com.mamahao.actsys.api.mapper.TestMapper;
import com.mamahao.actsys.api.po.Test;
import com.mamahao.actsys.api.service.AbstractService;
import com.mamahao.actsys.api.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/8
 * Time           :   9:23
 * Description    :
 */
@Service
@CacheConfig(cacheNames = "test_cache")
public class TestServiceImpl extends AbstractService<Test,Long> implements TestService{
    @Autowired
    private TestMapper testMapper;
    @Override
    public BaseMapper getMapper() {
        return testMapper;
    }

    @Override
    @Cacheable(key = "'test_' + #id",unless = "#result == null")
    public Test findByPrimaryKey(Long id) {
        System.out.println("没走缓存");
        return super.findByPrimaryKey(id);
    }
}
