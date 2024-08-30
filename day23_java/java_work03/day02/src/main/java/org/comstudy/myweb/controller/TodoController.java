package org.comstudy.myweb.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myweb.model.TodoVo;

@WebServlet("/todo/*")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<TodoVo> todoList = new ArrayList<TodoVo>();
	static {		
		todoList.add(new TodoVo(101, "새 할일", false));
		todoList.add(new TodoVo(102, "새 할일2", true));
		todoList.add(new TodoVo(103, "새 할일3", false));
	}
	private static int noCnt = 104;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// reqPath
		// /todo/* 분기 하는 작업
		System.out.println("doGet() - TodoController");
		
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		int beginIndex = ctxPath.length();
		String path = reqUri.substring(beginIndex);
		
		//System.out.println("reqUri >>> " + reqUri);
		//System.out.println("ctxPath >>> " + ctxPath);
		System.out.println("path >>> " + path);
		
		String viewName = "/WEB-INF/views/TodoList.jsp";
		if("/todo/input".equals(path)) {
			viewName = "/WEB-INF/views/TodoInput.jsp";
		} else if("/todo/detail".equals(path)) {
			viewName = "/WEB-INF/views/TodoDetail.jsp";
		} else if("/todo/modify".equals(path)) {
			viewName = "/WEB-INF/views/TodoModify.jsp";
		} else if("/todo/delete".equals(path)) {
			viewName = "/WEB-INF/views/TodoDelete.jsp";
		} else {
			viewName = "/WEB-INF/views/TodoList.jsp";
			req.setAttribute("todoList", todoList);
		}
		
		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		System.out.println("doPost() - TodoController");
		
		// 파라미터 받아 오기
		String title = req.getParameter("title");
		boolean done = Boolean.parseBoolean(req.getParameter("done") );
		todoList.add(new TodoVo(noCnt++, title, done) );
		resp.sendRedirect("list");
	}
}