package com.gcsun.entity;

import org.springframework.stereotype.Component;

/**
 * Created by 11981 on 2017/11/29.
 * 书籍归还表
 */
@Component
public class BookInto {

    private String bookId;
    private String bookName;
    private String bookWriter;
    private String bookType;
    private String bookPress;
    private int bookIntoNum;
    private int bookCostPrice;
    private String bookIntoDate;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public int getBookIntoNum() {
        return bookIntoNum;
    }

    public void setBookIntoNum(int bookIntoNum) {
        this.bookIntoNum = bookIntoNum;
    }

    public int getBookCostPrice() {
        return bookCostPrice;
    }

    public void setBookCostPrice(int bookCostPrice) {
        this.bookCostPrice = bookCostPrice;
    }

    public String getBookIntoDate() {
        return bookIntoDate;
    }

    public void setBookIntoDate(String bookIntoDate) {
        this.bookIntoDate = bookIntoDate;
    }
}
