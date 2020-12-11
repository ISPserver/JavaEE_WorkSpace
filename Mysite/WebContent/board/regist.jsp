<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//multipart/form-data 방식으로 전송된 파라미터는 업로드 컴포넌트를 통해 처리해야함
	
	//업로드 설정은 DiskFileItemFactory로 한다.
	DiskFileItemFactory factory = new DiskFileItemFactory();
	//자바기반의 웹어플리케이션은 어떤 OS건 중립적 실행이 보장되어야 하므로, 특정 시스템에 의존하는
	//경로를 사용해서는 안됨 --> 개발자가 경로를 넣으려고 하지말고, 프로그래밍에서 시스템의 경로를 동적으로 얻어와서 이용
	//해결방법: JSP의 내장객체가 바로 application 내장객체다.
	//application 내장객체는, 현재 어플리케이션의 전역적 정보를 가진 객체이므로,
	//어떤 디렉토리에서 사이트가 실행되는지 조차 알아낼 수 있다.
	String realPath = application.getRealPath("/data");//웹사이트의 루트를 기준으로 특정 파일이나 디렉토리 명시하면,
	//스스로 현재 웹사이트가 얹혀진 os로부터 풀경로를 구해온다.
	out.print(realPath);
	
	factory.setRepository(new File("C:/javaEE_workspace/Mysite/WebContent/data"));//임시적으로 사용할 경로
	factory.setSizeThreshold(2*1024*1024); //2M

	ServletFileUpload upload = new ServletFileUpload(factory);

%>