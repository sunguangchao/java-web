package com.advice;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 11981 on 2017/12/3.
 * 异常抛出增强测试
 *
 * 在这里ForumService的两个方法所抛出的增强都被TransactionManager这个异常
 * 抛出增强捕获成功并成功处理
 */
public class ThrowAdviceTest {
    @Test
    public void throwAdvice(){
        String configPath = "advice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService) ctx.getBean("forumService");
        try {
            forumService.removeForum(10);
        }catch (Exception e){}

        try {
            forumService.updateForum(new Forum());
        }catch (Exception e){

        }

    }
}
/**
 * Output:
 *
 * -----------
 method:removeForum
 抛出异常:运行异常
 成功回滚事务。
 -----------
 method:updateForum
 抛出异常:数据更新操作异常
 成功回滚事务。
 */
