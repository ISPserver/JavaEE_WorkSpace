package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�����̶�? Java Ŭ������ ���� ������������ �ؼ� �� ����Ǿ��� �� �ִ� Ŭ����
/*
 * ������ ������ ��û�� ������ ��Ĺ �� �������̳ʿ� ���� �ν��Ͻ��� �޸𸮿� �ö��
 * �ν��Ͻ��� �����ɶ�, �������� �������� ������ ����̿��� �Ѱܹ��� �� �ִµ�, �̶� ���Ǵ�
 * �޼��尡 init()�̴�.
 * */
public class HelloServlet extends HttpServlet {
	
	//�� �޼���� ������ �¾ ����, �ʱ�ȭ �۾��� ��� 
	//���� Tomcat�� ���� �������̳ʿ� ���� ȣ��ȴ�. �� ������ ���� �� �����ֱ� �޼���
	//�� ȣ���ڴ� �ٷ� ��Ĺ��
	public void init(ServletConfig config) throws ServletException {
		String msg = config.getInitParameter("msg");
		System.out.println(msg);
		
		//jsp���尴ü�� �����ø����̼��� ������ ������ ���� ��ü application
		ServletContext context = config.getServletContext();//jsp������ application ���尴ü
		System.out.println(context.getRealPath("/"));
	}
	
	//������ �ϴ� ������ ��, �ʱ�ȭ ���� ��ġ��, Ŭ���̾�Ʈ�� ��û�� ó���� �غ� �� ���̸�,
	//Ŭ���̾�Ʈ�� ��û�� ó���ϴ� �޼��尡 �ٷ� service �޼�����
	//���񽺸޼��尡 ��û�� ó���Ϸ���, Ŭ���̾�Ʈ�� ��û������ Ŭ���̾�Ʈ���� ���� ����������
	//�ʿ�� �ϱ� ������ service()�޼��� �Ű������� request,response ��ü�� ���޵Ǿ���(����̰� ��)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ŭ���̾�Ʈ�� ������ ��û ������ �Ķ���͸� ����� ���
		String id = request.getParameter("id");
		
		//Ŭ���̾�Ʈ���� ����
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("����� ������ �Ķ���ʹ�"+id);
	}
	
	//������ �Ҹ��Ҷ� ȣ��Ǵ� �޼���
	public void destroy() {		
		super.destroy();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet do!!!");
	}

}
