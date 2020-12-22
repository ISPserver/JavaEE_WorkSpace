package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class DetailController implements Controller {
	BoardDAO boardDAO = new BoardDAO();

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String board_id = request.getParameter("board_id");
		
		//3단계: 알맞는 객체에 일 시키기
		Board board = (Board)boardDAO.select(Integer.parseInt(board_id));
		
		//4단계: 저장할게 잇음
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/detail");
		
		return mav;
	}

}
