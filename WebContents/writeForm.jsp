<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/viewcss.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>

	<!-- side menu -->
	<div id="div1">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><hr></li>
			<li><a href="#JAVA" class="menu" id='JAVA'>· JAVA</a></li>
			<li><a href="#JAVASCRIPT" class="menu" id='JAVASCRIPT'>·JAVASCRIPT</a></li>
			<li><a href="#JSP" class="menu" id='JSP'>· JSP</a></li>
			<li><a href="#SERVLET" class="menu" id='SERVLET'>· SERVLET</a></li>
			<li><a href="#AJAX" class="menu" id='AJAX'>· AJAX</a></li>
			<li><a href="#JSON" class="menu" id='JSON'>· JSON</a></li>
		</ul>
	</div>

	<form name="form1" method="GET" action="WriteAction">
		<table style="padding-top: 50px" align="60%" width="700" border="0" cellpadding="2">
			<tr>
				<td height=20 align=center bgcolor=#111827><font color=white>새글작성</font></td>
			</tr>
			<tr>
				<td bgcolor=white></td>
			</tr>
		</table>
		<table class="table2">
			<tr>
				<td>메뉴</td>

				<td>
				<select name="menu_name">
					<option>JAVA</option>
					<option>JAVASCRIPT</option>
					<option>JSP</option>
					<option>SERVLET</option>
					<option>AJAX</option>
					<option>JSON</option>
				</select>
				</td>

			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" size="20" ></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" size="60" ></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" cols="85" rows="15" ></textarea></td>
			</tr>
		</table>
		<input type="submit" name="update" value="완료"> 
		<input type="button" onclick="history.back()" value="취소"> 
	</form>

</body>
<footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</html>