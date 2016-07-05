package com.mamahao.actsys.api.configuration.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/1
 * Time           :   11:56
 * Description    :
 */
@Component
@Aspect
@Order(-1)
public class DynamicDataSourceAspect {

    @Pointcut(value = "execution(* com.mamahao.actsys.api.service..*.*(..))")
    public void serviceAopPoint(){}

    @Before(value = "serviceAopPoint()")
    public void changeDataSource(JoinPoint jp) throws NoSuchMethodException {
        Method method = ((MethodSignature)jp.getSignature()).getMethod();
        TargetDataSource targetDataSource = getTargetDataSource(jp,method);
        if(targetDataSource != null && DynamicContextHolder.containsDataSource(targetDataSource.name())){
            System.out.println("ds.name:" + targetDataSource.name());
            DynamicContextHolder.setDataSourceKey(targetDataSource.name());
        }else {
            String name = method.getName().toLowerCase();
            if(name.startsWith("find")
                    || name.startsWith("query")
                    || name.startsWith("get")
                    || name.startsWith("search")
                    || name.startsWith("select")){
                DynamicContextHolder.setDataSourceKey("ds1");
            }else{
                DynamicContextHolder.setDefaultDataSource();
            }
        }
    }



    @After(value = "serviceAopPoint()")
    public void resetDataSource(JoinPoint jp){
        DynamicContextHolder.clearDataSourceKey();
    }


    private TargetDataSource getTargetDataSource(JoinPoint jp, Method Method) {
        Object[] args = jp.getArgs();
        Class<?>[] parameterTypes = new Class[args.length];
        if(args.length > 0){
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }
        }
        TargetDataSource targetDataSource = null;
        try {
            Method targetMethod = jp.getTarget().getClass().getDeclaredMethod(Method.getName(), parameterTypes);
            targetDataSource = AnnotationUtils.findAnnotation(targetMethod, TargetDataSource.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return targetDataSource;
    }
}
