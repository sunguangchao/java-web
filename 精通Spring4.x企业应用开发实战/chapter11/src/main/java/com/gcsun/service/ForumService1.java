package com.gcsun.service;

import com.gcsun.dao.ForumDao;
import com.gcsun.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by 11981 on 2017/12/14.
 * 编程式的事务管理
 */
@Service
public class ForumService1 {
    public ForumDao forumDao;
    public TransactionTemplate template;
    public void addForum(final Forum forum){
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                forumDao.addForum(forum);//需要在事务环境中执行的代码
            }
        });
    }

    @Autowired
    public void setForumDao(ForumDao forumDao){
        this.forumDao = forumDao;
    }

    @Autowired//通过AOP主动注入
    public void setTemplate(TransactionTemplate template){
        this.template = template;
    }
}
