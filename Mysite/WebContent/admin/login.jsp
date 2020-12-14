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
		
		response.sendRedirect("/admin/index.jsp?admin="+admin);//Client로 하여금 지정한 URL로 요청을 시도하게끔 하는 기능
	}else{
		//로그인 실패시
		out.print(getMsgBack("로그인 정보가 올바르지 않습니다."));
	}
%>