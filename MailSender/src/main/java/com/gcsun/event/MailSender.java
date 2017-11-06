package com.gcsun.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by 11981 on 2017/11/6.
 * 实现此接口才具有发布事件的能力
 */
public class MailSender implements ApplicationContextAware {
    private ApplicationContext ctx;


    //以便容器启动时注入容器实例
    public void setApplicationContext(ApplicationContext ctx) throws BeansException{
        this.ctx = ctx;
    }

    public void sendMail(String to){
        System.out.println("MailSender:模拟发送邮件...");
        MailSendEvent mse = new MailSendEvent(this.ctx, to);
        //向容器中所有事件监听器发送事件
        ctx.publishEvent(mse);
    }
}
