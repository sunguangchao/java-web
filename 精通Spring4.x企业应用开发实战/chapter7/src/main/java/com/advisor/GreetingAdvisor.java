package com.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by 11981 on 2017/12/3.
 * 静态普通方法名匹配切面
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor{
    //切点方法匹配规则：方法名为greetTo
    public boolean matches(Method method, Class clazz){
        return "greetTo".equals(method.getName());
    }

    //切点类配匹配规则：Waiter的类或子类
    @Override
    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            public boolean matches(Class<?> aClass) {
                return Waiter.class.isAssignableFrom(aClass);
            }
        };
    }

}
