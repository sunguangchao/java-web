package com.gcsun.chat;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by 11981 on 2017/9/28.
 */

@ServerEndpoint(value = "/chat/{sessionId}",
        encoders = ChatMessageCodec.class,
        decoders = ChatMessageCodec.class,
        configurator = ChatEndpoint.EndpointConfigurator.class)
@WebListener
public class ChatEndpoint implements HttpSessionListener{
    private static final String HTTP_SESSION_PROPERTY = "com.gcsun.ws.HTTP_SESSION";
    private static final String WS_SESSION_PROPERTY = "com.gcsun.http.WS_SESSION";
    private static long sessionIdSequence = 1L;
    private static final Object sessionIdSequenceLock = new Object();
    private static final Map<Long, ChatSession> chatSessions = new Hashtable<>();
    private static final Map<Session, ChatSession> sessions = new Hashtable<>();
    private static final Map<Session, HttpSession> httpSessions = new Hashtable<>();
    //等待会话列表
    public static final List<ChatSession> pendingSessions = new ArrayList<>();

    @OnOpen
    //新的握手完成后，session和httpSession的区别
    public void onOpen(Session session, @PathParam("sessionId") long sessionId){
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(ChatEndpoint.HTTP_SESSION_PROPERTY);
        try {
            if (httpSession == null || httpSession.getAttribute("username") == null){
                session.close(new CloseReason(
                        //An Enumeration of status codes for a web socket close that are defined in the specification
                        CloseReason.CloseCodes.VIOLATED_POLICY,
                        "You are not logged in!"
                ));
                return;
            }
            String username = (String)httpSession.getAttribute("username");
            session.getUserProperties().put("username", username);
            ChatMessage message = new ChatMessage();
            message.setTimestamp(OffsetDateTime.now());
            message.setUser(username);
            ChatSession chatSession;
            //如果sessionId等于0，创建新会话，并添加到等待会话列表中
            if (sessionId < 1)
            {
                message.setType(ChatMessage.Type.STARTED);
                message.setContent(username + " started the chat session");
                chatSession = new ChatSession();
                synchronized (ChatEndpoint.sessionIdSequenceLock){
                    chatSession.setSessionId(ChatEndpoint.sessionIdSequence++);
                }
                //设置session
                chatSession.setCustomer(session);
                chatSession.setCustomerUsername(username);
                chatSession.setCreationMessage(message);
                ChatEndpoint.pendingSessions.add(chatSession);
                //聊天服务器映射
                ChatEndpoint.chatSessions.put(chatSession.getSessionId(), chatSession);
            }else{
                //如果sesssionId大于0，则不需要创建新会话
                message.setType(ChatMessage.Type.JOINED);
                message.setContent(username + " joined the chat session");
                //从映射中根据sessionId获取chatSession
                chatSession = ChatEndpoint.chatSessions.get(sessionId);
                chatSession.setRepresentative(session);
                chatSession.setRepresentativeUsername(username);
                ChatEndpoint.pendingSessions.remove(chatSession);
                //getBasicRemote()：阻塞式消息推送
                //getAsyncRemote()：非阻塞式消息推送
                session.getBasicRemote().sendObject(chatSession.getCreationMessage());//把对象直接发送出去
                session.getBasicRemote().sendObject(message);
            }

            ChatEndpoint.sessions.put(session, chatSession);
            ChatEndpoint.httpSessions.put(session, httpSession);
            this.getSessionsFor(httpSession).add(session);
            chatSession.log(message);
            chatSession.getCustomer().getBasicRemote().sendObject(message);
        }catch (IOException | EncodeException e){

        }
    }

