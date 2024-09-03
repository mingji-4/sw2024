package org.comstudy.myweb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
        System.out.println("생성자 SessionListener");
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("sessionCreated() - SessionListener");
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("sessionDestroyed() - SessionListener");
    }
}