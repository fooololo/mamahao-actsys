package com.mamahao.actsys.api;

import com.mamahao.actsys.api.configuration.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/6/28
 * Time           :   18:06
 * Description    :
 */
@SpringCloudApplication
@Import(DynamicDataSourceRegister.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
