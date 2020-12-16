/*
 * jsp나 servlet 둘다 웹서버에서 해석 및 실행은 되지만
 * jsp는 서블릿의 디자인적 처리시 단점을 보완하기 위해 개발된 기술이므로,
 * jsp는 주로 디자인 영역에서 사용된다. servlet은 기능적인 곳에 쓰인다.
 * */
package com.webApp1216.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webApp1216.board.model.Notice;
import com.webApp1216.board.model.NoticeDAO;

public class RegistServlet extends HttpServlet {
	NoticeDAO dao = new NoticeDAO();
	//글쓰기를 처리하는 서블릿이므로, 클라이언트 요청이 post로 들어옴, 따라서 doXXX중 doPost 이용
	
	//참고) doXXX 메서드는 service()메서드에 의해 호출됨. 이때 요청,응답 객체 전달
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*클라이언트 파라미터 받기*/
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//VO에 담기
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//클라이언트의 브라우저에 출력할 데이터를 응답객체 심어놓기
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter(); 		
		
		//글등록
		int result = dao.insert(notice);
		if(result==0) {
			out.print("<script>");
			out.print("<alert('등록실패')>;");
			out.print("history.back()");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("<alert('등록성공')>;");
			out.print("location.href='/board/list';");
			out.print("</script>");
		}
	}
}
