package com.gcsun;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by 11981 on 2017/9/30.
 */
public class Configurator implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event){
        ServletContext context = event.getServletContext();
        FilterRegistration.Dynamic registration = context.addFilter(
                "authenticationFilter", new AuthenticationFilter()
        );
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(
                null, false, "/tickets", "/chat", "/sessions"
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent event){

    }


}
