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
 * Created by 11981 on 2017/12/15.
 */
@Service
public class MultiForumService {
    private ForumDao forumDao;
    private TopicDao topicDao;
    private PostDao postDao;

    @Transactional("topic")
    public void addTopic(Topic topic) throws Exception{
        System.out.println("topic tx");
    }
    @Transactional("forum")
    public void updateForum(Forum forum){
        System.out.println("forum tx");
    }
    public ForumDao getForumDao(){
        return forumDao;
    }
    @Autowired
    public void setForumDao(ForumDao forumDao){
        this.forumDao = forumDao;
    }

    public TopicDao getTopicDao(){
        return topicDao;
    }

    @Autowired
    public void setTopicDao(TopicDao topicDao){
        this.topicDao = topicDao;
    }

    public PostDao getPostDao(){
        return postDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao){
        this.postDao = postDao;
    }
}
