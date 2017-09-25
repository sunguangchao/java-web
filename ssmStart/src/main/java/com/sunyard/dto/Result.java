package com.sunyard.dto;

/**
 * Created by 11981 on 2017/9/23.
 * 封装json对象，所有返回结果都使用它
 */
public class Result<T> {
    private boolean success;
    private T data;
    private String error;

    public Result(){

    }

    //成功时的构造器
    public Result(boolean success, T data){
        this.success = success;
        this.data = data;
    }
    //错误时的构造器
    public Result(boolean success, String error){
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(){
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "JsonResult[" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error +
                ']';
    }
}
