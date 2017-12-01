package com.gcsun;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by 11981 on 2017/11/14.
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    //拦截父类所有方法的调用
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
        PerformanceMonitor.begin(obj.getClass().getName() + "." + method.getName());
        Object result = proxy.invokeSuper(obj, args);//通过代理类调用父类中的方法。
        PerformanceMonitor.end();
        return result;

    }
}
