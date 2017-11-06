package com.gcsun.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * Created by 11981 on 2017/11/6.
 */
public class MailSendEvent extends ApplicationContextEvent {
    private String to;
    public MailSendEvent(ApplicationContext source, String to){
        super(source);
        this.to = to;
    }

    public String getTo(){
        return this.to;
    }
}
