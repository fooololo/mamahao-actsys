package com.mamahao.actsys.api.service.demo.impl;

import com.mamahao.actsys.api.configuration.datasource.TargetDataSource;
import com.mamahao.actsys.api.service.demo.AbstractService;
import com.mamahao.actsys.api.service.demo.DemoService;
import com.mamahao.actsys.api.mapper.DemoMapper;
import com.mamahao.actsys.api.po.Demo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @TargetDataSource(name = "ds1")
    public Demo findByPrimaryKey(Long id) {
        return super.findByPrimaryKey(id);
    }

    @TargetDataSource(name = "ds2")
    public int save(Demo record) {
        return super.save(record);
    }

    @Override
    public int saveOne(Demo demo) {
        return demoMapper.insert(demo);
    }

    @Override
    public void test() {
        System.out.println("nothing to do.");
    }
}
