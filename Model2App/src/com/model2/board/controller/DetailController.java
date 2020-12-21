package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class DetailController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 로직 객체에게 일 시키기		
		String board_id = request.getParameter("board_id");
		Board board = boardDAO.select(Integer.parseInt(board_id));
		
		//4단계: 저장할 것이 있다면 저장
		request.setAttribute("board", board);
		//System.out.println("상세보기 컨트롤러에서 넘겨받은 요청객체"+board);
	}

	@Override
	public String getResultView() {
		return "/view/board/detail";
	}

	@Override
	public boolean isForward() {
		return true;
	}
	
}
