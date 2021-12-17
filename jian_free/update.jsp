<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jian_free_domain.*"%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"  %>
<!DOCTYPE html>
<%request.setCharacterEncoding("UTF-8"); %>
<%	String id = (String)session.getAttribute("ID"); %>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Accessories</title>
   <link rel="stylesheet" href="resources/Accessories.css" type="text/css"></link>
</head>
<body>
	<mytag:menu_tag/>
    <br><br>
    <div>
	
		<form action="http://localhost:8080/jian_free/UserServlet?cmd=update" method="post">
	
		<%
			UserVO user = (UserVO)request.getAttribute("user");
		%>
		<fieldset style = "width:429px; margin:0 auto;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">Information Update</legend>
			<ul style="margin-top:20px;">
			<li style="margin-top:10px;">ID : <input type="text" name="userId" value=<%=user.getUserId()%> readonly style="width:100%; height:47px;"></li>
			<li style="margin-top:10px;">PASSWD : <input type="password" name="userPasswd" value=<%=user.getUserPasswd()%> autofocus style="width:100%; height:47px;"></li>
			<li style="margin-top:10px;">USERNAME : <input type="text" name="userName" value=<%=user.getUserName()%> style="width:100%; height:47px;"></li>
			<li style="margin-top:10px;">MOBILE : <input type="text" name="userMobile" value=<%=user.getUserMobile()%> style="width:100%; height:47px;"></li>
			<li style="margin-top:10px;">EMAIL : <input type="text" name="userEmail" value=<%=user.getUserEmail() %> style="width:100%; height:47px;"></li>
			</ul>
			</fieldset>
			<br>
			<fieldset style = "width:429px; margin:0 auto;">
				<input type = "submit" name = "submit" value = "수정 완료"> 
				<input type = "reset" name = "reset" value = "다시 작성">
			</fieldset>
		</form>
	</div>
</body>
</html>