package org.comstudy.myapp.phonebook.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhoneSearchCommandImpl implements PhoneCommand {

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"UTF-8\">\r\n");
		out.write("<title>index.jsp</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("<h2>전화 번호 검색</h2>\r\n");
		out.write("<form action=\"list.do\">\r\n" + 
				"<input type=\"text\" name=\"name\">\r\n" + 
				"<input type=\"submit\" value=\"검색\">\r\n" + 
				"</form>");
		out.write("</body>\r\n");
		out.write("</html>");
		out.close();
	}

}