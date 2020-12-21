//댓글 목록요청을 처리하는 컨트롤러
package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
//댓글 목록 요청을 처리하는 컨트롤러
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//3단계: 코멘트 목록 가져오기
		String board_id = request.getParameter("board_id");
		List commentList = commentDAO.selectAll(Integer.parseInt(board_id));
		
		//4단계: 저장할게 잇다면, 결과저장
		request.setAttribute("commentList", commentList);
	}

	@Override
	public String getResultView() {
		return "/view/comment/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}
	
}
