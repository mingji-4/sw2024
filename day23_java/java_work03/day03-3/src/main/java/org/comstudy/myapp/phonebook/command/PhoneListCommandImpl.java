package org.comstudy.myapp.phonebook.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myapp.phonebook.model.Phone;
import org.comstudy.myapp.phonebook.model.PhoneDAO;


public class PhoneListCommandImpl implements PhoneCommand {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Phone> list = PhoneDAO.phoneList;
		String name = req.getParameter("name");
		if(name != null && !name.equals("")) {
			// 새 목록 생성
			System.out.println("name이 있다!");
			ArrayList<Phone> newList = new ArrayList<Phone>();
			for(Phone phone : PhoneDAO.phoneList) {
				if(phone.getName().indexOf(name) != -1) {
					newList.add(phone);
				}
			}
			list = newList;
		}
		
		PrintWriter out = resp.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"UTF-8\">\r\n");
		out.write("<title>index.jsp</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("<h2>전화 번호 목록</h2>\r\n");	
		out.write("<table width=100% border=1 >\r\n");
		for(Phone phone : list) {
			out.write(phone.toRow());
		}
		out.write("</table>\r\n");
		out.write("<a href=search.do>검색</a> | ");
		out.write("<a href=input.do>등록</a> | ");
		out.write("<a href=list.do>목록</a>");
		out.write("</body>\r\n");
		out.write("</html>");
		out.close();
	}

}