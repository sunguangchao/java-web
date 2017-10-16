package com.gcsun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by 11981 on 2017/9/30.
 */
@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
    //这里使用Hashtable，多线程情况下是安全的
    private static final Map<String, String> userDatabase = new Hashtable<>();
    //使用静态代码块进行初始化
    static {
        userDatabase.put("Nicholas", "password");
        userDatabase.put("Sarah", "drowssap");
        userDatabase.put("Mike", "wordpass");
        userDatabase.put("John", "green");
    }

    //主要进行页面跳转的控制
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        if (request.getParameter("logout") != null)
        {
            session.invalidate();
            response.sendRedirect("login");
            return;
        }else if (session.getAttribute("username") != null){
            response.sendRedirect("tickets");
            return;
        }
        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);


    }

    //jsp中的登录表单被提交时，请求发送到doPost方法
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null){
            response.sendRedirect("tickets");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //不能通过验证的情况
        if (username == null || password == null ||
                !LoginServlet.userDatabase.containsKey(username) ||
                !password.equals(LoginServlet.userDatabase.get(username))){
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
        //通过验证，进行下面的操作
        }else{
            //添加username特性
            session.setAttribute("username", username);
            //直接更改会话ID
            request.changeSessionId();
            //重定向至票据页面
            response.sendRedirect("tickets");
        }

    }
}
