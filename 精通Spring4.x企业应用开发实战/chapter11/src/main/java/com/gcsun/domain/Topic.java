package com.gcsun.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 11981 on 2017/12/11.
 */
public class Topic implements Serializable{
    private static final long serialVersionUID = 1L;
    private int topicId;
    private String topicTitle;
    private Date topicTime;
    private Post post;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Date getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Date topicTime) {
        this.topicTime = topicTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
