package com.gcsun;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Created by 11981 on 2017/9/10.
 */
public class PageVisit implements Serializable{
    private long enteredTimestamp;
    //封装过的Long数据，因为leftTimestamp可以为null
    private Long leftTimestamp;
    private String request;
    private InetAddress ipAddress;

    public long getEnteredTimestamp() {
        return enteredTimestamp;
    }

    public void setEnteredTimestamp(long enteredTimestamp) {
        this.enteredTimestamp = enteredTimestamp;
    }

    public Long getLeftTimestamp() {
        return leftTimestamp;
    }

    public void setLeftTimestamp(Long leftTimestamp) {
        this.leftTimestamp = leftTimestamp;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }


}
