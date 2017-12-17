package com.gcsun.dao;

import com.gcsun.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by 11981 on 2017/12/11.
 */
public class PostDao {
    private JdbcTemplate jdbcTemplate;

    public void addPost(final Post post){
        String sql = " INSERT INTO t_post(topic_id,post_text)"
                + " VALUES(?,?)";
        Object[] params = new Object[]{post.getTopicId(), post.getPostText()};
        jdbcTemplate.update(sql, params);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
