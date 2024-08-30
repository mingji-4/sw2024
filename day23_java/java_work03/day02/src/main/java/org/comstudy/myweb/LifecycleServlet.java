package org.comstudy.myweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어노테이션을 이용해서 URL매핑 하는 대신 web.xml에 URL매핑한다.
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = -2428974764302822321L;

	public LifecycleServlet() {
		System.out.println("1. 생성자() - LifecycleServlet");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("2. init() - LifecycleServlet");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("3. service() - LifecycleServlet");
	}

	@Override
	public void destroy() {
		System.out.println("4. destroy() - LifecycleServlet");
	}
}