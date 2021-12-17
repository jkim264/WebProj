<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "jian_free_domain.*, java.util.List"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<html>
<%request.setCharacterEncoding("UTF-8");%>
<%	String id = (String)session.getAttribute("ID"); %>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Accessories</title>
   <link rel="stylesheet" href="resources/Accessories.css" type="text/css"></link>
   
</head>
<body>
	<mytag:menu_tag/>
    <br><br>
	<p id=sect>${message}<br>
		<%
			UserVO user = (UserVO)request.getAttribute("user");
		%>
		
		<fieldset style = "width:500px; margin:0 auto;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">계정 리스트</legend>
		<table>
	
		<tr><th>계정</th><th>이름</th><th>전화번호</th><th>이메일</th></tr>
		
		<%
			List<UserVO> userList = (List<UserVO>)request.getAttribute("userlist");
			for(UserVO vo : userList) {
		%>
			<tr>
				<td><%=vo.getUserId() %></td>
				<td><%=vo.getUserName() %></td>
				<td><%=vo.getUserMobile() %></td>
				<td><%=vo.getUserEmail() %></td>
				<td><form action="http://localhost:8080/jian_free/UserServlet?cmd=delete&userId=<%=vo.getUserId()%>" method="post">
			<input type = "submit" name = "submit" value = "탈퇴하기"> 
			</form>	</td>
			</tr>
			<% } %>
		</table>
			</fieldset>
			
			
	<br><br>
</body>
</html>