package com.gcsun.service;

import com.gcsun.dao.BookDao;
import com.gcsun.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 11981 on 2017/11/29.
 */
public class BookService {
    @Autowired
    private BookDao bookDao;


    public boolean addBook(Book book){
        try {
            bookDao.addBook(book);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
