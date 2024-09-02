<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>컴스터디 스쿨</h1>
<%
// application 내장 객체는 자동 생성 됨.
String servletDataFile = application.getInitParameter("ServletDataFile");
%>

<p>servletDataFile: <%=servletDataFile %></p>

</body>
</html>