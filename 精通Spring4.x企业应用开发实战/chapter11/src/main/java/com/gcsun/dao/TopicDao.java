package com.gcsun.dao;

import com.gcsun.domain.Topic;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by 11981 on 2017/12/11.
 */
public class TopicDao {
    private JdbcTemplate jdbcTemplate;

    public void addTopic(final Topic topic){
        final String sql = "INSERT INTO t_topic(topic_title) VALUES(?)";
        Object[] params = new Object[]{topic.getTopicTitle()};
        jdbcTemplate.update(sql, params);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
