package com.mamahao.actsys.api.configuration.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/6/29
 * Time           :   10:57
 * Description    :
 */
public class DynamicDataSource extends AbstractRoutingDataSource implements InitializingBean{
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicContextHolder.getDataSourceKey();
    }
}
