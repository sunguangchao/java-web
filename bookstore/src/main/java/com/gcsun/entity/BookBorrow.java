package com.gcsun.entity;

import org.springframework.stereotype.Component;

/**
 * Created by 11981 on 2017/11/29.
 * 书籍借出表
 */
@Component
public class BookBorrow {

    private String bookBorrowId;
    private String bookId;
    private String bookName;
    private String bookWriter;
    private String bookType;
    private String bookPress;
    private String userId;
    private String userName;
    private String userTel;
    private String bookBorrowDate;
    private int    bookBorrowNum;
    private String bookReturnDate;
    private String bookRealReturnDate;

    public String getBookBorrowId() {
        return bookBorrowId;
    }

    public void setBookBorrowId(String bookBorrowId) {
        this.bookBorrowId = bookBorrowId;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getBookBorrowDate() {
        return bookBorrowDate;
    }

    public void setBookBorrowDate(String bookBorrowDate) {
        this.bookBorrowDate = bookBorrowDate;
    }

    public int getBookBorrowNum() {
        return bookBorrowNum;
    }

    public void setBookBorrowNum(int bookBorrowNum) {
        this.bookBorrowNum = bookBorrowNum;
    }

    public String getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(String bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public String getBookRealReturnDate() {
        return bookRealReturnDate;
    }

    public void setBookRealReturnDate(String bookRealReturnDate) {
        this.bookRealReturnDate = bookRealReturnDate;
    }
}
