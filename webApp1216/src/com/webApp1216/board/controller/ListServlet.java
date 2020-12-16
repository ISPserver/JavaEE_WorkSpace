/*
 * javaEE 개발패턴 중 mvc 패턴을 적용한 개발방법을 가리켜 model2 방식이라 일컫는다.
 * 특히 jsp가 디자인에 사용되고 있으므로, 웹상의 요청을 받고 응답이 가능한 서블릿이 컨트롤러로써
 * 역할을 수행하게 된다.
 * */
package com.webApp1216.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webApp1216.board.model.NoticeDAO;

//클라이언트의 목록요청을 처리하는 서블릿 정의
public class ListServlet extends HttpServlet{
	NoticeDAO noticeDAO=new NoticeDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = noticeDAO.selectAll();
		//뭔가 저장할 방법
		//세션? 클라이언트가 브라우저 프로세스를 닫지 않거나, 일정 시작내 재접속할때 서버측 메모리에
		//담겨진 정보를 사용할 수 있는 기술(새로운 접속인 경우 세션객체는 새로 생성, 세션 아이디 새롭게 발급)
		//jsp에서의 session 내장객체는 자료형이 HttpSession임
		HttpSession session = request.getSession();//이 요청과 관련 세션 얻기
		session.setAttribute("noticeList", list);//세션 객체에 보관	
		
		//결과 페이지 선택
		response.sendRedirect("/board/list.jsp");
	}
}