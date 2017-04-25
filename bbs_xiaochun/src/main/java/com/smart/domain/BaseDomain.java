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
}
