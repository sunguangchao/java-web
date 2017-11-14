package com.gcsun;

/**
 * Created by 11981 on 2017/11/14.
 */
public class TestForumService {
    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeForum(10);
        forumService.removeTopic(1012);
    }
}
