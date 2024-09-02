package org.comstudy.myapp.phonebook.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myapp.phonebook.model.Phone;
import org.comstudy.myapp.phonebook.model.PhoneDAO;

public class PhoneModifyCommandImpl implements PhoneCommand {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Phone> list = PhoneDAO.phoneList;
		int no = Integer.parseInt(req.getParameter("no"));
		int idx = list.indexOf(new Phone(no));
		if("POST".equals(req.getMethod())) {
			//  수정 처리 후 리스트로 이동
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			PhoneDAO.phoneList.get(idx).setName(name); 
			PhoneDAO.phoneList.get(idx).setPhone(phone); 
			resp.sendRedirect("list.do");
			return;
		}
		Phone phone = list.get(idx);
		// list 데이터 바인딩
		req.setAttribute("phone", phone);
		
		// list.jsp 페이지로 forward
		String viewName = "/WEB-INF/views/modify.jsp";
		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}

}