/*
 *웹상의 모든 클라이언트의 요청을 받고, 응답을 전담하는 컨트롤러 정의 
 */
package com.model2.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet{
	FileReader fis; //컨트롤러 매핑 설정파일을 읽기 위한 스트림
	JSONObject controllerMap;//컨트롤러의 정보들이 들어있는 Map(json)
	JSONObject viewMap;//컨트롤러의 정보들이 들어있는 Map(json)
	
	public void init(ServletConfig config) throws ServletException {
		//경로를 xml에서만 바꾸면 되게끔 init Parameter 설정
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			
			//파싱
			JSONObject json = (JSONObject)jsonParser.parse(fis);			
			//데이터에 접근
			controllerMap = (JSONObject)json.get("controller");			
			viewMap = (JSONObject)json.get("view");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//doXXX형 메서드를 정의해 요청 받기
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//공통 코드
		
		//2단계: 요청을 분석한다.
		String uri = request.getRequestURI(); //클라이언트가 요청시 사용한 uri 자체가 요청의 구분값으로 사용될 수 있다.
		
		//if문을 대신할 구조화 된 데이터를 선택(xml, json, properties)
		String controllerName = (String)controllerMap.get(uri);
		//System.out.println(controllerName);
		
		//동생 하위 컨트롤러의 이름을 알앗으니, 인스턴스를 만들고, execute(), getResultView호출()
		Class controllerCalss = null; 
		Controller controller = null;
		try {
			controllerCalss = Class.forName(controllerName);//String, 즉 문자열로 지정한 클래스에 대한 실제 클래스
			controller = (Controller)controllerCalss.newInstance(); //하위 컨트롤러의 인스턴스 생성
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.execute(request, response);//3단계업무
		
		//하위 컨트롤러부터 넘겨받은 뷰에 대한 정보를 이용하여 클라이언트에게 알맞는 뷰를 보여주기
		String resultKey = controller.getResultView();		
		String viewPage = (String)viewMap.get(resultKey);
		
		//응답시 sendRedirect로 처리해야할 경우가 있고,
		//때로는 forwarding 처리해야할 경우가 있다.
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);//응답없이, 서버상의 또 다른 자원으로 요청을 전달
		}else {
			response.sendRedirect(viewPage);//세션믿고 까부는중
		}
	}
	
	@Override
	public void destroy() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
