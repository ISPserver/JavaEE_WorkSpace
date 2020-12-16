/*삭제 요청을 처리하는 컨트롤러 정의*/
package com.webApp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webApp1216.board.model.NoticeDAO;

public class DeleteServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	//삭제요청은 get , post 둘다 가능하긴 하지만 파라미터가 notice_id밖에 없으므로 get
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		
		int result = dao.delete(Integer.parseInt(notice_id));
		HttpSession session = request.getSession();
		
		//result=0;//테스트용
		
		if(result==0) {		
			//실패 페이지를 보여준다
			session.setAttribute("msg", "글이 삭제되지 않았습니다.관리자에게 문의하세요.");
			response.sendRedirect("/error/message.jsp");
		}else {
			session.setAttribute("msg", "글이 삭제되지 않았습니다.관리자에게 문의하세요.");
			response.sendRedirect("/board/list");
		}
	}
}
