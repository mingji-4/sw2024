package org.comstudy.myweb;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   String views = "";
   
   @Override
   public void init() throws ServletException {
      views = this.getInitParameter("views");
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      resp.setCharacterEncoding("UTF-8");
      PrintWriter out = resp.getWriter();
      
      ServletContext application = req.getServletContext();
      String servletDataFile = application.getInitParameter("ServletDataFile");
      out.write("servletDataFile: " + servletDataFile + "<br/>");
      out.write("views: " + views);
      
      System.out.println(views + servletDataFile);
      InputStream is = this.getClass().getResourceAsStream(servletDataFile);
      
      @SuppressWarnings("resource")
	  Scanner fileScanner = new Scanner(is);
      while(fileScanner.hasNext()) {
         System.out.println(fileScanner.nextLine());
      }
      
      out.close();
   }
}