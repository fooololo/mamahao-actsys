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
    public static List<String> dataSourceNames = Lists.newArrayList();
    public static void setCurrentDataSource(String name){
        holder.set(name);
    }

    public static String getCurrentDataSource(){
       return holder.get();
    }
    public static void clearCurrentDataSource(){
        holder.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceNames.contains(dataSourceId);
    }
    public static void addDataSource(String dataSourceName){
        dataSourceNames.add(dataSourceName);
    }
}
