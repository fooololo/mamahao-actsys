package com.mamahao.actsys.api.configuration.datasource;

import javax.sql.DataSource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/6
 * Time           :   10:36
 * Description    :
 */
public class DataSourceWrapper {
    private String name;
    private String groupName;
    private boolean master;
    private DataSource dataSource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
