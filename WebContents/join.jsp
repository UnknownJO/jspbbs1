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
	<form action="joinAction" method="POST"> 
	<table>
		<caption id="cp1"><h1>회원가입</h1></caption>
		<tr>
			<td>아이디:</td>
			<td><input type="text" name="uid"></td>
		</tr>
		<tr>
			<td>비밀번호:</td>
			<td><input type="password" name="pwd1"></td>
		</tr>
		<tr>
			<td>비번확인:</td>
			<td><input type="password" name="pwd2"></td>
			<td>${result}</td>
		</tr>
		<tr>
			<td>이름:</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>이메일:</td>
			<td><input type="email" name="email"></td>
		</tr>
		<tr>
			<td>전화번호:</td>
			<td><input type="number" name="tel"></td>
		</tr>
		<tr id='bttn'>
			<td rowspan="2"><input type="submit" value="가입" ></td>
			<td rowspan="2"><input type="button" onclick="location.href='login.jsp'" value="취소" style=""></td>
		</tr>
	</table>
	</form>
	</div>
	<footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</body>
</html>