<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//DB연동
	QnADAO dao = new QnADAO();
	List<QnA> list = dao.selectAll();
	

	int totalRecord=list.size();//총 레코드 수, 실제DB에 있는 데이터 수를 대입하면 된다.
	int pageSize=10;//한 페이지당 보여줄 레코드 수
	int totalPage=((int)Math.ceil((float)totalRecord/pageSize));//총 페이지 수
	int blockSize=10;//한 블럭당 보여질 페이지 수	
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage")); //현재 페이지		
	}
	int firstPage=currentPage-(currentPage-1)%blockSize;//반복문의 시작값
	int lastPage=firstPage+blockSize-1; //반복문의 끝값
	int num=totalRecord-((currentPage-1)*pageSize);//페이지당 시작 번호(힌트: 위에 변수 조합)
	int curPos=(currentPage-1)*pageSize;//페이지당 List에서의 시작 index
%>
<%="totalRecord:"+totalRecord+"<br>" %>
<%="pageSize:"+pageSize+"<br>" %>
<%="totalPage:"+totalPage+"<br>" %>
<%="blockSize:"+blockSize+"<br>" %>
<%="currentPage:"+currentPage+"<br>" %>
<%="firstPage:"+firstPage+"<br>" %>
<%="lastPage:"+lastPage+"<br>" %>
<%="num:"+num+"<br>" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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
img{
	box-sizing:border-box;
}
a{
	text-decoration:none;
}
.pageNum{
	font-size:20pt;
	color:red;
	font-weight:bold;
}
</style>

<script>
</script>
</head>
<body>
	<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  
  <%for(int i=1; i<=pageSize; i++){%>
  <%if(num<1) break; %>
  <% //break문을 만나지 않았다는건 레코드가 있다는 뜻, break아래에서 데이터 추출
  		QnA qna= list.get(curPos++);//1page: 0~9, 2page:10~19
  %>
  <tr>
    <td><%=num-- %></td>
    <td>
    	<%if(qna.getDepth()>0) { %>
    	<img src="/images/reply.png" style="margin-left:<%=15*qna.getDepth() %>">
    	<%}%>
    	<a href="/qna/detail.jsp?qna_id=<%=qna.getQna_id()%>"><%=qna.getTitle()%></a>
    </td>
    <td><%=qna.getWriter() %></td>
    <td><%=qna.getRegdate() %></td>
    <td><%=qna.getHit() %></td>
  </tr>  
  <%} %>
  <tr>
  	<td colspan="5" style="text-align:center">
  	<%if((firstPage-1) >=1 ) { //돌아갈 페이지가 있다면%>
  		<a href="/qna/list2.jsp?currentPage=<%=firstPage-1%>">◀</a>
  	<%} else { //돌아갈 페이지가 없다면%>
  		<a href="javascript:alert('처음 페이지입니다.');">◀</a>
  	<%} %>
  	
		<%for(int i=firstPage; i<=lastPage; i++){ %>		
			<%if(i>totalPage) break; //페이지 출력하는 i가 총페이지 도달하면 break%>
			<a href="/qna/list2.jsp?currentPage=<%=i %>" <%if(currentPage==i) {%>class="pageNum"<%}%>>[<%=i %>]</a>			
		<%} %>
		
		<%if((lastPage+1) <totalPage ) { //돌아갈 페이지가 있다면%>	
		<a href="/qna/list2.jsp?currentPage=<%=lastPage+1%>">▶</a>
		<%} else { //돌아갈 페이지가 없다면%>
			<a href="javascript:alert('마지막 페이지입니다.');">▶</a>
		<%} %>
	</td>
	</tr>
	<tr>
	
  	<td colspan="5">
		<button>글등록</button>
	</td>
  </tr>
  
  <tr>
  	<td colspan="5" style="text-align:center">
		<%@ include file="/inc/footer.jsp"%>
	</td>
  </tr>

</table>
</body>
</html>