<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "jian_free_domain.*,jian_free_persistence.*, java.util.List"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<html>
<% request.setCharacterEncoding("UTF-8"); %>
<%	String id = (String)session.getAttribute("ID"); %>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Accessories</title>
   <link rel="stylesheet" href="resources/Accessories.css" type="text/css"></link>
</head>
<body>
	<mytag:menu_tag/>
    <br><br>
    <div>
   		<%
   			BoardVO post = (BoardVO)request.getAttribute("Board");
		%>
		<fieldset style = "margin: auto; align:right;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">게시글 보기</legend>
			<ul>
				<li style="margin-top:10px;">글번호 : <%=post.getBnum() %></li></ul>
				<ul><li style="margin-top:10px;">제목 : <%=post.getBtitle() %></li></ul>
				<ul><li style="margin-top:10px;">작성자 : <%=post.getBwriter() %></li></ul>
				<ul><li style="margin-top:10px;">작성일 : <%=post.getBdate() %></li></ul>
				<ul><li style="margin-top:10px;">작성 내용 : <%=post.getBcontent() %></li>
			</ul>
			</fieldset>
		<br>
		<button type = "button" onClick="history.go(-1)"> 이전 </button>
		<% if(id != null) { 
			if (id.equals("admin") || id.equals(post.getBwriter())) { %>
			<form action="http://localhost:8080/jian_free/BoardServlet?cmd=update_move&bnum=<%=post.getBnum()%>" method="post"  style="margin-top:20px;">
			<input type = "submit" name = "submit" value = "수정하기"></form> 
			<form action="http://localhost:8080/jian_free/BoardServlet?cmd=delete&bnum=<%=post.getBnum()%>" method="post"  style="margin-top:20px;">
			<input type = "submit" name = "submit" value = "삭제하기"></form>
		<%}}%>
	</div>
</body>
</html>