<%@page import="java.io.File"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
//넘겨받은 board_id를 이용하여 삭제
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");
	ImageBoardDAO boardDAO = new ImageBoardDAO();
	
	//삭제업무(DB삭제, 파일삭제)
	
	//파일삭제(파일 다룰수 있는 클래스: java.io.File)
	File file = new File("C:/javaEE_workspace/BoardApp/WebContent/data/"+filename);
	if(file.delete()){
		//DB도 삭제
		boardDAO.delete(Integer.parseInt(board_id));
		out.print(getMsgURL("삭제 완료", "/imageboard/list.jsp"));
	}else{
		//File이 삭제 안됬다면 DB삭제 안함
		out.print(getMsgBack("삭제 실패"));
	}	
	
%>