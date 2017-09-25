package com.sunyard.service.impl;

import com.sunyard.BaseTest;
import com.sunyard.dto.AppointExecution;
import com.sunyard.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 11981 on 2017/9/23.
 */
public class BookServiceImplTest extends BaseTest{
    @Autowired
    private BookService bookService;
    @Test
    public void testAppoint() throws Exception{
        long bookId = 1001;
        long studentId = 12345678910L;
        AppointExecution execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }
}
