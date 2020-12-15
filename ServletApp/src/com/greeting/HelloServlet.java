package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿이란? Java 클래스중 오직 웹서버에서만 해석 및 실행되어질 수 있는 클래스
/*
 * 서블릿은 최초의 요청이 있을떄 톰캣 즉 웹컨테이너에 의해 인스턴스가 메모리에 올라옴
 * 인스턴스가 생성될때, 서블릿으로 가져야할 정보를 고양이에케 넘겨받을 수 있는데, 이때 사용되는
 * 메서드가 init()이다.
 * */
public class HelloServlet extends HttpServlet {
	
	//이 메서드는 서블릿이 태어난 직후, 초기화 작업에 사용 
	//또한 Tomcat과 같은 웹컨테이너에 의해 호출된다. 즉 서블릿의 생성 및 생명주기 메서드
	//의 호출자는 바로 톰캣임
	public void init(ServletConfig config) throws ServletException {
		String msg = config.getInitParameter("msg");
		System.out.println(msg);
		
		//jsp내장객체중 웹어플리케이션의 전역적 정보를 가진 객체 application
		ServletContext context = config.getServletContext();//jsp에서의 application 내장객체
		System.out.println(context.getRealPath("/"));
	}
	
	//서블릿이 일단 생성된 후, 초기화 까지 마치면, 클라이언트의 요청을 처리할 준비가 된 것이며,
	//클라이언트의 요청을 처리하는 메서드가 바로 service 메서드임
	//서비스메서드가 요청을 처리하려면, 클라이언트의 요청정보와 클라이언트에게 보낼 응답정보를
	//필요로 하기 때문에 service()메서더 매개변수로 request,response 객체가 전달되야함(고양이가 함)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 전송한 요청 정보중 파라미터를 끄집어서 출력
		String id = request.getParameter("id");
		
		//클라이언트에게 전송
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("당신이 전송한 파라미터는"+id);
	}
	
	//서블릿이 소멸할때 호출되는 메서드
	public void destroy() {		
		super.destroy();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet do!!!");
	}

}
