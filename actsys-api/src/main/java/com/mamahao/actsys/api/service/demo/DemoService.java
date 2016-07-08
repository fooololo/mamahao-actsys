package com.mamahao.actsys.api.service.demo;

import com.mamahao.actsys.api.po.Demo;
import com.mamahao.actsys.api.service.BaseService;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   14:39
 * Description    :
 */
public interface DemoService extends BaseService<Demo,Long> {
    void test();
    Demo update(Demo demo);
    void delete(Long id);
}
