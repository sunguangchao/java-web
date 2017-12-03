package com.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by 11981 on 2017/12/3.
 */
public class GreetingInterceptor implements MethodInterceptor{
    //截获目标方法的执行，并在前后添加横切逻辑
    public Object invoke(MethodInvocation invocation) throws Throwable{
        Object[] args = invocation.getArguments();//目标方法入参

        String clientName = (String)args[0];
        System.out.println("How are you,Mr " + clientName + ".");//在目标方法执行前调用
        Object obj = invocation.proceed();//通过反射机制调用目标方法
        System.out.println("Please enjoy yourself");//在目标方法后调用
        return obj;
    }
}
