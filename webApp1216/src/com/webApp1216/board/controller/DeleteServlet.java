/*���� ��û�� ó���ϴ� ��Ʈ�ѷ� ����*/
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
	//������û�� get , post �Ѵ� �����ϱ� ������ �Ķ���Ͱ� notice_id�ۿ� �����Ƿ� get
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		
		int result = dao.delete(Integer.parseInt(notice_id));
		HttpSession session = request.getSession();
		
		//result=0;//�׽�Ʈ��
		
		if(result==0) {		
			//���� �������� �����ش�
			session.setAttribute("msg", "���� �������� �ʾҽ��ϴ�.�����ڿ��� �����ϼ���.");
			response.sendRedirect("/error/message.jsp");
		}else {
			session.setAttribute("msg", "���� �������� �ʾҽ��ϴ�.�����ڿ��� �����ϼ���.");
			response.sendRedirect("/board/list");
		}
	}
}
