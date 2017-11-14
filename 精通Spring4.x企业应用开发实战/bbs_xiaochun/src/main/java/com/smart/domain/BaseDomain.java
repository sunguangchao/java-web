package com.smart.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by 11981 on 2017/4/23.
 * 所有PO的父类
 */

//实现了Serializable接口，以便JVM可以序列化PO实例
public class BaseDomain implements Serializable {

    //统一的toString()方法
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
    /*
    *  系统中一般都要打印日志的，因为所有实体的toString()方法 都用的是简单的"+"，
    *  因为每"＋" 一个就会 new 一个 String 对象，这样如果系统内存小的话会暴内存
    *  （前提系统实体比较多）。使用ToStringBuilder就可以避免暴内存这种问题的。
    * */
}
