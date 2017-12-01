package com.springaction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by 11981 on 2017/11/17.
 */
@Component//该注解表明这个类会作为组件类
@ComponentScan//启用组件扫描
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play(){
        System.out.println("Playing " + title + " by " + artist);
    }
}
