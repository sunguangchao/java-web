package com.smart.web;

import com.smart.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.Filter;

import static com.smart.cons.CommonConstant.LOGIN_TO_URL;
import static com.smart.cons.CommonConstant.USER_CONTEXT;

/**
 * Created by 11981 on 2017/4/23.
 */
public class ForumFilter implements Filter{
    private static final String FILTERED_REQUSET = "@@session_context_filtered_request";

    //不需要登录即可访问的URI资源
    private static final String[] INHERENT_ESCAPE_URIS =  { "/index.jsp",
            "/index.html", "/login.jsp", "/login/doLogin.html",
            "/register.jsp", "/register.html", "/board/listBoardTopics-",
            "/board/listTopicPosts-" };

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
        if (request != null && request.getAttribute(FILTERED_REQUSET) != null){
            chain.doFilter(request,response);
        }else{
            request.setAttribute(FILTERED_REQUSET, Boolean.TRUE);
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            User userContext = getSessionUser(httpRequest);

            if (userContext == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)){
                String toUrl = httpRequest.getRequestURL().toString();
                if (!StringUtils.isEmpty(httpRequest.getQueryString())){
                    toUrl += "?" + httpRequest.getQueryString();
                }

                httpRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);

                request.getRequestDispatcher("/login.jsp").forward(request, response);

                return;
            }
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException{

    }
    /**
     * 当前URI资源是否需要登录才能访问
     * @param requestURI
     * @param request
     * @return
     */
    private boolean isURILogin(String requestURI, HttpServletRequest request){
        if (request.getContextPath().equalsIgnoreCase(requestURI)
                 || (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
            return true;
        for (String uri : INHERENT_ESCAPE_URIS){
            if (requestURI != null && requestURI.indexOf(uri) >= 0){
                return true;
            }
        }
        return false;
    }

    protected User getSessionUser(HttpServletRequest request){
        return (User)request.getSession().getAttribute(USER_CONTEXT);
    }

    public void destroy(){

    }

}
