<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>
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
    	<% if(id != null) { %><button type = "button" onClick="location.href='WritePost.jsp'">글쓰기</button><br> 
		<%}%>
		<mytag:sales_tag/>
			
	<br><br>
</body>
</html>