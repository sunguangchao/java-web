package com.gcsun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 11981 on 2017/9/30.
 * 显示会话列表
 */
@WebServlet(
        name = "sessionListServlet",
        urlPatterns = "/sessions"
)
public class SessionListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException{
        request.setAttribute("timestamp", System.currentTimeMillis());
        request.setAttribute("numberOfSessions", SessionRegistry.getNumberOfSessions());
        request.setAttribute("sessionList", SessionRegistry.getAllSessions());
        request.getRequestDispatcher("/WEB-INF/jsp/view/sessions.jsp").forward(request, response);

    }
}
