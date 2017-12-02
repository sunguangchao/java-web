package com.advice;

import com.advice.Waiter;

/**
 * Created by 11981 on 2017/12/2.
 * 训练不足的服务生
 */
public class NaiveWaiter implements Waiter{

    public void greetTo(String name){
        System.out.println("greet to " + name + "...");
    }
    public void serveTo(String name){
        System.out.println("serving "+name+"...");
    }
}
