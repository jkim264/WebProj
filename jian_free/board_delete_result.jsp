<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "jian_free_domain.*"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
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
	<p id=sect>${message}<br> <!-- 표현언어 사용 -->
	<button type="button" onClick="location.href='Sale.jsp'">목록 보기</button>
	
	</p>
	</body>
	</html>