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
		$.ajax({
			url:'Main',
			type:'POST',
			datatype:'JSON',
			async:'true',
			success:function(data){
				var obj = JSON.parse(data);
				var array = obj.UserList;
				var html  = '<h1>사용자 목록</h1>'; 
				    html  += '<input type="text" id="searchword">'; 
				    html  += '<button id="searchbtn">검색</button>';
				    html  += '<table id="customers">';
					html  += '<tr>';
					html  += '<th>아이디</th>';
					html  += '<th>이름</th>';
					html  += '<th>이메일</th>';
					html  += '<th>전화번호</th>';
					html  += '</tr>';
					
					for (var i = 0; i < array.length; i++) {
						var el = array[i];
					html += '<tr>';
					html += '<td>' + el.id    + '</td>';
					html += '<td>' + el.name  + '</td>';
					html += '<td>' + el.email + '</td>';
					html += '<td>' + el.tel   + '</td>';
					html += '</tr>'
					}   
					html += '</table>';
					$('#div3').html(html);
					
				}	    
		});
		
	// 메뉴버튼(게시판 내용)

	$('.menu').on('click', function(e){
		var menu = e.target;
		$.ajax({
			url : 'conbbs',
			type: 'POST',
			data : {"whichmenu": menu.id},
			datatype:'JSON',
			async:'true',
			success:function(data){
				var obj = JSON.parse(data);
				var array = obj.contents;
				var html  = '<h1 id="mm">' + menu.id + '</h1>';
					html  += '<input type="text" id="searchword">';  
					html  += '<button id="searchbtn">검색</button>';
				    html  += '<table id="customers">';
					html  += '<tr>';
					html  += '<th>글번호</th>';
					html  += '<th>제목</th>';
					html  += '<th>글쓴이</th>';
					html  += '<th>시간</th>';
					html  += '<th>조회수</th>';
					html  += '</tr>';
					
					for (var i = 0; i < array.length; i++) {
						var el = array[i];
					html += '<form>';
					html += '<tr>';
					html += '<td>' + el.row_num        + '</td>';
					html += '<td>';
					html += '<a href="viewcon?con_num='+el.con_num+'&con_title='+el.con_title+'&con_writer='+el.con_writer+'&con_writingdate='+el.con_writingdate+'">'+el.con_title+'</a>';  
					html += '</td>';
					html += '<td>' + el.con_writer      + '</td>';
					html += '<td>' + el.con_writingdate + '</td>';
					html += '<td>' + el.con_hitcount    + '</td>';
					html += '</tr>';
					}   
					html += '</table>';
					html += '<button id="writebtn">글쓰기</button>';
					$('#div3').html(html);
					
					var startNum = ${pageNum} - (${pageNum}-1)%5;
					tag = '' ;
					 if(startNum != 1){
					tag += '<a href="PagingCon?pageNum='+(startNum-i-1)+'&menu_name='+menu.id+'">이전</a>';
					}
					for(var i=0; i<5 ;i++){
					tag += '<a href="PagingCon?pageNum='+(startNum+i)+'&menu_name='+menu.id+'">'+(startNum+i)+'&nbsp</a>';
					}	
					tag += '<a href="PagingCon?pageNum='+(startNum+i+1)+'&menu_name='+menu.id+'">다음</a>';
					$('#div4').html(tag);
			}
		});
	});
	
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
		 location.href='writeForm.jsp';
	});
	

});	

</script>
</head>
<body>
	<% String username = (String)session.getAttribute("username"); %>
	<!-- side menu -->
	<div id="div1">
			<h4 style="text-align: center"><%=username %>님 환영합니다.</h4>
			<c:if test="${username != null}">
			<a href="logout.jsp">로그아웃</a>
			</c:if>
		<ul>
			<li><a href="index.jsp" >Home</a></li>
			<li><hr></li>
			<li><a href="#JAVA" class="menu" id='JAVA'>· JAVA</a></li>
			<li><a href="#JAVASCRIPT" class="menu" id='JAVASCRIPT'>· JAVASCRIPT</a></li>
			<li><a href="#JSP" class="menu" id='JSP'>· JSP</a></li>
			<li><a href="#SERVLET" class="menu" id='SERVLET'>· SERVLET</a></li>
			<li><a href="#AJAX" class="menu" id='AJAX'>· AJAX</a></li>
			<li><a href="#JSON" class="menu" id='JSON'>· JSON</a></li>
		</ul>
	</div>
	<!--table-->
	
	<div id="div3"></div>
	
	<div id="div4"></div>

  <footer>Copyright © 2021-2025 Jo, All Rights Reserved.</footer>
</body>
</html>