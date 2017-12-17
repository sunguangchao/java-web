package com.gcsun.service;

import com.gcsun.dao.ForumDao;
import com.gcsun.dao.PostDao;
import com.gcsun.dao.TopicDao;
import com.gcsun.domain.Forum;
import com.gcsun.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 11981 on 2017/12/11.
 */
@Service
@Transactional//对业务类进行事务增强的标注，见applicationContext-anno.xml
public class BbtForum {
    public ForumDao forumDao;
    public TopicDao topicDao;
    public PostDao postDao;

    public void addTopic(Topic topic) throws Exception{
        topicDao.addTopic(topic);
        postDao.addPost(topic.getPost());
    }

    @Transactional(readOnly = true)
    public Forum getForum(int forumId){
        return forumDao.getForum(forumId);
    }

    public void updateForum(Forum forum){
        forumDao.updateForum(forum);
    }

    public int getForumNum(){
        return forumDao.getForumNum();
    }
    @Autowired
    public void setForumDao(ForumDao forumDao){
        this.forumDao = forumDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }
}
