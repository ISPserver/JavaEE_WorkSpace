<%@page import="com.model2.domain.Board"%>
<%@page import="common.board.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%List<Board> list = (List)request.getAttribute("boardList");

	 Pager pager = new Pager();
	 pager.init(request, list);	 
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td>No</td>
			<td>제목</td>
			<td>작성자</td>
			<td>내용</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<%int num = pager.getNum(); %>
		<%int curPos = pager.getCurPos(); %>
		<%for(int i=1; i<=pager.getPageSize(); i++){ %>
		<%if(num<1) break; %>
		<%if(pager.getNum()<1) break; %>
		<%Board board = list.get(curPos++); %>
		<tr>
			<th><%=num-- %></th>
			<th><a href="/board/detail?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a>[<%=board.getCnt() %>]</th>
			<th><%=board.getWriter() %></th>
			<th><%=board.getContent() %></th>
			<th><%=board.getRegdate().substring(0,10) %></th>
			<th><%=board.getHit() %></th>
		</tr>
		<%} %>
		<tr>
			<td colspan="6" style="text-align:center">
				<a href="list.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				<%for(int i=pager.getFirstPage(); i<pager.getLastPage(); i++){ %>
				<%if(i>pager.getTotalPage()) break; %>
				[<%=i %>]				
				<%} %>
				<a href="list.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<button onClick="location.href='regist_form.jsp'">글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>