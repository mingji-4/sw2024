package org.comstudy.myapp.phonebook.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhoneSearchCommandImpl implements PhoneCommand {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// list.jsp 페이지로 forward
		String viewName = "/WEB-INF/views/search.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}