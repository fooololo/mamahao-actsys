package com.mamahao.actsys.api;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/6/28
 * Time           :   18:06
 * Description    :
 */
@SpringCloudApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
