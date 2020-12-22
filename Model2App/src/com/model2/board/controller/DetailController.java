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
		//3�ܰ�: �˸´� ���� ��ü���� �� ��Ű��		
		String board_id = request.getParameter("board_id");
		Board board = boardDAO.select(Integer.parseInt(board_id));
		
		//4�ܰ�: ������ ���� �ִٸ� ����
		request.setAttribute("board", board);
		//System.out.println("�󼼺��� ��Ʈ�ѷ����� �Ѱܹ��� ��û��ü"+board);
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