<%@page import="board.model.News"%>
<%@page import="java.util.List"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	NewsDAO dao = new NewsDAO();
	List<News> list = dao.selectAll();
	
	int totalRecord=list.size(); //총 레코드 수
	int pageSize = 10; //페이지당 보여질 레코드 수(개발자 정의)
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);//총 페이지 수
	int blockSize = 10;//블럭당 보여질 페이지 수(개발자 정의)
	int currentPage = 1;//현재 페이지(default는 1페이지)
	if(request.getParameter("currentPage")!=null){//파라미터로 페이지가 전달되면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage=currentPage-(currentPage-1)%blockSize;//첫 페이지1,11,21
	int lastPage=firstPage+blockSize-1;//마지막페이지10,20,30
	int curPos = (currentPage-1)*pageSize;//array 
	int num = totalRecord - curPos; //페이지당 시작번호
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
table{
	width:100%;
	border:1px solid #cccccc;
}
td{
	border:1px solid #cccccc;
}
a{text-decoration:none;}
.pageNum{
	font-size:15pt;
	color:blue;
	font-weight:bold;	
}
.inactive{
	color:#cccccc;
}

</style>
<script>
function showColor(obj){
	obj.style.background="dodgerblue";
}
function hideColor(obj){
	obj.style.background="";
}

</script>
</head>
<body>
	<table>
		<tr>
			<td width="5%">No</td>
			<td width="70%">제목</td>
			<td width="10%">작성자</td>
			<td width="10%">등록일</td>
			<td width="5%">조회수</td>
		</tr>
		
		<%for(int i=1; i<=pageSize; i++){ %>
		<%if(num<1)break; %>
		<%News news = list.get(curPos++); %>
		<tr onmouseover="showColor(this)" onmouseout="hideColor(this)">
			<td><%=num-- %></td>
			<td>
			<%if(news.getWriter().length()<1) {//삭제된 게시글이면%>
			<span class="inactive"><%=news.getTitle() %></span>
			<%}else { %>
			<a href="detail.jsp?news_id=<%=news.getNews_id() %>"><%=news.getTitle() %>
			<%if(news.getCnt()>0) {//댓글이 0개 이상일때만 표시%>
			[<%=news.getCnt() %>]
			<%} %>
			</a>
			</td>
			<%} %>
			<td><%=news.getWriter() %></td>
			<td><%=news.getRegdate().substring(0,10) %></td>
			<td><%=news.getHit() %></td>
		</tr>
		<%}%>
		
		<tr>
			<td colspan="5" align="center">
				<a href="list.jsp?currentPage=<%=firstPage-1%>">◀</a>
				<% for(int i=firstPage; i<=lastPage; i++){%>
				<%if(i>totalPage)break; //총 페이지 넘으면 그만 생성%>
				<a <%if(currentPage==i){ %>class="pageNum"<%}%>href="list.jsp?currentPage=<%=i %>">[<%=i %>]</a>
				<%}%>
				<a href="list.jsp?currentPage=<%=lastPage+1%>">▶</a>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onclick="location.href='regist_form.jsp'">글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>