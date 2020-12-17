/*
 * MVC패턴으로 개발하다보면, 늘어나는 컨트롤러에 대해 일일이 매핑과정을 진행해야함
 * 이때 너무 많은 매핑은 관리하기가 까다롭다. 따라서 유지보수성 떨어짐
 * 현실과 유사하게, 어플리케이션에서도 대형 업무처리시 클라이언트의 요청을 곧바로 해당
 * 컨트롤레에게 처리하게 하지 않고, 중간에 메인 컨트롤러를 두고, 모든 요청을 이 메인 컨트롤러에서
 * 처리해 적절한 하위 컨트롤러에게 전담시키는 방식 이용
 * 
 * 이 컨트롤러는 웹어플리케이션의 모든 ~~~요청을 1차적으로 받는다.
 */
package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blood.controller.BloodController;
import movie.controller.MovieController;
import movie.model.MovieAdvisor;

public class DispatcherServlet extends HttpServlet{
	FileInputStream fis ;
	Properties props;
	
	//init은 서블릿의 생명주기에서, 최초 접촉자에 의해 톰켓이 인스턴스를 생성하며, 이와 동시에 초기화 메서드로서 호출된다.
	public void init(ServletConfig config) throws ServletException {
		//getRealPath는 jsp의 내장객체인 application 자바에선 ServletContext를 이용
		ServletContext context = config.getServletContext();
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(contextConfigLocation);
		
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);//스트림과 프로퍼티스 연결
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	//get or post 상관없이, 모든 요청을 이 메서드에서 처리
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8"); //파라미터에 대한 인코딩		
		//1단계: 요청을 받는다. --> action에 *.do로 주면됨
		//클라이언트가 영화를 원하면? --> 영화담당 컨트롤러에게 전가
		//클라이언트가 혈액형을 원하면? --> 혈액형담당 컨트롤러에게 전가
		
		//2단계: 요청을 분석하여, 알맞는 컨트롤러에게 요청을 전달 --> 글쓰기?, 삭제? 혈액형? 영화?		
		//해답은 클라이언트 요청 자체에 있음. 즉 요청시 사용한 URI가 곧 요청 구분값이다.
		String uri = request.getRequestURI();		
		Controller controller = null;		
		String className = null;
		
		//if문 대신, 프로퍼티스 객체를 이용하여 key값으로 메모리에 올려질 컨트롤러의 이름을 value로 반환받기
		className = props.getProperty(uri);
		
		//하위 컨트롤러 생성
		try {
			Class controllerClass = Class.forName(className);//클래스 로드
			controller = (Controller)controllerClass.newInstance();
			
			//2단계: 하위 컨트롤러에게 전달,다형성
			controller.execute(request, response);//다형성으로 실행됨	
			//5단계: 클라이언트에게 알맞는 결과를 보여준다.
			response.sendRedirect(controller.getViewPage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	//서블릿의 생명주기 메서드 중 서블릿이 소멸할때 호출되는 메서드인 destory()에
	//스트림 등의 자원을 닫는 처리
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
