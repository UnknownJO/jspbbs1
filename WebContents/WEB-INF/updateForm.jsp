<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/viewcss.css">
<style>
/* side menu */

  /* 게시판 글쓰기 CSS*/ 

   /* -----------------------------------------------------------------------------------------*/   
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

</script>
</head>
<body>


	<!-- side menu -->
	<div id="div1">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><hr></li>
			<li><a href="#JAVA" class="menu" id='JAVA'>· JAVA</a></li>
			<li><a href="#JAVASCRIPT" class="menu" id='JAVASCRIPT'>·
					JAVASCRIPT</a></li>
			<li><a href="#JSP" class="menu" id='JSP'>· JSP</a></li>
			<li><a href="#SERVLET" class="menu" id='SERVLET'>· SERVLET</a></li>
			<li><a href="#AJAX" class="menu" id='AJAX'>· AJAX</a></li>
			<li><a href="#JSON" class="menu" id='JSON'>· JSON</a></li>
		</ul>
	</div>

	<%-- viewList.menu_name은 현재 보고있는 페이지의 이름, menuList는 전체 메뉴목록 --%>
	<form id="form1" name="form1" method="POST" action="UpdateAction">
	<input type="hidden" name="con_num" value="${viewList.con_num}" />
		
		<table style="padding-top: 50px" align="60%" width="700" border="0" cellpadding="2">
			<tr>
				<td height=20 align=center bgcolor=#111827><font color=white>내용수정</font></td>
			</tr>
			<tr>
				<td bgcolor=white></td>
			</tr>
		</table>
		<table class="table2">
			<tr>
				<td>메뉴</td>

				<td><select name="menu_name">
						<c:forEach var="m" items="${ menuList }">
							<c:choose>
								<c:when test="${ m.menu_name eq viewList.menu_name }">
									<option selected>${ m.menu_name }</option>
								</c:when>
								<c:otherwise>
									<option>${ m.menu_name }</option>
								</c:otherwise>

							</c:choose>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" size=20 value=${ viewList.con_writer }></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"  size=60 value=${ viewList.con_title }></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" cols=85 rows=15 >${ viewList.con_content }</textarea></td>
			</tr>
		</table>
		<input type="submit" name="update" value="완료"> 
		<input type="button" name="delete" onclick="history.back()" value="취소"> 
	</form>
	
	<div></div>




</body>
<footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</html>