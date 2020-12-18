/*
 *������ ��� Ŭ���̾�Ʈ�� ��û�� �ް�, ������ �����ϴ� ��Ʈ�ѷ� ���� 
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
	FileReader fis; //��Ʈ�ѷ� ���� ���������� �б� ���� ��Ʈ��
	JSONObject controllerMap;//��Ʈ�ѷ��� �������� ����ִ� Map(json)
	JSONObject viewMap;//��Ʈ�ѷ��� �������� ����ִ� Map(json)
	
	public void init(ServletConfig config) throws ServletException {
		//��θ� xml������ �ٲٸ� �ǰԲ� init Parameter ����
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			
			//�Ľ�
			JSONObject json = (JSONObject)jsonParser.parse(fis);			
			//�����Ϳ� ����
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
	
	//doXXX�� �޼��带 ������ ��û �ޱ�
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//���� �ڵ�
		
		//2�ܰ�: ��û�� �м��Ѵ�.
		String uri = request.getRequestURI(); //Ŭ���̾�Ʈ�� ��û�� ����� uri ��ü�� ��û�� ���а����� ���� �� �ִ�.
		
		//if���� ����� ����ȭ �� �����͸� ����(xml, json, properties)
		String controllerName = (String)controllerMap.get(uri);
		//System.out.println(controllerName);
		
		//���� ���� ��Ʈ�ѷ��� �̸��� �˾�����, �ν��Ͻ��� �����, execute(), getResultViewȣ��()
		Class controllerCalss = null; 
		Controller controller = null;
		try {
			controllerCalss = Class.forName(controllerName);//String, �� ���ڿ��� ������ Ŭ������ ���� ���� Ŭ����
			controller = (Controller)controllerCalss.newInstance(); //���� ��Ʈ�ѷ��� �ν��Ͻ� ����
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.execute(request, response);//3�ܰ����
		
		//���� ��Ʈ�ѷ����� �Ѱܹ��� �信 ���� ������ �̿��Ͽ� Ŭ���̾�Ʈ���� �˸´� �並 �����ֱ�
		String resultKey = controller.getResultView();		
		String viewPage = (String)viewMap.get(resultKey);
		
		//����� sendRedirect�� ó���ؾ��� ��찡 �ְ�,
		//���δ� forwarding ó���ؾ��� ��찡 �ִ�.
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);//�������, �������� �� �ٸ� �ڿ����� ��û�� ����
		}else {
			response.sendRedirect(viewPage);//���ǹϰ� ��δ���
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