<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>
<%
	String notice_id = request.getParameter("notice_id");
	NoticeDAO noticeDAO = new NoticeDAO();
	int result = noticeDAO.delete(Integer.parseInt(notice_id));//DML수행
	//삭제 후 완료메시지 보여주고 list.jsp를 요청할 것!!
	if(result==0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제성공","/board/list.jsp"));
	}	
%>