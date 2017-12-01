package com.advisor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 11981 on 2017/12/1.
 */
public class DynamicAdvisorTest {

    @Test
    public void dynamic(){
        String configPath = "advisor/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)ctx.getBean("waiter2");
        waiter.serveTo("Peter");
        waiter.greetTo("Peter");
        waiter.serveTo("Peter");
        waiter.greetTo("John");
    }
}
