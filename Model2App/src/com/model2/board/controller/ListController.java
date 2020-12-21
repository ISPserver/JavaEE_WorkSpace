/*코멘트 게시판의 리스트 요청을 처리하는 컨트롤러,
 *이 컨트롤러는 서블릿은 아니다. 단지, 서블릿으로 전달받은 요청,응답 객체를
 *넘겨 받았으므로 하위 컨트롤러가 가능*/
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
		//3단계: 알맞는 로직 객체에 일 시키기
		List list = boardDAO.selectAll();
		
		//4단계: 저장할게 있다면 저장
		request.setAttribute("boardList", list);
	}

	public String getResultView() {
		return "/view/board/list";
	}

	public boolean isForward() {
		return true;
	}	

}
