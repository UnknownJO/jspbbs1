<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/logincss.css">
<style>

</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
   $( function(){
	
   });
</script>
</head>
<body>
	<%
		
		
	%>
	<div id="div1">
	<form action="LoginAction" method="POST">
	<table>
		<caption id="cp1"><h1>나만의 필기노트</h1></caption>
		<tr>
			<td>아이디:</td>
			<td><input type="text" name="uid"></td>
			<td rowspan="2"><input type="submit" id="btn" value="로그인"></td>
		</tr>
		<tr>
			<td>비밀번호:</td>
			<td><input type="password" name="pwd"></td>
		</tr>
	</table>
	<div id="div2">
	<a href="" >아이디 찾기</a>
	<a href="" >비밀번호 찾기</a>
	<a href="join.jsp" >회원가입</a>
	
	</div>
	</form>
	</div>
	<div style="text-align: center;" >
		${ result }
	</div>
	<footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</body>
</html>