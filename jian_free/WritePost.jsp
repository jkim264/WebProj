<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "jian_free_domain.*,jian_free_persistence.*, java.util.List"%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"  %>
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
   			UserDAO dao = new UserDAO();
   			UserVO user = dao.read(id);
			
		%>
		<form action="http://localhost:8080/jian_free/BoardServlet?cmd=write" method="post">
		<fieldset style = "margin: auto; align:right;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">게시글 작성</legend>
			<ul style="margin-top:20px; ">
				<li style="margin-top:10px;">제목 : <input type="text" name="btitle" autofocus required placeholder="이름" style="width:100%; height:35px;"></li></ul>
				<ul style="margin-top:20px; ">
				<li style="margin-top:10px;">작성자 : <input type="text" name="bwriter" value=<%=user.getUserName() %> readonly style="width:100%; height:35px;"></li></ul>
				<ul style="margin-top:20px; ">
				<li style="margin-top:10px;">작성 내용 : <input type="text" name="bcontent" required placeholder="문의 내용" style="width:100%; height:300px;"></li>
			</ul>
			</fieldset>
		<br>
		<fieldset style = "width:429px; margin: auto;">
			<input type="submit" name="submit" value="보내기">
			<input type="reset" name="reset" value="다시 작성">
		</fieldset>
		<button type = "button" onClick="location.href='Sale.jsp'"> 이전 </button>
		</form>
	</div>
</body>
</html>