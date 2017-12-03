package com.advisor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 11981 on 2017/12/3.
 */
public class StaticMethodAdvisorTest {
    @Test
    public void staticMethod(){
        String config = "advisor/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Waiter waiter = (Waiter) ctx.getBean("waiter");
        Seller seller = (Seller) ctx.getBean("seller");
        waiter.greetTo("John");
        waiter.greetTo("John");
        seller.greetTo("John");
    }
}
