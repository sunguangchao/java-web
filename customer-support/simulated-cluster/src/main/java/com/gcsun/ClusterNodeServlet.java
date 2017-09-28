package com.gcsun;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by 11981 on 2017/9/27.
 */
@ClientEndpoint//告诉容器该类是一个合法终端，该类不会被自动实例化
public class ClusterNodeServlet extends HttpServlet {
    private Session session;
    private String nodeId;

    @Override
    public void init() throws ServletException
    {
        this.nodeId = this.getInitParameter("nodeId");
        String path = this.getServletContext().getContextPath() + "/clusterNodeSocket/" + this.nodeId;
        try {
            URI uri = new URI("ws", "localhost:8081", path, null, null);
            this.session = ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
        }catch (URISyntaxException | IOException | DeploymentException e)
        {
            throw new ServletException("Cannot connect to " + path + ".", e);
        }
    }

    @Override
    public void destroy(){
        try {
            this.session.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ClusterMessage message = new ClusterMessage(this.nodeId, "request:{ip:\"" + request.getRemoteAddr() +
                "\",queryString:\"" + request.getQueryString() + "\"}");
        try (
            OutputStream output = this.session.getBasicRemote().getSendStream();
            ObjectOutputStream stream = new ObjectOutputStream(output)
        )
        {
            stream.writeObject(message);
        }
        response.getWriter().append("OK");

    }
    @OnMessage
    //接收来自其它集群节点的回复信息
    public void onMessage(InputStream input){
        try (ObjectInputStream stream = new ObjectInputStream(input)){
            ClusterMessage message = (ClusterMessage)stream.readObject();
            System.out.println("INFO (Node " + this.nodeId +
                    "): Message received from cluster; node = " +
                    message.getNodeId() + ", message = " + message.getMessage());

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    @OnClose
    //将在连接异常关闭时打印错误消息
    public void onClose(CloseReason reason){
        CloseReason.CloseCode code = reason.getCloseCode();
        if (code != CloseReason.CloseCodes.NORMAL_CLOSURE){
            System.err.println("ERROR: WebSocket connection closed unexpectedly;" +
                    " code = " + code + ", reason = " + reason.getReasonPhrase());
        }
    }
}
