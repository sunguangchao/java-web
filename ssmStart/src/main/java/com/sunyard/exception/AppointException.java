package com.sunyard.exception;

/**
 * Created by 11981 on 2017/9/23.
 * 库存不足异常
 */
public class AppointException extends RuntimeException {
    public AppointException(String message){
        super(message);
    }

    public AppointException(String message, Throwable cause){
        super(message, cause);
    }
}
