package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
//��� ��û�� ó���ϴ� ��Ʈ�ѷ�
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;
import com.model2.domain.Comment;

public class RegistController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	
	//����� ����� �񵿱� ��û���� ������ ������, ���������� �������� �����ִ°� �ƴ�,
	//�����͸� �����ؾ� �Ѵ�.
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���Ͱ��� �Ѿ������ ���� Ȯ��
		String msg = request.getParameter("msg");
		String author = request.getParameter("author");
		String board_id = request.getParameter("board_id");
		//vo���
		Comment comment = new Comment();
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(Integer.parseInt(board_id));
		//��ϸ޼��� ȣ��
		int result = commentDAO.insert(comment);
		
		//4�ܰ�: DML ������ ����
		request.setAttribute("result", result);//boxing
	}

	@Override
	public String getResultView() {
		return "/view/comment/regist";//��� ����� Ŭ���̾�Ʈ���� ������ jsp Ű ��
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
