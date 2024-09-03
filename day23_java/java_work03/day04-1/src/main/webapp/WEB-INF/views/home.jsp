<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Homepage</h1>
<p>Message: ${message }</p>
<p>Message: ${requestScope.message }</p>
<p>Message: <%=request.getAttribute("message") %></p>

</body>
</html>