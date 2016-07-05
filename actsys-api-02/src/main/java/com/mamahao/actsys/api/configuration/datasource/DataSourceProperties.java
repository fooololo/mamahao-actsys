package com.mamahao.actsys.api.configuration.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/5
 * Time           :   21:01
 * Description    :
 */
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {
    private DataSourceConfig master;
    private List<DataSourceConfig> slaves;

    public DataSourceConfig getMaster() {
        return master;
    }

    public void setMaster(DataSourceConfig master) {
        this.master = master;
    }

    public List<DataSourceConfig> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<DataSourceConfig> slaves) {
        this.slaves = slaves;
    }
}
