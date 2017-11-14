package com.smart.dao;

import com.smart.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

/**
 * Created by 11981 on 2017/4/23.
 * @Repository 用于对DAO实现类进行标注
 * @Service 用于对Service实现类进行标注
 * @Controller 用于对Controller实现类进行标注
 *
 */

@Repository
public class BoardDao extends BaseDao<Board>{
    private static final String GET_BOARD_NUM = "select count(f.boardId) from Board f";

    public long getBoardNum(){
        Iterator iter = getHibernateTemplate().iterate(GET_BOARD_NUM);
        return ((Long)iter.next());
    }
}
