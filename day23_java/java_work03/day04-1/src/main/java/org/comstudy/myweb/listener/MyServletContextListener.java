package org.comstudy.myweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
        System.out.println("MyServletContextListener 생성자");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed() - MyServletContextListener");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("contextInitialized() - MyServletContextListener");
    }
}