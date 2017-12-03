package com.introduce;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 11981 on 2017/12/3.
 */
public class IntroduceTest {
    @Test
    public void introduce(){
        String configPath = "introduce/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService)ctx.getBean("forumService");
        forumService.removeForum(10);//默认情况下，未开启性能监控模式
        forumService.removeTopic(1022);
        Monitorable monitorable = (Monitorable) forumService;//开启性能监控模式
        monitorable.setMonitorActive(true);
        forumService.removeForum(10);
        forumService.removeTopic(1022);
    }
}
