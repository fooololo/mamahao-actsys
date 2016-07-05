package com.mamahao.actsys.api.configuration.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/5
 * Time           :   21:01
 * Description    :
 */
@ConfigurationProperties(prefix = "datasources")
@Component
public class DataSourceProperties {
    private List<DataSourceConfigGroup> groups;

    public List<DataSourceConfigGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<DataSourceConfigGroup> groups) {
        this.groups = groups;
    }
}