    @OnError
    public void onError(Session session, Throwable e){
        ChatMessage message = new ChatMessage();
        message.setUser((String) session.getUserProperties().get("username"));
        message.setType(ChatMessage.Type.ERROR);
        message.setTimestamp(OffsetDateTime.now());
        message.setContent(message.getUser() + "left the chat due to an error");
        try {
            Session other = this.close(session, message);
            if (other != null){
                other.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, e.toString()));
            }
        }catch (IOException ignore){

        }finally {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, e.toString()));
            }catch (IOException ignore){

            }
        }
    }

    @OnMessage
    //从某个客户端接收到消息，将消息发送到两个客户端
    public void onMessage(Session session, ChatMessage message)
    {
        ChatSession c = ChatEndpoint.sessions.get(session);
        Session other = this.getOtherSession(c, session);
        if (c != null && other != null)
        {
            c.log(message);
            try {
                session.getBasicRemote().sendObject(message);
                other.getBasicRemote().sendObject(message);
            }catch (IOException | EncodeException e){
                this.onError(session, e);
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason){

        if (reason.getCloseCode() == CloseReason.CloseCodes.NORMAL_CLOSURE)
        {
            ChatMessage message = new ChatMessage();
            message.setUser((String) session.getUserProperties().get("username"));
            message.setType(ChatMessage.Type.LEFT);
            message.setTimestamp(OffsetDateTime.now());
            message.setContent(message.getUser() + " left the chat.");
            try {
                Session other = this.close(session,message);
                if (other != null)
                    other.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSession httpSession = event.getSession();
        if (httpSession.getAttribute(WS_SESSION_PROPERTY) != null){
            ChatMessage message = new ChatMessage();
            message.setUser((String)httpSession.getAttribute("username"));
            message.setType(ChatMessage.Type.LEFT);
            message.setTimestamp(OffsetDateTime.now());
            message.setContent(message.getUser() + "logged out.");
            for (Session session : new ArrayList<>(this.getSessionsFor(httpSession))){
                try {
                    session.getBasicRemote().sendObject(message);
                    Session other = this.close(session, message);
                    if (other != null)
                        other.close();
                }catch (IOException | EncodeException e){
                    e.printStackTrace();
                }
                finally {
                    try {
                        session.close();
                    }catch (IOException ignore){}
                }
            }
        }
    }
    @Override
    public void sessionCreated(HttpSessionEvent event){

    }
    private synchronized ArrayList<Session> getSessionsFor(HttpSession session){
        try {
            if (session.getAttribute(WS_SESSION_PROPERTY) == null)
                session.setAttribute(WS_SESSION_PROPERTY, new ArrayList<>());
            return (ArrayList<Session>) session.getAttribute(WS_SESSION_PROPERTY);
        }catch (IllegalStateException e){
            return new ArrayList<>();
        }
    }

    //当会话关闭时，一个消息将被发送到另一个用户，通知他聊天已经结束，并且关闭两个链接
    private Session close(Session s, ChatMessage message){
        ChatSession c = ChatEndpoint.sessions.get(s);
        Session other = this.getOtherSession(c, s);
        ChatEndpoint.sessions.remove(s);
        HttpSession h = ChatEndpoint.httpSessions.get(s);
        if (h != null)
            this.getSessionsFor(h).remove(s);
        if (c != null)
        {
            c.log(message);
            ChatEndpoint.pendingSessions.remove(c);
            ChatEndpoint.chatSessions.remove(c.getSessionId());
            try {
                c.writeChatLog(new File("chat."+c.getSessionId()+".log"));

            }catch (Exception e){
                System.err.println("Could not write chat log.");
            }
        }
        if (other != null){
            ChatEndpoint.sessions.remove(other);
            h = ChatEndpoint.httpSessions.get(other);
            if (h != null)
                this.getSessionsFor(h).remove(s);
            try {
                other.getBasicRemote().sendObject(message);
            }catch (IOException | EncodeException e){
                e.printStackTrace();
            }
        }
        return other;
    }

    private Session getOtherSession(ChatSession c, Session s){
        return c == null ? null : (s == c.getCustomer() ? c.getRepresentative() : c.getCustomer());
    }

    public static class EndpointConfigurator extends ServerEndpointConfig.Configurator{
        @Override
        //握手的时候，该方法将调用并暴露出底层的HTTP请求
        public void modifyHandshake(ServerEndpointConfig config,
                                    HandshakeRequest request,
                                    HandshakeResponse response){
            super.modifyHandshake(config, request, response);
            config.getUserProperties().put(ChatEndpoint.HTTP_SESSION_PROPERTY, request.getHttpSession());
        }
    }

}
