package com.gcsun;

/**
 * Created by 11981 on 2017/11/13.
 * 用于记录性能监视信息
 */
public class MethodPerformance {
    private long begin;
    private long end;
    private String serviceMethod;


    public MethodPerformance(String serviceMethod){
        this.serviceMethod = serviceMethod;
        this.begin = System.currentTimeMillis();
    }

    public void printPerformance(){
        end = System.currentTimeMillis();
        //计算目标方法类的执行时间
        long elapse = end - begin;
        System.out.println(serviceMethod+"花费"+elapse+"毫秒。");
    }

    public void reset(String serviceMethod){
        this.serviceMethod = serviceMethod;
        this.begin = System.currentTimeMillis();
    }
}
