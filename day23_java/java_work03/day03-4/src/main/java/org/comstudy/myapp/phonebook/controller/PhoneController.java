package org.comstudy.myapp.phonebook.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myapp.phonebook.command.PhoneCommand;
import org.comstudy.myapp.phonebook.command.PhoneDeleteCommandImpl;
import org.comstudy.myapp.phonebook.command.PhoneInputCommandImpl;
import org.comstudy.myapp.phonebook.command.PhoneListCommandImpl;
import org.comstudy.myapp.phonebook.command.PhoneModifyCommandImpl;
import org.comstudy.myapp.phonebook.command.PhoneSearchCommandImpl;

@WebServlet("/phone/*")
public class PhoneController extends HttpServlet {
	static Map<String, PhoneCommand> cmdMap = new HashMap<String, PhoneCommand>();
	static {
		cmdMap.put("/list.do", new PhoneListCommandImpl());
		cmdMap.put("/input.do", new PhoneInputCommandImpl());
		cmdMap.put("/search.do", new PhoneSearchCommandImpl());
		cmdMap.put("/delete.do", new PhoneDeleteCommandImpl());
		cmdMap.put("/modify.do", new PhoneModifyCommandImpl());
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String METHOD = req.getMethod();
		System.out.println(METHOD + " - doProcess() - PhoneController");
		
		String path = req.getRequestURI().substring((req.getContextPath()+"phone/").length() );
		System.out.println(path);
		
		cmdMap.get(path).process(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
}