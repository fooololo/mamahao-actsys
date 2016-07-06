package com.mamahao.actsys.api.service;

import com.mamahao.actsys.api.po.Demo;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   14:39
 * Description    :
 */
public interface DemoService extends BaseService<Demo,Long>{
    int saveOne(Demo demo);
    void test();
}
