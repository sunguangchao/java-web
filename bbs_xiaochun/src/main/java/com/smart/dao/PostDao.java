package com.smart.dao;

import com.smart.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11981 on 2017/4/23.
 */
@Repository
public class PostDao extends BaseDao<Post>{

    private static final String GET_PAGED_POSTS = "from Post where topic.topicId =? order by createTime desc";

    private static final String DELETE_TOPIC_POSTS = "delete from Post where topic.topicId=?";

    private static final String GET_POST_ID = "select postText from Post where user.userId = ? order by createTime desc";
    public Page getPagedPosts(int topicId, int pageNo, int pageSize){
        return pagedQuery(GET_PAGED_POSTS, pageNo, pageSize, topicId);
    }

    /**
     * 删除主题下的所有帖子
     * @param topicId 主题ID
     */
    public void deleteTopicPosts(int topicId){
        getHibernateTemplate().bulkUpdate(DELETE_TOPIC_POSTS);
    }

    /**
     * 根据用户的ID得到其回复过的帖子
     */

    public List<String> getPostByUserId(int userId){
        List<String> postText = (List<String>)getHibernateTemplate().find(GET_POST_ID, userId);
        if (postText == null)
            return null;
        else
            return postText;
    }
}
