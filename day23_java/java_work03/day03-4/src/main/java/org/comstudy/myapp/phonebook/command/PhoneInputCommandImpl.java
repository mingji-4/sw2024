package org.comstudy.myapp.phonebook.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myapp.phonebook.model.Phone;
import org.comstudy.myapp.phonebook.model.PhoneDAO;

public class PhoneInputCommandImpl implements PhoneCommand {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if("POST".equals( req.getMethod()) ) {
			// 저장 후 리스트로 이동
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			PhoneDAO.phoneList.add(new Phone(PhoneDAO.phoneSeq++, name, phone));
			resp.sendRedirect("list.do");
			return;
		}
		
		// list.jsp 페이지로 forward
		String viewName = "/WEB-INF/views/input.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

}