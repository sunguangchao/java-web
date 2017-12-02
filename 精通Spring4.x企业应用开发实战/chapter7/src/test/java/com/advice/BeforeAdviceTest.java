package com.advice;

import com.advisor.GreetingBeforeAdvice;
import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by 11981 on 2017/12/2.
 */
public class BeforeAdviceTest {
    @Test
    public void before(){
        Waiter target = new NaiveWaiter();

        BeforeAdvice advice = new GreetingBeforeAdvice();
        //Spring提供的代理工厂
        ProxyFactory pf = new ProxyFactory();
        pf.setInterfaces(target.getClass().getInterfaces());
        //启用优化
        pf.setOptimize(true);
        //设置代理目标
        pf.setTarget(target);
        //为代理目标添加增强
        pf.addAdvice(advice);
        //生成代理实例
        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }
}
/*Output:
com.advice.NaiveWaiter.greetTo
How are you！Mr.John.
waiter greet to John...
com.advice.NaiveWaiter.serveTo
How are you！Mr.Tom.
serving Tom...
 */