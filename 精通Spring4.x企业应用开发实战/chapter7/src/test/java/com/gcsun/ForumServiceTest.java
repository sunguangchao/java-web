package com.gcsun;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by 11981 on 2017/11/14.
 * 创建动态代理实例
 */
public class ForumServiceTest {
        @Test
        public void proxy(){
            ForumService target = new ForumServiceImpl();//希望被代理的目标业务类
            //将目标业务类和横切代码编织到一起
            PerformanceHandler handler = new PerformanceHandler(target);
            //根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
            ForumService proxy = (ForumService) Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    handler
            );
            //调用代理实例
            proxy.removeForum(10);
            proxy.removeTopic(1012);
        }

}

/**结果：
 begin monitor...
 模拟删除Forum记录:10
 end monitor...
 com.gcsun.ForumServiceImpl.removeForum花费41毫秒。
 begin monitor...
 模拟删除Topic记录:1012
 end monitor...
 com.gcsun.ForumServiceImpl.removeTopic花费20毫秒。
 */