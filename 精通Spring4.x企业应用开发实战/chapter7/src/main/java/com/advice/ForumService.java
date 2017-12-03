package com.advice;

import java.sql.SQLException;

/**
 * Created by 11981 on 2017/12/3.
 * 异常抛出增强类
 */
public class ForumService {
    public void removeForum(int forumId){
        throw new RuntimeException("运行异常");
    }

    public void updateForum(Forum forum) throws Exception{
        throw new SQLException("数据更新操作异常");
    }
}
