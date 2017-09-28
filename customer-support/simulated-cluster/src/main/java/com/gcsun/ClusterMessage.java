package com.gcsun;

import java.io.Serializable;

/**
 * Created by 11981 on 2017/9/27.
 */
public class ClusterMessage implements Serializable {
    private String nodeId;
    private String message;

    public ClusterMessage(){

    }

    public ClusterMessage(String nodeId, String message){
        this.nodeId = nodeId;
        this.message = message;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getMessage() {
        return message;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
