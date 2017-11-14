package com.smart.execption;

/**
 * Created by 11981 on 2017/4/23.
 */
public class UserExistException extends Exception{
    public UserExistException(String errorMsg){
        super(errorMsg);
    }
}
