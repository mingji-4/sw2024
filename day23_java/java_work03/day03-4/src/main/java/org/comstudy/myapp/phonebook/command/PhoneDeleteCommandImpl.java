package org.comstudy.myapp.phonebook.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myapp.phonebook.model.Phone;
import org.comstudy.myapp.phonebook.model.PhoneDAO;

public class PhoneDeleteCommandImpl implements PhoneCommand {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 즉시 삭제 후 list 페이지로 이동
		ArrayList<Phone> list = PhoneDAO.phoneList;
		int no = Integer.parseInt(req.getParameter("no"));
		int idx = list.indexOf(new Phone(no));
		if (idx != -1) {
			list.remove(idx);
			System.out.println("삭제 완료!");
		}
		resp.sendRedirect("list.do");
	}
}