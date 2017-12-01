package com.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by 11981 on 2017/12/2.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        String clientName = (String) args[0];
        System.out.println(obj.getClass().getName() + "." + method.getName());
        System.out.println("How are you！Mr." + clientName + ".");
    }
}
