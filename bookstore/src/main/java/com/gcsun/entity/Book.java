package com.gcsun.entity;

import org.springframework.stereotype.Component;

/**
 * Created by 11981 on 2017/11/29.
 * 思考：是否可以做一个抽象类把共同的部分抽象出来
 */
@Component
public class Book {

    private String bookId;    //书籍ID
    private String bookName;
    private String bookWriter;//作者
    private String bookType;  //类型
    private String bookPress; //出版社
    private int    bookNum;   //数量
    private int    bookSellingPrice; //销售价格
    private int    bookBorrowPrice; //借出价格
    private String bookAddress;

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

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getBookSellingPrice() {
        return bookSellingPrice;
    }

    public void setBookSellingPrice(int bookSellingPrice) {
        this.bookSellingPrice = bookSellingPrice;
    }

    public int getBookBorrowPrice() {
        return bookBorrowPrice;
    }

    public void setBookBorrowPrice(int bookBorrowPrice) {
        this.bookBorrowPrice = bookBorrowPrice;
    }

    public String getBookAddress() {
        return bookAddress;
    }

    public void setBookAddress(String bookAddress) {
        this.bookAddress = bookAddress;
    }
}
