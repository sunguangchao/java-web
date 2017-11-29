package com.gcsun.dao;

import com.gcsun.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 11981 on 2017/11/29.
 * 这里方法名就对应了映射文件中的id
 */
@Component
public interface BookDao {

    /**
     * 添加书籍
     * @param book
     */
    void addBook(Book book);

    /**
     * 分页查询所有书
     * @param startPage
     * @return
     */
    List<Book> findBook(int startPage);


    /**
     * 查询所有书籍
     * @return
     */
    List<Book> findAllBooks();

    /**
     * 查询书籍详情
     * @param bookId
     * @return
     */
    Book findBookProduct(String bookId);
}
