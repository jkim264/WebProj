<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>
<% String id = (String)session.getAttribute("ID");%>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Accessories</title>
   <link rel="stylesheet" href="resources/Accessories.css" type="text/css"></link>
</head>
<body>
	<mytag:menu_tag/>
    <br><br>
    <img src="./resources/service.JPG" width="430 px" heigh= "260px" float="left" align=left>
    <div align=right>
		<form action="http://localhost:8080/jian_free/ServiceServlet?cmd=write" method="post">
		<fieldset style = "width:429px; align:right; left-margin: 40px; margin: auto; ">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">문의 내용</legend>
			<ul style="margin-top:20px;">
				<li style="margin-top:10px;">이름 : <input type="text" name="sname" autofocus required placeholder="이름" style="width:100%; height:47px;"></li>
				<li style="margin-top:10px;">이메일 : <input type="text" name="semail" required placeholder="test@****.***" style="width:100%; height:47px;"></li>
				<li style="margin-top:10px;">문의 내용 : <input type="text" name="scontent" required placeholder="문의 내용" style="width:100%; height:100px;"></li>
			</ul>
			</fieldset>
		<br>
		<fieldset style = "width:429px; align:right; left-margin: 40px; margin: auto; ">
			<input type="submit" name="submit" value="문의 작성">
			<input type="reset" name="reset" value="다시 작성">
			<% if(id!=null && id.equals("admin")) { %><button type = "button" onClick="location.href='ServiceList.jsp'">문의 목록 보기</button><br> 
		<%}%>
		</fieldset>
		</form>	
	</div>
	
</body>
</html>