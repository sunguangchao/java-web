package com.gcsun;

import org.junit.Test;

/**
 * Created by 11981 on 2017/11/14.
 */
public class ForumServiceTest2 {
    @Test
    public void proxy(){
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }
}
/**
 begin monitor...
 模拟删除Forum记录:10
 end monitor...
 com.gcsun.ForumServiceImpl$$EnhancerByCGLIB$$593d746.removeForum花费61毫秒。
 begin monitor...
 模拟删除Topic记录:1023
 end monitor...
 com.gcsun.ForumServiceImpl$$EnhancerByCGLIB$$593d746.removeTopic花费20毫秒。
*/