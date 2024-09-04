<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>메모 앱</h1>
<nav>
	<a href="memos">메모 리스트</a>
</nav>
<hr/>
<%
Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");
DataSource ds = (DataSource) envCtx.lookup("jdbc/MemoDB");

Connection conn = ds.getConnection();

System.out.println("conn >>> " + conn);
out.println("conn >>> " + conn);

conn.close();
%>
</body>
</html>