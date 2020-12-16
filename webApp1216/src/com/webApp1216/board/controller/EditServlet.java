package com.webApp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webApp1216.board.model.Notice;
import com.webApp1216.board.model.NoticeDAO;

public class EditServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	//post방식
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터에 대한 인코딩 처리
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String notice_id = request.getParameter("notice_id");
		
		Notice notice = new Notice();//empty vo 생성
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(Integer.parseInt(notice_id));
		
		//DAO에게 인식
		int result = dao.update(notice);
		
		HttpSession session = request.getSession();
		
		if(result==0) {
			session.setAttribute("msg", "수정에 실패했습니다");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/detail?notice_id="+notice.getNotice_id());
		}
		
		
	}
}
