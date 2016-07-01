package com.mamahao.actsys.api.configuration.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/1
 * Time           :   11:56
 * Description    :
 */
@Component
@Aspect
@Order(-1)
public class DynamicDataSourceAspect {
    @Before("@annotation(tds)")
    public void changeDataSource(JoinPoint jp,TargetDataSource tds){
        if(tds != null){
            String name = tds.name();

        }
    }
    @After("@annotation(tds)")
    public void resetDataSource(JoinPoint jp,TargetDataSource tds){
        DynamicContextHolder.clearDataSourceKey();
    }
}
