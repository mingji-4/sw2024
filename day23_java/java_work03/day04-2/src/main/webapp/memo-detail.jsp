<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Memo Detail</h1>
	<p>
		<strong>Title:</strong> ${memo.title}
	</p>
	<p>
		<strong>Content:</strong> ${memo.content}
	</p>
	<!-- HTML 폼에서는 method가 POST 또는 GET으로만 보낼 수 있다. -->
	<form action="${pageContext.request.contextPath}/memos/${memo.id}" method="post">
		<input type="hidden" name="id" value="${memo.id}" />
		Title: <input type="text" name="title" required><br>
		Content:
		<textarea name="content" required></textarea>
		<br> <input type="submit" value="Edit">
	</form>
</body>
</html>