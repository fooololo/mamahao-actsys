package com.mamahao.actsys.api.configuration.datasource;

import java.util.List;

/**
 * Created by huluohu on 2016/7/5.
 */
public class DataSourceConfigGroup {
    private String groupName;
    private List<DataSourceConfig> items;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<DataSourceConfig> getItems() {
        return items;
    }

    public void setItems(List<DataSourceConfig> items) {
        this.items = items;
    }
}
