package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class RegistController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 로직 객체에게 일 시키기
		//파라미터 vo에 담기
		Notice notice = new Notice();//create empty vo
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		//4단계: 저장할 것이 있다면 저장, insert는 저장할게 없음 --> 생략
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
