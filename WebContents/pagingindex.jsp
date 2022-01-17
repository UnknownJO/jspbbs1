<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/indexcss.css">
<style>

   /* -----------------------------------------------------------------------------------------*/   
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	$(function(){
		
		
	// 메뉴버튼(게시판 내용)

	
	
	//검색 버튼
	$('body').on('click','#searchbtn', function() {
		var searchword = $('#searchword').val();
		$.ajax({
			url:'searchbbs',
			type:'POST',
			data:{"searchword":searchword},
			datatype:'JSON',
			async:true,
			success:function(data){
				var obj = JSON.parse(data);
				var array = obj.contents;
				var html  = '<h1>검색결과</h1>';
					html  += '<input type="text" id="searchword">';  
					html  += '<button id="searchbtn">검색</button>';
				    html  += '<table id="customers">';
					html  += '<tr>';
					html  += '<th>순서</th>';
					html  += '<th>게시판</th>';
					html  += '<th>제목</th>';
					html  += '<th>글쓴이</th>';
					html  += '<th>시간</th>';
					html  += '<th>조회수</th>';
					html  += '</tr>';
					
					for (var i = 0; i < array.length; i++) {
						var el = array[i];
					html += '<tr>';
					html += '<td>' + el.row_num         + '</td>';
					html += '<td>' + el.menu_name    + '</td>';
					html += '<td>' + el.con_title       + '</td>';
					html += '<td>' + el.con_writer      + '</td>';
					html += '<td>' + el.con_writingdate + '</td>';
					html += '<td>' + el.con_hitcount    + '</td>';
					html += '</tr>';
					}   
					html += '</table>';
					$('#div3').html(html);
			} 
		});
		
	});
		
	//글쓰기 버튼
	$('body').on('click','#writebtn', function() {
		 location.href='writeform.jsp';
	});
	

});	

</script>
</head>
<body>
	<%--<% String username = (String)session.getAttribute("username"); --%>
	<!-- side menu -->
	<div id="div1">
			<h4 style="text-align: center">${ username }님 환영합니다.</h4> 
			<c:if test="${username != null}">
			<a href="logout.jsp">로그아웃</a>
			</c:if>
		<ul>
			<li><a href="index.jsp" >Home</a></li>
			<li><hr></li>
			<li><a href="PagingCon2?menu_name=JAVA" class="menu" id='JAVA'>· JAVA</a></li>
			<li><a href="PagingCon2?menu_name=JAVASCRIPT" class="menu" id='JAVASCRIPT'>· JAVASCRIPT</a></li>
			<li><a href="PagingCon2?menu_name=JSP" class="menu" id='JSP'>· JSP</a></li>
			<li><a href="PagingCon2?menu_name=SERVLET" class="menu" id='SERVLET'>· SERVLET</a></li>
			<li><a href="PagingCon2?menu_name=AJAX" class="menu" id='AJAX'>· AJAX</a></li>
			<li><a href="PagingCon2?menu_name=JSON" class="menu" id='JSON'>· JSON</a></li>
		</ul>
	</div>
	
<!-- ----------------------------------------------------------------------------------------------- -->
	
	<div id="div3">
	<h1 id="mm">${ menu_name }</h1>
	<input type="text" id="searchword">
	<button id="searchbtn">검색</button>
	<table id="customers">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>시간</th>
			<th>조회수</th>
		</tr>
		<c:forEach var='abc' items='${ pageCon }'>
		<tr>
			<td>${ abc.row_num }</td>
			<td><a href="viewcon?con_num=${ abc.con_num }&con_title=${ abc.con_title }
			&con_writer=${ abc.con_writer }&con_writingdate=${ abc.con_writingdate }">${ abc.con_title }</a>
			<td>${ abc.con_writer }</td>
			<td>${ abc.con_writingdate }</td>
			<td>${ abc.con_hitcount}</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<%-- --------------페이징 처리------------------------------------------------------------------------ --%>
	<div id="div4">
		<%-- 3. 클릭되거나 페이지 로딩하면 클릭한 넘버값 스스로 가져와서 저장소에 저장. --%>
		<%-- view에 null값이 올것을 대비해서 삼항연산자로 임시저장소에 값을 넣는다. --%>
		<c:set var="pageNum" value="${ (param.pageNum == null)?1:param.pageNum}" />   
		<%-- 4. 클릭해서 파라미터로 날라온 페이지숫자로 스타트넘버를 구해서 해당 페이지업이 출력되는 것. --%>
		<%-- 5. 그렇게 클릭한 페이지가 몇이냐에 따라 링크도 바뀌고 페이지업도 바뀌게 된다. --%>
		<c:set var="startNum" value="${ pageNum -(pageNum-1)%5 }" />
		<c:set var="lastNum" value="23" />

	<%-- -------------------------------------------------------------------------------------- --%>
	<div> 
		<c:if test="${startNum > 1}">
			<a class="btn-next" href="?pageNum=${ startNum -1 }&menu_name=${menu_name}">이전5페이지</a>
		</c:if>
	
	<%------------------------------------------------------------------------------------------ --%>	
	
		<%-- 1. 페이지숫자 5개를 반복해서 출력함.. --%>
		<c:forEach var="i" begin="0" end="4"> 
		<%-- 2. 출력돼있는 숫자중 하나를 클릭하면 p에 담겨서 파라미터로 보내짐. --%>
		<%-- startNum은 있을수도 없을수도 있는데 없어도 페이지는 찍힌다.
             값이 없다면 1~5페이지가 찍히고 , 있으면 그 페이지넘버로 스타트페이지가 
             구해지고 해당 페이지업넘버들이 찍힌다. --%>                                             
			<a class="-text- red" style="color: green" href="PagingCon?pageNum=${ startNum +i }&menu_name=${menu_name}">${startNum +i}</a>
		</c:forEach>
	
	<%------------------------------------------------------------------------------------------ --%>	
	 
		<a class="btn-next" href="PagingCon?pageNum=${ startNum +5 }&menu_name=${menu_name}">다음5페이지</a> 
	</div>
	</div>
	<%-- --------------------------------------------------------------------------------------------- --%>
	<%-- --------------------------------------------------------------------------------------------- --%>
	<footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</body>
</html>