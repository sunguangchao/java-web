package com.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by 11981 on 2017/12/2.
 * 前置增强
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    //在目标类调用方法前执行
    //method为目标类方法，args为目标类的入餐，obj为目标类实例
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        String clientName = (String) args[0];
        System.out.println(obj.getClass().getName() + "." + method.getName());
        System.out.println("How are you！Mr." + clientName + ".");
    }
}
