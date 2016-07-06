package com.mamahao.actsys.api.configuration.datasource.annotation;

import java.lang.annotation.*;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/1
 * Time           :   15:10
 * Description    :
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TargetDataSource {
    String name();
}
