package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 11981 on 2017/3/12.
 */

@Service //将UserService标注为一个服务层的Bean
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    //注入UserDao的Bean
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    //注入LoginLogDao的Bean
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao = loginLogDao;
    }

    //檢查用戶名/密碼的正確性
    public boolean hasMatchUser(String userName, String password){
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }
    //以用戶名為條件加載User對象
    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional //事务注解，讓該方法運行在事务环境中
    public void loginSuccess(User user){
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

}
