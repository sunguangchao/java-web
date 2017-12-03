package com.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by 11981 on 2017/12/3.
 * 通过实现AfterReturningAdvice来定义后置增强的逻辑
 */
public class GreetingAfterAdvice implements AfterReturningAdvice{
    public void afterReturning(Object returnObj, Method method, Object[] args, Object obj){
        System.out.println("Please enjoy yourself");

    }
}
