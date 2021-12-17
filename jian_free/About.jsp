<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
   <p id=about>안녕하세요. Accessories 홈페이지입니다. <br>
   이 사이트는 본인이 직접 만든 액세사리에 관해 올리는 커뮤니티 사이트 입니다. <br><br><br><br><br>
   
   사이트 이용시, 불편한 점은 Service Center에 남겨주시기 바랍니다.</p>
   
</body>
</html> 