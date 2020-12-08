<%@page import="board.model.QnADAO"%>
<%@page import="board.model.QnA"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	
	String qna_id = request.getParameter("qna_id");
	String team = request.getParameter("team");
	String rank = request.getParameter("rank");
	String depth = request.getParameter("depth");
	
	QnA qna = new QnA();
	qna.setQna_id(Integer.parseInt(qna_id));
	qna.setTeam(Integer.parseInt(team));
	qna.setRank(Integer.parseInt(rank));
	qna.setDepth(Integer.parseInt(depth));
	
	QnADAO dao = new QnADAO();
	int result = dao.delete(qna);
	
	if(result==0){
		out.print(getMsgBack("삭제 실패"));
	}else{
		out.print(getMsgURL("삭제 완료", "/qna/list.jsp"));
	}
%>