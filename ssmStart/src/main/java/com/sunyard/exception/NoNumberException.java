package com.sunyard.exception;

/**
 * Created by 11981 on 2017/9/23.
 */
public class NoNumberException extends RuntimeException {
    public NoNumberException(String message) {
        super(message);
    }

    public NoNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
