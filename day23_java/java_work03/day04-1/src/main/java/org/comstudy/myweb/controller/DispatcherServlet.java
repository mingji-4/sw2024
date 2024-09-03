package org.comstudy.myweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String prefix = "";
	String suffix = "";
	
	@Override
	public void init() throws ServletException {
		prefix = getInitParameter("prefix");
		suffix = getInitParameter("suffix");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET: doGet() - DispatcherServlet");
		
		// 메세지 바인딩
		req.setAttribute("message", "Hello Spring world");
		
		String viewName="home";
		RequestDispatcher view = req.getRequestDispatcher(prefix+ viewName + suffix);
		view.forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("name >>> " + name);
		
		resp.sendRedirect("home.do");
	}
}