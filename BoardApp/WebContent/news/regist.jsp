<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	/*
		넘겨받은 파라미터 값을 이용해 뉴스기사 등록하기
		jsp 문서에서만 사용가능한 서버측의 태그를 사용
		사실상 자바 코드지만, 코드량 단축시키기 위해 태그형식으로 지원
	*/
	request.setCharacterEncoding("utf-8");
%>
<!-- request.getParameter() + vo.setXXX() 같은 효과-->
<jsp:useBean id="news" class="board.model.News"/>
<jsp:setProperty property="*" name="news"/>
<%
	if(new NewsDAO().insert(news)==0){
		out.print(getMsgBack("등록 실패"));
	}else{
		out.print(getMsgURL("등록 성공", "list.jsp"));
	}
%>

