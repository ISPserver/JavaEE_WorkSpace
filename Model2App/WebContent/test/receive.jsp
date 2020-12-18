<%@ page contentType="text/html;charset=utf-8"%>
<%
	//메시지 받기
	request.setCharacterEncoding("utf-8");
	String msg = request.getParameter("msg");	
	
	//session.setAttribute("result", msg);
	//session과 request는 거의 쌍둥이, 단지 살 수 있는 생명력에만 차이가 있음
	request.setAttribute("result", msg);
	
	//서버상의 또다른 jsp(서블릿)에 요청을 전달하기, 즉 응답없이(안끊기고) 객체 전달
	RequestDispatcher dis = request.getRequestDispatcher("/test/result.jsp");
	dis.forward(request, response);//포워딩(전달) 시작
	
	//response.sendRedirect("/test/result.jsp");
%>