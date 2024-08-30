<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>할 일 입력</h1>
	<a href="input">할 일 입력</a>
	<a href="detail">할 일 상세보기</a>
	<a href="modify">할 일 수정</a>
	<a href="delete">할 일 삭제</a>
	<!-- 입력 폼 들어가는 곳 -->
	<form action="input" method="post">
	할일: <input type="text" name="title" value="아무거나 할 일"/> <br/>
	완료: <input type="text" name="done" value="false"/><br/>
	<input type="submit" value="저장">
	</form>
</body>
</html>