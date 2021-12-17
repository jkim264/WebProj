<%@ tag language="java" body-content="empty" pageEncoding="UTF-8"%>
<%	String id = (String)session.getAttribute("ID"); %>
<H3><a href="Welcome.jsp"><img src="./resources/아이콘.png" style="height:200px;"></a></H3>
	<% if(id == null) { %><a href="Login.html"><img src="./resources/login.png" style="height:50px;float:right"></a> 
	<%} else { %> <a style="float:right;" href="http://localhost:8080/jian_free/UserServlet?cmd=update&userId=<%=id%>" target="_self"><%=id %>님, 정보수정</a><br><a href="http://localhost:8080/jian_free/UserServlet?cmd=logout&userId=<%=id%>"><img src="./resources/logout.png" style="height:50px;float:right"></a> <%} %>
	<br><br><br>
	<p style="float:right;">  이승기-정신이 나갔었나봐</p>
	
	<audio src="./resources/music.flac" autoplay controls loop ></audio>

	<br><br><br>
   <div id="topMenu">
   <ul style="list-style-type: none;margin: 0;padding: 0;background-color: #e9e9e9;">
      <li><a class="menuLink"  href="About.jsp">About</a></li>
      <li><a class="menuLink"  href="Sale.jsp">Post</a></li>
      <li><a class="menuLink"  href="ServiceCenter.jsp">Service Center</a></li>
    </ul>
    </div>