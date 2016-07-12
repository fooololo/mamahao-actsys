package com.mamahao.actsys.api.configuration.jpa.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/12
 * Time           :   14:16
 * Description    :
 */
@ConfigurationProperties(prefix = "spring.data.jpa")
@ConditionalOnProperty(name = "spring.data.jpa.repositories.enabled",havingValue = "true")
public class JpaApplicationProperties {

    private List<String> basePackages;
    private List<String> entityPackages;

    public List<String> getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(List<String> basePackages) {
        this.basePackages = basePackages;
    }

    public List<String> getEntityPackages() {
        return entityPackages;
    }

    public void setEntityPackages(List<String> entityPackages) {
        this.entityPackages = entityPackages;
    }
}
