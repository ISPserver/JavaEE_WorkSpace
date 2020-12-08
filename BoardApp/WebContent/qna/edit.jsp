<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%

	request.setCharacterEncoding("utf-8");

	String qna_id = request.getParameter("qna_id");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	QnA qna = new QnA();
	qna.setQna_id(Integer.parseInt(qna_id));
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	
	QnADAO dao = new QnADAO();
	int result = dao.update(qna);
	
	if(result==0){
		out.print(getMsgBack("수정 실패"));
	}else{
		out.print(getMsgURL("수정 완료", "/qna/list.jsp"));
	}
%>