package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class RegistController implements Controller{
	BoardDAO boardDAO = new BoardDAO(); 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//VO에 저장
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		//매퍼 쿼리 전송
		boardDAO.insert(board);//3단계
		
		//4단계: 저장할 것이 없다(클라이언트로 하여금, 지정한 url로 재접속 유도)
	}

	@Override
	public String getResultView() {	
		return "/view/board/regist";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
