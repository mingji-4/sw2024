package org.comstudy.myweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/app")
public class App extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      res.setCharacterEncoding("UTF-8");
      res.setContentType("text/html; charset=UTF-8");
	  // res.getWriter().append("Served at: ").append(req.getContextPath());
	  // PrintWriter 객체를 이용해서 화면에 HTML 출력
	  PrintWriter out = res.getWriter();
	  out.println("<!DOCTYPE html><html><head>\n"
	  		+ "<meta charset=\"UTF-8\">\n"
	  		+ "<titleApp 서블렛 예제</title>\n"
	  		+ "</head><body>");
	  out.println("<h1>Hello Servlet World</h1>");
	  out.println("<a href=index.jsp>index 페이지로 이동</a>");
	  out.println("</body></html>");
	  out.close();
;   } // end of doGet

   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      doGet(req, res);
   }
}
