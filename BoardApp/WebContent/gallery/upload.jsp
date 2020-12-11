<%@page import="java.io.File"%>
<%@page import="common.file.FileManager"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
	/*
	클라이언트가 전송한 제목 텍스트 및 바이너리 파일을 서버의 특정 디렉토리에 저장해보자
	이런 처리를 업로드라고 한다.
	*/
	request.setCharacterEncoding("utf-8");//파라미터에 한글깨지지 않도록 인코딩
	//String msg = request.getParameter("msg");//String 메시지 받기
	
	//이미지는  글씨가 아닌 바이너리 파일이므로, request.getParameter로는 못받는다
	//따라서 IO, 네트워크 등의 처리를 해야하는데, 이 자체만으로도 하나의 프로젝트 규모
	//따라서, 누군가가 만든 라이브러리를 이용해 개발시간 단축
	//cos.jar라는 Oreilly라는 출판사에서 제작한 컴포넌트 사용
	String saveDirectory="C:/javaEE_workspace/BoardApp/WebContent/data";//하드디스크의 물리적 경로를 명시해야 한다.
	int maxSize=2*1024*1024;//2M byte의미
	String encoding="utf-8";
	
	//FileRenamePolicy policy: 업로드시, 동일한 파일을 업로드 했을때 자동으로 이름 부여
	//예) p.jpg, p1,jpg --> 파일명은 개발자가 주도해 명명하므로, policy를 굳이 이용할 필요 없다.)
	try{
		MultipartRequest multi = new MultipartRequest(request,saveDirectory,maxSize,encoding);
		//업로드 컴포넌트를 이용할 경우, 스트링 파라미터도 업로드 컴포넌트를 이용해야 한다.
		String msg = multi.getParameter("msg");
		out.print(msg);
		//업로드가 완료된 후, 즉 서버의 저장소에 파일이 존재하게 된후 해야할일
		//개발자가 규칙 정해서 설정
		long time = System.currentTimeMillis();
		//구한시간에 확장자를 붙이면 최종적으로 파일이름 리네임		
		String ori = multi.getOriginalFileName("photo");
		String ext = FileManager.getExtend(ori); //jpg png 만 얻어오기
		String filename = time+"."+ext;
		
		//파일명 교체
		File savedFile = multi.getFile("photo");
		savedFile.renameTo(new File(saveDirectory+"/"+filename));
		
		//클라이언트에게 전송할 응답정보를 가진 객체;
		//클라이언트의 브라우저로 하여금, 지정한 URL로 재접속을 시도하게 만듬
		//response.sendRedirect("/gallery/photo_list.jsp");
		
	}catch(IOException e){
		e.printStackTrace();//로그에 에러 출력
	}
	
%>