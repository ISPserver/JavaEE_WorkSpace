<%@page import="board.model.NewsDAO"%>
<%@page import="board.model.News"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="news" class="board.model.News"></jsp:useBean>
<jsp:setProperty property="*" name="news"/>
<%
	if(new NewsDAO().update(news)==0){
		out.print(getMsgBack("수정실패"));
	}else{
		out.print(getMsgURL("수정성공", "detail.jsp?news_id="+news.getNews_id()));
	}
%>