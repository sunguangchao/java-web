package com.gcsun.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 11981 on 2017/11/6.
 */
public class ApplicationEventTest {
    public static void main(String[] args) {
        String resourceFile = "beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(resourceFile);
        MailSender mailSender = ctx.getBean(MailSender.class);
        mailSender.sendMail("text mail");
        System.out.println("done.");
    }
}
