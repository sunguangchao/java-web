package com.springaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 11981 on 2017/11/17.
 */
@Component
public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;

    @Autowired
    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }

    public void play(){
        cd.play();
    }
}
