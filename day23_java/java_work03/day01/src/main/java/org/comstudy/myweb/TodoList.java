package org.comstudy.myweb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myweb.model.TodoVo;

@WebServlet("/todo/list")
public class TodoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TodoList.jsp페이지로 forward 하기
		// WEB-INF 내부는 외부에서 브라우저로 접근 할 수 없다. (내부 클래스에서 접근 하거나 forward 가능) 
		
		// TodoList.jsp 뷰페이지에서 사용할 리트스를 바인딩
		ArrayList<TodoVo> todoList = new ArrayList<TodoVo>();
    	todoList.add(new TodoVo(101, "새 할일111", false));
    	todoList.add(new TodoVo(102, "새 할일211", true));
    	todoList.add(new TodoVo(103, "새 할일311", false));
    	req.setAttribute("todoList", todoList);
		
		String path = "/WEB-INF/views/TodoList.jsp";
		RequestDispatcher view =  req.getRequestDispatcher(path);
		view.forward(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}