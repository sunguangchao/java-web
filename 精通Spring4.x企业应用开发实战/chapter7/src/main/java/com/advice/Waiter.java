package com.advice;

/**
 * Created by 11981 on 2017/12/2.
 * 因为和后面的例子有冲突，所以在这里新建了一个Waiter的接口类
 */
public interface Waiter {
    void greetTo(String name);
    void serveTo(String name);
}
