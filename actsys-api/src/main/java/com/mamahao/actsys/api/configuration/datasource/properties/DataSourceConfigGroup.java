package com.mamahao.actsys.api.configuration.datasource.properties;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by huluohu on 2016/7/5.
 */
public class DataSourceConfigGroup {
    @NotBlank(message = "数据源组不能为空")
    private String groupName;
    @Valid
    @Size(min = 1,message = "数据源组中至少配置一个数据源")
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
    public boolean hasMaster(){
        boolean flag = false;
        if(items != null && items.size() > 0){
            for (DataSourceConfig conf : items) {
                if (conf.isMaster()) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public boolean onlyOne(){
        if(items != null && items.size() == 1){
            return true;
        }
        return false;
    }
    public DataSourceConfig getMaster(){
        if(onlyOne()){
            return items.get(0);
        }
        for (DataSourceConfig conf : items) {
            if(conf.isMaster()){
                return conf;
            }
        }
        return items.get(0);
    }
}
