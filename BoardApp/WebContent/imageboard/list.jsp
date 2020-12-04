<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	ImageBoardDAO boardDAO = new ImageBoardDAO();
	ArrayList<ImageBoard> list = boardDAO.selectAll();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table{
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$("button").on("click", function(){
		//자바스크립트에서 링크 구현
		location.href="/imageboard/regist_form.jsp";		
	});
});//onload
</script>
</head>
<body>

<table>
  <tr>
    <th>No</th>    
    <th>사진</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <% for(int i=0; i<list.size(); i++){ %>
  <% ImageBoard board = list.get(i); %>
  <tr>
    <td><%=board.getBoard_id() %></td>
    <td><img src="<%="../data/"+board.getFilename() %>" width="100" height="50" /></td>
    <td>
	<a href="/imageboard/detail.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle()%></a>
	</td>
    <td><%=board.getAuthor()%></td>
    <td><%=board.getRegdate()%></td>
    <td><%=board.getHit()%></td>
  </tr>
  <% } %>
  <tr>
  	<td colspan="6">
		<button>글등록</button>
	</td>
  </tr>
  
  <tr>
  	<td colspan="6" style="text-align:center">
		<%@ include file="/inc/footer.jsp"%>
	</td>
  </tr>

</table>

</body>
</html>