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
		// GET 방식일때 입력 창 보여준다.
		PrintWriter out = resp.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"UTF-8\">\r\n");
		out.write("<title>index.jsp</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("<h2>전화 번호 입력</h2>\r\n");
		out.write("<form action=\"input.do\" method=\"POST\">\r\n" + 
				"<table>\r\n" + 
				"<tr>\r\n" + 
				"<th>성명</th>\r\n" + 
				"<td><input type=\"text\" name=\"name\" value=\"김범준\"></td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<th>성명</th>\r\n" + 
				"<td><input type=\"text\" name=\"phone\" value=\"010-1234-5678\"></td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td colspan=\"2\"><input type=\"submit\" value=\"저장\"></td>\r\n" + 
				"</tr>\r\n" + 
				"</table>\r\n" + 
				"</form>");
		out.write("</body>\r\n");
		out.write("</html>");
		out.close();
		
	}

}