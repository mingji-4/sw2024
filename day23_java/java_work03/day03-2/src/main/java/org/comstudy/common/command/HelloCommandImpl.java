package org.comstudy.common.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloCommandImpl extends Command {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 처리 후 뷰네임 반환
		System.out.println("process() - HelloCommandImpl");
		return "hello";
	}

}