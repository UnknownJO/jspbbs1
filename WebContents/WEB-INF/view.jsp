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


	
   /* -----------------------------------------------------------------------------------------*/   
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>


	$(function() {
		//버튼에 서브밋은 없고 그냥 버튼 두개다.
		$('input[type="button"]').on('click', function(e) { // 버튼들에 클릭이 발생하면 함수 실행.
			console.log(e);
			var btn = e.target; //우선 누른버튼의 정보를 가져와서
			var form = document.getElementById('form1'); // form태그의 정보도 가져오고
			var action = null;
			switch (btn.name) { // 누른것이 어느 버튼인지 판별해서
			case "update":
				action = 'UpdateView'; // 액션을 진행한다.
				break;
				
			case "delete":
				action = 'DeleteAction'; 
			}
			form.action = action; //form태그의 액션을 수행하고
			form.submit(); // 폼태그의 서브밋을 수행한다.
		});

	});
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
	<form id="form1" name="form1" method="GET">
		<input type="hidden" name="con_num" value="${viewList.con_num}" /> 
		<input type="hidden" name="menu_name" value="${viewList.menu_name}" /> 
		<input type="hidden" name="menuList" value="${menuList}" />

		<table style="padding-top: 50px" align="60%" width="700" border="0" cellpadding="2">
			<tr>
				<td height=20 align=center bgcolor=#111827><font color=white>내용보기</font></td>
			</tr>
			<tr>
				<td bgcolor=white></td>
			</tr>
		</table>
		<table class="table2">
			<tr>
				<td>메뉴</td>

				<td><select>
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
				<td><input type="text" name="writer" size=20
					readonly="readonly" value=${ viewList.con_writer }></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" readonly="readonly" size=60
					value=${ viewList.con_title }></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" cols=85 rows=15
						readonly="readonly">${ viewList.con_content }</textarea></td>
			</tr>
		</table>
		<input type="button" name="update" value="수정"> 
		<input type="button" name="delete" value="삭제"> 
	</form>
	
	<div></div>




</body>
<footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</html>