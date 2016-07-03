package com.mamahao.actsys.api.configuration.datasource;

import com.google.common.collect.Maps;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/1
 * Time           :   15:35
 * Description    :
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar,EnvironmentAware {
    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;
    //如果配置文件没指定数据源类型，则使用此类型
    private static final Object DEFAULT_DATASOURCE_TYPE = "org.apache.tomcat.jdbc.pool.DataSource";
    //默认数据源
    private DataSource defaultDataSource;
    private Map<String,DataSource> targetDataSources = Maps.newHashMap();
    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initTargetDataSources(environment);
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object,Object> targetDataSource = Maps.newHashMap();
        targetDataSource.put("defaultDataSource",defaultDataSource);
        DynamicContextHolder.addDataSource("defaultDataSource");
        targetDataSource.putAll(targetDataSources);

        Iterator<String> iterator = targetDataSources.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            DynamicContextHolder.addDataSource(key);
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);

        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource",defaultDataSource);
        mpv.addPropertyValue("targetDataSources",targetDataSource);

        registry.registerBeanDefinition("dataSource",beanDefinition);
    }

    private void initTargetDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = buildDataSource(dsMap);
            targetDataSources.put(dsPrefix, ds);
            bindDataSource(ds, env);
        }
    }


    private void initDefaultDataSource(Environment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env,"spring.datasource");
        Map<String,Object> dsMap = Maps.newHashMap();
        dsMap.put("type",propertyResolver.getProperty("type"));
        dsMap.put("driver-class-name",propertyResolver.getProperty("driver-class-name"));
        dsMap.put("url",propertyResolver.getProperty("url"));
        dsMap.put("username",propertyResolver.getProperty("username"));
        dsMap.put("password",propertyResolver.getProperty("password"));

        defaultDataSource = buildDataSource(dsMap);
        bindDataSource(defaultDataSource,env);
    }

    private void bindDataSource(DataSource dataSource, Environment env) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        //bindDataSource.setValidator(new LocalValidatorFactory().run(this.applicationContext));
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if(dataSourcePropertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<>(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    private DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if(type == null){
                type = DEFAULT_DATASOURCE_TYPE;
            }
            Class<? extends DataSource> dataSourceType
                    = (Class<? extends DataSource>) Class.forName(String.valueOf(type));
            String driverClassName = String.valueOf(dsMap.get("driver-class-nam"));
            String url = String.valueOf(dsMap.get("url"));
            String username = String.valueOf(dsMap.get("username"));
            String password = String.valueOf(dsMap.get("password"));
            DataSourceBuilder builder = DataSourceBuilder.create()
                    .driverClassName(driverClassName)
                    .url(url)
                    .username(username)
                    .password(password)
                    .type(dataSourceType);
            return builder.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
