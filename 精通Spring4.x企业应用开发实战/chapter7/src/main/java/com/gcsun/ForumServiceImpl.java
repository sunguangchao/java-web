package com.gcsun;

/**
 * Created by 11981 on 2017/11/13.
 */
public class ForumServiceImpl implements ForumService{


    public void removeTopic(int topicId){
        //在此位置的原有横切代码被移除
//        PerformanceMonitor.begin("com.gcsun.ForumServiceImpl.removeTopic");
        System.out.println("模拟删除Topic记录:"+topicId);
        try {
            Thread.currentThread().sleep(20);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
//        PerformanceMonitor.end();

    }

    public void removeForum(int forumId){
//        PerformanceMonitor.begin("com.gcsun.ForumServiceImpl.removeForum");
        System.out.println("模拟删除Forum记录:"+forumId);
        try {
            Thread.currentThread().sleep(40);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
//        PerformanceMonitor.end();

    }
}
