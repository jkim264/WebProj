<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "jian_free_domain.*"%>
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
			BoardVO vo = (BoardVO)request.getAttribute("Board");
		%>
		<form action="http://localhost:8080/jian_free/BoardServlet?cmd=update" method="post">
		<fieldset style = "margin: auto; align:right;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold;">게시글 수정</legend>
			<ul style="margin-top:20px;">
			<li style="margin-top:10px;">글 번호 : <input type="text" name="bnum" value=<%=vo.getBnum() %> required placeholder=<%=vo.getBnum() %> readonly style="width:100%; height:35px;"></li></ul>
			<ul style="margin-top:20px;">
			<li style="margin-top:10px;">제목 : <input type="text" name="btitle" value=<%=vo.getBtitle()%> autofocus required placeholder=<%=vo.getBtitle()%> style="width:100%; height:35px;"></li></ul>
			<ul style="margin-top:20px;">
			<li style="margin-top:10px;">작성자 : <input type="text" name="bwriter" value=<%=vo.getBwriter() %> readonly style="width:100%; height:35px;"></li></ul>
			<ul style="margin-top:20px;">
			<li style="margin-top:10px;">작성 내용 : <input type="text" name="bcontent" value=<%=vo.getBcontent() %> required placeholder=<%=vo.getBcontent() %>  style="width:100%; height:300px;"></li>
			</ul>
			</fieldset>
		<br>
		<fieldset style = "width:429px; margin: auto;">
			<input type="submit" name="submit" value="보내기">
			<input type="reset" name="reset" value="다시 작성">
		</fieldset>
		<button type = "button" onClick="history.go(-1)"> 이전 </button>
		</form>
	</div>
</body>
</html>