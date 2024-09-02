package org.comstudy.myapp.phonebook.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PhoneCommand {
	void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}