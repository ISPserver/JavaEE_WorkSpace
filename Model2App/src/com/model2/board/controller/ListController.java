/*�ڸ�Ʈ �Խ����� ����Ʈ ��û�� ó���ϴ� ��Ʈ�ѷ�,
 *�� ��Ʈ�ѷ��� ������ �ƴϴ�. ����, �������� ���޹��� ��û,���� ��ü��
 *�Ѱ� �޾����Ƿ� ���� ��Ʈ�ѷ��� ����*/
package com.model2.board.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ�: �˸´� ���� ��ü�� �� ��Ű��
		List list = boardDAO.selectAll();
		
		//4�ܰ�: �����Ұ� �ִٸ� ����
		request.setAttribute("boardList", list);
	}

	public String getResultView() {
		return "/view/board/list";
	}

	public boolean isForward() {
		return true;
	}	

}
