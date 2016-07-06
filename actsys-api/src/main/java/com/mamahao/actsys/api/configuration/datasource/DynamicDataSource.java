package com.mamahao.actsys.api.configuration.datasource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mamahao.actsys.api.configuration.datasource.properties.DataSourceConfig;
import com.mamahao.actsys.api.configuration.datasource.properties.DataSourceConfigGroup;
import com.mamahao.actsys.api.configuration.datasource.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/6/29
 * Time           :   10:57
 * Description    :   动态数据源，根据绑定在线程中的数据源名称切换实际的数据源
 */
@Component("dataSource")
@Order(0)
public class DynamicDataSource extends AbstractRoutingDataSource{
    @Autowired
    private DataSourceProperties dataSourceProperties;
    @PostConstruct
    public void init(){
        if(dataSourceProperties == null
                || dataSourceProperties.getGroups() == null
                || dataSourceProperties.getGroups().size() == 0){
            throw new IllegalArgumentException("datasource configure at least one.");
        }
        initDataSources(dataSourceProperties.getGroups());
    }

    /**
     * 初始化数据源
     * @param groups
     */
    private void initDataSources(List<DataSourceConfigGroup> groups) {
        List<DataSourceWrapper> allDataSource = Lists.newArrayList();
        for (int i = 0; i < groups.size(); i++) {
            DataSourceConfigGroup dataSourceConfigGroup = groups.get(i);
            List<DataSourceWrapper> dataSourceWrappers = buildDataSourceGroup(dataSourceConfigGroup);
            allDataSource.addAll(dataSourceWrappers);
            if(i == 0){
                DataSourceWrapper wrapper = getLeaderMaster(dataSourceWrappers);
                setDefaultTargetDataSource(wrapper.getDataSource());
            }
        }
        Map<Object,Object> dataSourceMap = Maps.newHashMap();
        for (DataSourceWrapper w : allDataSource) {
            dataSourceMap.put(w.getName(),w.getDataSource());
            DynamicContextHolder.addDataSource(w.getName());
        }
        setTargetDataSources(dataSourceMap);
    }

    private DataSourceWrapper getLeaderMaster(List<DataSourceWrapper> dataSourceWrappers) {
        DataSourceWrapper wrapper = null;
        for (DataSourceWrapper w : dataSourceWrappers) {
            if (w.isMaster()) {
                wrapper = w;
                break;
            }
        }
        if(wrapper == null){
            wrapper = dataSourceWrappers.get(0);
        }
        return wrapper;
    }

    private List<DataSourceWrapper> buildDataSourceGroup(DataSourceConfigGroup dataSourceConfigGroup) {
        List<DataSourceWrapper> dataSourceWrappers = Lists.newArrayList();
        if(dataSourceConfigGroup.onlyOne() || !dataSourceConfigGroup.hasMaster()){
            DataSourceConfig dataSourceConfig = dataSourceConfigGroup.getItems().get(0);
            dataSourceConfig.setMaster(true);
        }
        for (DataSourceConfig conf : dataSourceConfigGroup.getItems()) {
            DataSourceWrapper wrapper = buildDataSource(dataSourceConfigGroup.getGroupName(),conf);
            if(wrapper != null){
                dataSourceWrappers.add(wrapper);
            }
        }
        return dataSourceWrappers;
    }

    private DataSourceWrapper buildDataSource(String groupName, DataSourceConfig conf) {
        DataSourceWrapper wrapper = null;
        try {
            Class<? extends DataSource> dataSourceType
                    = (Class<? extends DataSource>) Class.forName(conf.getType());
            DataSourceBuilder builder = DataSourceBuilder.create()
                    .driverClassName(conf.getDriverClassName())
                    .url(conf.getUrl())
                    .username(conf.getUsername())
                    .password(conf.getPassword())
                    .type(dataSourceType);
            DataSource ds =  builder.build();
            wrapper = new DataSourceWrapper();
            wrapper.setName(conf.getName());
            wrapper.setGroupName(groupName);
            wrapper.setMaster(conf.isMaster());
            wrapper.setDataSource(ds);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return wrapper;
    }


    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("当前数据源：" + DynamicContextHolder.getCurrentDataSource());
        return DynamicContextHolder.getCurrentDataSource();
    }
}
