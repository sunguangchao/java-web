package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * Created by 11981 on 2017/4/23.
 */
@Repository
public class LoginLogDao extends BaseDao<LoginLog>{
    public void save(LoginLog loginLog){
        this.getHibernateTemplate().save(loginLog);
    }
}
