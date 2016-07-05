package com.mamahao.actsys.api.configuration.datasource;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/6/29
 * Time           :   14:35
 * Description    :
 */
public class DynamicContextHolder{
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = Lists.newArrayList();
    public static void setDataSourceKey(String key){
        holder.set(key);
    }
    public static void setDefaultDataSource(){
        holder.set("defaultDataSource");
    }
    public static String getDataSourceKey(){
       return holder.get();
    }
    public static void clearDataSourceKey(){
        holder.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
    public static void addDataSource(String dataSourceId){
        dataSourceIds.add(dataSourceId);
    }
}
