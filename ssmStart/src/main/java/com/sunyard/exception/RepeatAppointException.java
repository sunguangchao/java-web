package com.sunyard.exception;

/**
 * Created by 11981 on 2017/9/23.
 */
public class RepeatAppointException extends RuntimeException{
    public RepeatAppointException(String message){
        super(message);
    }

    public RepeatAppointException(String message, Throwable cause){
        super(message, cause);
    }
}
