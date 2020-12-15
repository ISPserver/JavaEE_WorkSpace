<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	String admin_id="scott";
	String admin_pass="1234";
	
	String mid = request.getParameter("mid");
	String password = request.getParameter("password");
	
	//ID와 같고 비번까지 같다면
	if(mid.equals(admin_id) && password.equals(admin_pass)){ 
		//로그인 성공시 관리자 페이지 보여주기
		Admin admin = new Admin();
		//관리자 정보를 VO에 담기
		admin.setMid(mid);
		admin.setPassword(password);
		
		//jsp의 내장객체인 session 객체는 클라이언트가 신규접속이라고 생각할때, 새로운 session 인스턴스를 생성
		//세션 아이디도 생성하여, 세션에 부여한다.
		//이 세션은 클라이언트가 브라우저를 종료하지 않거나, 일정시간내 재접속 할 경우 사용 가능
		//웹은 stateless 기반이긴 하나 서버측 메모리에 생성된 세션을 이용하면 연결이 유지된 것처럼
		//보여질 수 있다. 주 용도)로그인 후 회원정보를 모든 페이지에서 사용할 수 있는 기능, 장바구니
		session.setAttribute("ad", admin);		
		response.sendRedirect("/admin/index.jsp");//Client로 하여금 지정한 URL로 요청을 시도하게끔 하는 기능
	}else{
		//로그인 실패시
		out.print(getMsgBack("로그인 정보가 올바르지 않습니다."));
	}
%>