package com.gcsun;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 11981 on 2017/11/14.
 * JDK 动态代理
 * 实现InvocationHandler
 */
public class PerformanceHandler implements InvocationHandler {
    private Object target;

    public PerformanceHandler(Object target){
        this.target = target;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        Object obj = method.invoke(target, args);//通过反射方法调用业务类的目标方法
        PerformanceMonitor.end();
        return obj;
    }


}
