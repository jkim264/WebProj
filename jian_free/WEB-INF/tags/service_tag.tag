<%@ tag language="java" body-content="empty" pageEncoding="UTF-8" import = "jian_free_persistence.*, java.util.*, jian_free_domain.*"%>

<fieldset style = "margin: 0;padding: 0;">
				<legend style="margin:50px 0px 0px 0px;padding-bottom:5px;border-bottom:solid 2px #ccc;
				font-size:20px; font-weight:bold; float:center;" >문의 관리</legend>
	<table>
	
		<tr><th>글번호</th><th>이름</th><th>이메일</th><th>문의 내용</th><th>작성일</th><th>관리</th></tr>
		
		<%
			request.setCharacterEncoding("UTF-8");
			int pageNumber = 1;
			ServiceDAO dao = new ServiceDAO();
			if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			}
			ArrayList<ServiceVO> servicelist = (ArrayList<ServiceVO>)dao.getServiceList(pageNumber);
			request.setAttribute("servicelist", servicelist);
			for(int i = 0; i < servicelist.size(); i++) {
		%>
			<tr>
				<td><%=servicelist.get(i).getSnum() %></td>
				<td><%=servicelist.get(i).getSname() %></td>
				<td><%=servicelist.get(i).getSemail() %></td>
				<td><%=servicelist.get(i).getScontent() %></td>
				<td><%=servicelist.get(i).getSdate()%></td>
				<td><form action="http://localhost:8080/jian_free/ServiceServlet?cmd=delete&snum=<%=servicelist.get(i).getSnum()%>" method="post">
				<input type = "submit" name = "submit" value = "삭제하기">
			</form>	
				 </td>
			</tr>
			<% } %>
		</table>
		</fieldset>
		<% if(pageNumber != 1) {%> <a href="http://localhost:8080/jian_free/ServiceServlet?cmd=list&pageNumber=<%=pageNumber - 1%>" target="_self">이전</a>>
		<%} if(dao.nextPage(pageNumber+1)) { %> <a href="http://localhost:8080/jian_free/ServiceServlet?cmd=list&pageNumber=<%=pageNumber + 1%>" target="_self">다음</a>>
		<%} %>