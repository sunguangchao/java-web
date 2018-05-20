package com.gcsun.exception;


/**
 * Created by 11981 on 2018/5/20.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message){
        super(message);
    }

    public SeckillException(String message, Throwable cause){
        super(message, cause);
    }
}
