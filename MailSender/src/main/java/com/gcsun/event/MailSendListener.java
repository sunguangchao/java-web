package com.gcsun.event;

import org.springframework.context.ApplicationListener;

/**
 * Created by 11981 on 2017/11/6.
 */
public class MailSendListener implements ApplicationListener<MailSendEvent> {
    //对事件进行处理
    public void onApplicationEvent(MailSendEvent event){
        MailSendEvent mse = (MailSendEvent) event;
        System.out.println("MailSendListener:向" + mse.getTo() + "发送完一封邮件");
    }

}
