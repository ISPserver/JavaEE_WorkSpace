<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="board.model.Notice"%>
<%@ page import="board.model.NoticeDAO"%>
<%@ include file="/inc/lib.jsp" %>
<%
	NoticeDAO noticeDAO = new NoticeDAO();	

	request.setCharacterEncoding("utf-8");//한국어, 중국어, 아랍어 전세계 모든 언어 깨지지않음
	//jsp가 지원하는 내장객체 중, request객체를 이용하여 클라이언트의 전송 파라미터를 받아보자!!
	String author = request.getParameter("author"); //작성자
	String title = request.getParameter("title");//제목
	String content = request.getParameter("content");//내용
	
	//등록
	Notice notice = new Notice();
	notice.setAuthor(author);
	notice.setTitle(title);
	notice.setContent(content);
	
	int result = noticeDAO.regist(notice);//vo, dto
	
	if(result==0){
		out.print(getMsgBack("등록실패"));		
	}else{
		out.print(getMsgURL("등록성공","/board/list.jsp"));
	}
	
%>