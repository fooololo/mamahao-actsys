package com.mamahao.actsys.api.configuration.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/5
 * Time           :   21:01
 * Description    :     数据源配置
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {
    @Valid
    @Size(min = 1,message = "至少配置一个数据源")
    private List<DataSourceConfigGroup> groups;

    public List<DataSourceConfigGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<DataSourceConfigGroup> groups) {
        this.groups = groups;
    }
}
