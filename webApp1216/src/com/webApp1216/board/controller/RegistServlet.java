/*
 * jsp�� servlet �Ѵ� ���������� �ؼ� �� ������ ������
 * jsp�� ������ �������� ó���� ������ �����ϱ� ���� ���ߵ� ����̹Ƿ�,
 * jsp�� �ַ� ������ �������� ���ȴ�. servlet�� ������� ���� ���δ�.
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
	//�۾��⸦ ó���ϴ� �����̹Ƿ�, Ŭ���̾�Ʈ ��û�� post�� ����, ���� doXXX�� doPost �̿�
	
	//����) doXXX �޼���� service()�޼��忡 ���� ȣ���. �̶� ��û,���� ��ü ����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Ŭ���̾�Ʈ �Ķ���� �ޱ�*/
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//VO�� ���
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//Ŭ���̾�Ʈ�� �������� ����� �����͸� ���䰴ü �ɾ����
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter(); 		
		
		//�۵��
		int result = dao.insert(notice);
		if(result==0) {
			out.print("<script>");
			out.print("<alert('��Ͻ���')>;");
			out.print("history.back()");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("<alert('��ϼ���')>;");
			out.print("location.href='/board/list';");
			out.print("</script>");
		}
	}
}
