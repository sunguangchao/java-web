package com.gcsun.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 11981 on 2017/12/15.
 */
@Service
public class UserService {

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addCredits(){};
}
