<%@ tag language="java" body-content="empty" pageEncoding="UTF-8" import = "jian_free_persistence.*, java.util.List, jian_free_domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fieldset style = "margin: 0;padding: 0;">
	<table >
	
		<tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>게시일</th></tr>
		
		<%
			request.setCharacterEncoding("UTF-8");
			int pageNumber = 1;
			BoardDAO dao = new BoardDAO();
			if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			}
			List<BoardVO> boardlist = dao.getBoardList(pageNumber);
			request.setAttribute("boardlist", boardlist);
			
			for(int i = 0; i < boardlist.size(); i++) {
		%>
			<tr>
				<td><%=boardlist.get(i).getBnum() %></td>
				<td><a href="http://localhost:8080/jian_free/BoardServlet?cmd=read&bnum=<%=boardlist.get(i).getBnum()%>"target="_self"><%=boardlist.get(i).getBtitle() %></a>
				<td><%=boardlist.get(i).getBwriter() %></td>
				<td><%=boardlist.get(i).getBdate() %></td>
			</tr>
			<% } %>
		</table>
		</fieldset>
		<% if(pageNumber != 1) {%> <a href="http://localhost:8080/jian_free/BoardServlet?cmd=list&pageNumber=<%=pageNumber - 1%>" target="_self">이전</a>>
		<%} if(dao.nextPage(pageNumber+1)) { %> <a href="http://localhost:8080/jian_free/BoardServlet?cmd=list&pageNumber=<%=pageNumber + 1%>" target="_self">다음</a>>
		<%} %>
		<c:if test="${message != '게시글 작성 성공'}">${message}</c:if>