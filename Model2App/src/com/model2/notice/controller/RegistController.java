package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class RegistController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ�: �˸´� ���� ��ü���� �� ��Ű��
		//�Ķ���� vo�� ���
		Notice notice = new Notice();//create empty vo
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		//4�ܰ�: ������ ���� �ִٸ� ����, insert�� �����Ұ� ���� --> ����
	}
	
	@Override
	public String getResultView() {		
		return "/view/notice/regist";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}