<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>데이터 바인딩 결과 페이지</h1>
<%
   if(request.getAttribute("name")!= null){
      String name = (String)request.getAttribute("name");
      out.println("바인딩 된 name은 " + name + "<br/>");
   }
   if(session.getAttribute("email")!= null){
      String email = (String)session.getAttribute("email");
      out.println("바인딩 된 email은 " + email + "<br/>");
   }
%>

<a href="input.jsp">input 페이지로 이동</a>
   
</body>
</html>