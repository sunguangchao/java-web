package com.gcsun;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by 11981 on 2017/9/30.
 * session注册表
 * 维护活跃会话列表
 */
public final class SessionRegistry {
    //存放session的映射
    private static final Map<String, HttpSession> SESSIONS = new Hashtable<>();
    public static void addSession(HttpSession session)
    {
        SESSIONS.put(session.getId(), session);
    }

    public static void updateSessionId(HttpSession session, String oldSessionId){
        //采用同步的方法
        synchronized (SESSIONS){
            SESSIONS.remove(oldSessionId);
            addSession(session);
        }
    }

    public static void removeSession(HttpSession session){
        SESSIONS.remove(session.getId());
    }

    //包含注册表中所有session的列表
    public static List<HttpSession> getAllSessions(){
        return new ArrayList<>(SESSIONS.values());
    }

    public static int getNumberOfSessions(){
        return SESSIONS.size();
    }

    //构造函数私有，阻止创建实例
    private SessionRegistry(){

    }
}
