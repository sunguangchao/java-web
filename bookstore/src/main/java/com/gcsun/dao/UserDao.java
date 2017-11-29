package com.gcsun.dao;

import com.gcsun.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by 11981 on 2017/11/29.
 * 注释未完成
 */
@Component
public interface UserDao {
    /**
     *
     * @param user
     * @return
     */
    User login(User user);

    void regin(User user);

    void updateUser(User user);

    User findUserName(String username);
}
