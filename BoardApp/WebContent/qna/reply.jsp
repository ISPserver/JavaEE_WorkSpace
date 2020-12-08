<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//넘겨받은 파라미터 값을 이용하여 답글 달기
	//공식: Team, Rank, Depth(내가본글팀,내본글Rank+1,내본글Depth+1)
	//상세보기 페이지 Hidden을 통해 전송된 파라미터를 얻기	
	request.setCharacterEncoding("utf-8");

	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String team = request.getParameter("team");//내본글 team
	String rank = request.getParameter("rank");//내본글 rank
	String depth = request.getParameter("depth");//내본글 depth
	
	/*
	1단계: 후발로 등록된 글이 들어갈 자리 확보(기존 글들을 밀어내는 효과)
	String sql="update qna set rank= rank+1 where team="+team+" and rank > "+rank;	
	2단계: 내가본글의 바로 아래쪽에 답변 insert
	sql="insert into qna(team,rank,depth) values("+team+","+(rank+1)+","+(depth+1)+")";
	*/
	
	QnA qna = new QnA();//QnA VO 생성	
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	qna.setTeam(Integer.parseInt(team));
	qna.setRank(Integer.parseInt(rank));
	qna.setDepth(Integer.parseInt(depth));
	
	QnADAO dao = new QnADAO();//QnADAO 생성
	int result = dao.reply(qna);//쿼리 실행
	
	if(result==0){
		out.print(getMsgBack("답변등록실패"));
	}else{
		out.print(getMsgURL("답변등록성공", "/qna/list.jsp"));
	}
%>