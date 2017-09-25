package com.sunyard.dao;

import com.sunyard.BaseTest;
import com.sunyard.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by 11981 on 2017/9/22.
 */
public class BookDaoTest extends BaseTest{
    @Autowired
    private BookDao bookDao;

    @Test
    public void testQueryById() throws Exception{
        long bookId = 1000;
        Book book = bookDao.queryById(bookId);
        System.out.println(book);
    }

    @Test
    public void testQueryAll() throws Exception{
        List<Book> bookList = bookDao.queryAll(0, 4);
        for (Book book : bookList){
            System.out.println(book);
        }
    }

    @Test
    public void testReduceNumber() throws Exception{
        long bookId = 1000;
        int update = bookDao.reduceNumber(bookId);
        System.out.println("update=" + update);
    }




}
