//�� Ŭ���� ����:: ������ ���࿡ �ʿ��� SqlSession�� ���� �� �� �ְ� ���뼺
//�����Ͽ� ������ ��ü, Ư�� �� ��ü�� �ν��Ͻ��� ���ø����̼ǳ� 1���� �־� �ϹǷ�
//�̱��� SingleTon �������� ����
package com.model2.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	//2) �����ڸ� �������Ƿ�, ���� Ŭ�������� �ν��Ͻ��� �����ؾ���getter
	private static MybatisConfigManager instance;
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	
	//1) �����ڸ� ���, �ƹ��� new ���ϰ� �����
	private MybatisConfigManager() {
		String resource = "com/model2/mybatis/config/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//3) getter�� ����������, �ν��Ͻ� �޼����̹Ƿ� new�ϰ� ȣ�� �����ϱ� ������,
	//� �޼��嵵 ȣ�� �Ұ���
	//�ذ�å? new���� �ʾƵ� �Ʒ� �޼��� ȣ���� �� �ֵ��� static���� ����
	public static MybatisConfigManager getInstance() {
		if(instance==null) {//4�ܰ�:: �� �޼��� ȣ��� instance�� null�̸� ����
			instance = new MybatisConfigManager();
		}
		return instance;
	}
	
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public void close(SqlSession sqlSession) {
		if(sqlSession!=null) {
			sqlSession.close();
		}
	}
}
