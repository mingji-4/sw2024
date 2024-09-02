package org.comstudy.common.controller;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.common.command.Command;
import org.comstudy.common.command.MainCommandImpl;

@SuppressWarnings("unused")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Command> commandMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		try (FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		Iterator keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			//System.out.println(command+ " : " +handlerClassName);
			try {
				Class<?> commandClass = Class.forName(handlerClassName);
				// Command instance = (Command) handlerClass.newInstance();
				Constructor<?> constructor = commandClass.getConstructor(null);
				constructor.newInstance();
				Command instance = (Command) constructor.newInstance();
				commandMap.put(command, instance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reqURI = req.getRequestURI();
		if (reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		//System.out.println("reqURI >>> " + reqURI);
		Command handler = null;
		if("/".equals(reqURI)) {
			handler = commandMap.get("/index.do");
		} else {
			handler = commandMap.get(reqURI);
		}
		
		String viewName = null;
		try {
			viewName = handler.process(req, resp);
		} catch (Throwable e) {
			if (handler == null) {
				viewName = "404";
			}
		}
		//System.out.println("viewName >>> " + viewName);
		if (viewName != null) {
			String prefix = getInitParameter("prefix");
			String suffix = getInitParameter("suffix");
			RequestDispatcher dispatcher = req.getRequestDispatcher(prefix + viewName + suffix);
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
}