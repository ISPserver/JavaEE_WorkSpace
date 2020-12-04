<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	//파라미터 받기
	String board_id = request.getParameter("board_id");
	String author = request.getParameter("author");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String filename = request.getParameter("filename");
	
	//VO 생성
	ImageBoard board = new ImageBoard();
	board.setBoard_id(Integer.parseInt(board_id));
	board.setAuthor(author);
	board.setTitle(title);
	board.setContent(content);
	board.setFilename(filename);
	
	//DAO에 VO전달 (Update)
	ImageBoardDAO boardDAO = new ImageBoardDAO();
	int result = boardDAO.update(board);
	
	if(result==0){
		out.print(getMsgBack("수정실패"));
	}else{
		out.print(getMsgURL("수정성공", "/imageboard/list.jsp"));
	}
	
%>