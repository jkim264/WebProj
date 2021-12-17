<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "jian_free_domain.*, java.util.List"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<%	String id = (String)session.getAttribute("ID"); %>
<html>
<%request.setCharacterEncoding("UTF-8");%>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Accessories</title>
   <link rel="stylesheet" href="resources/Accessories.css" type="text/css"></link>
</head>
<body>
	<mytag:menu_tag/>
    <br><br>
	<p id=sect><%=request.getAttribute("message") %><br>
		<%
			UserVO user = (UserVO)request.getAttribute("user");
		%>
		
		<fieldset style = "width:429px; margin:0 auto;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">계정 정보</legend>
				<ul style="margin-top:20px;">
				<li style="margin-top:10px; width:100%;">ID : <%=user.getUserId() %></li>
				<li style="margin-top:10px; width:100%;">PASSWORD : <%=user.getUserPasswd() %></li>
			</ul>
			<% if(id == null) {%><a href="register.html" target="_self"> 회원가입하기 </a>
			<%} else if(user.getUserId().equals(id)) { %><a href="http://localhost:8080/jian_free/UserServlet?cmd=update&userId=<%=user.getUserId()%>"target="_self">회원정보 수정하기</a>
			<form action="http://localhost:8080/jian_free/UserServlet?cmd=delete&userId=<%=user.getUserId()%>" method="post">
			<input type = "submit" name = "submit" value = "탈퇴하기"> <%} %>
			<% if (user.getUserId().equals("admin")) %><a href="http://localhost:8080/jian_free/UserServlet?cmd=list" target="_self"> 전체 회원 관리하기 </a>
			</form>	
			
			</fieldset>
	<br><br>
</body>
</html>