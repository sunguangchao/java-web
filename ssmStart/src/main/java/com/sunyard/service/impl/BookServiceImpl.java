package com.sunyard.service.impl;

import com.sunyard.dao.AppointmentDao;
import com.sunyard.dao.BookDao;
import com.sunyard.dto.AppointExecution;
import com.sunyard.entity.Appointment;
import com.sunyard.entity.Book;
import com.sunyard.enums.AppointStateEnum;
import com.sunyard.exception.AppointException;
import com.sunyard.exception.NoNumberException;
import com.sunyard.exception.RepeatAppointException;
import com.sunyard.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 11981 on 2017/9/23.
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AppointmentDao appointmentDao;



    public Book getById(long bookId){
        return bookDao.queryById(bookId);
    }

    public List<Book> getList(){
        return bookDao.queryAll(0, 1000);
    }

    @Transactional
    /**
     * 使用注解控制事务方法的优点：
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    public AppointExecution appoint(long bookId, long studentId){
        try {
            int update = bookDao.reduceNumber(bookId);
            if (update <= 0){
                throw new NoNumberException("no number");
            }else{
                int insert = appointmentDao.insertAppointment(bookId, studentId);
                if (insert <= 0){
                    throw new RepeatAppointException("repeat appoint");
                }else{
                    Appointment appointment = appointmentDao.queryByKeyWithBook(bookId,studentId);
                    return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
                }

            }
        }catch (NoNumberException e1){
            throw e1;
        }catch (RepeatAppointException e2){
            throw e2;
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            throw new AppointException("appoint inner error:" + e.getMessage());
        }
    }

}
