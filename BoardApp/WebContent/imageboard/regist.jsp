<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DefaultFileItemFactory"%>
<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="common.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ include file="/inc/lib.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%! 
	String saveDir="C:/javaEE_workspace/BoardApp/WebContent/data";
	int maxSize=3*1024*1024; //3M byte
	ImageBoardDAO dao = new ImageBoardDAO();
%>
<%
	//실습했던 예제보다 기능이 더 추가됨, db에 넣어야함 DAO 이용
	
	DefaultFileItemFactory itemFactory = new DefaultFileItemFactory();
	itemFactory.setRepository(new File(saveDir));	
	itemFactory.setSizeThreshold(maxSize);
	itemFactory.setDefaultCharset("utf-8");
	
	ServletFileUpload upload = new ServletFileUpload(itemFactory);
	
	//업로드된 정보 분석--> 각각의 컴포넌트들을 FileItem으로 받는다.
	request.setCharacterEncoding("utf-8");
	List<FileItem> items = upload.parseRequest(request);
	
	ImageBoard board = new ImageBoard();//Empty상태 VO 생성
	for(FileItem item : items){
		if(item.isFormField()){//textField라면 DB에 넣기
			//VO에 텍스트필드 값 담기
			if(item.getFieldName().equals("author")){//필드명이 author면
				board.setAuthor(item.getString());
			}else if(item.getFieldName().equals("title")){//필드명이 author면
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("content")){//필드명이 author면
				board.setContent(item.getString());
			}
					
		}else{//textField가 아니라면 업로드 처리
			String newName=System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());
			String destFile = saveDir+"/"+newName;
			File file = new File(destFile);
			item.write(file);//물리적 저장 시점
			
			out.print("업로드 완료");
			//vo에 파일명 담기
			board.setFilename(newName);
		}
	}
	
	//반복문을 지나친 이 시점에는 VO에 데이터가 이미 채워진 상태
	
	int result = dao.insert(board);//이 시점에는 채워진 VO를 원함
	if(result==0){
		out.print(getMsgBack("등록실패"));
	}else{
		out.print(getMsgURL("등록성공", "/imageboard/list.jsp"));
	}
%>