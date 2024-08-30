package org.comstudy.myweb.ch01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ch01/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET - LoginServlet");
		
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate(); // session의 내용을 초기화
		// 로그아웃 처리 후 login 페이지로 이동
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST - LoginServlet");
		
		// form에 입력 된 데이터를 parameter로 전달 받는다.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.printf("%s, %s", username, password);
		
		// 로그인 데이터 체크
		// 실제로는 DAO의 검색하는 쿼리문을 구현한다.
		if("user01".equals(username) && "12345".equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			// 로그인 처리가 끝나면 welcome 페이지로 이동
			response.sendRedirect("welcome.jsp");
		} else {
			// 로그인 실패하면 다시 login 페이지로 이동
			response.sendRedirect("login.jsp");
		}
		
	}
}