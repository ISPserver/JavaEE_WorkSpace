//이 클래스 목적:: 쿼리문 수행에 필요한 SqlSession을 쉽게 얻어갈 수 있게 재사용성
//고려하여 정의한 객체, 특히 이 객체의 인스턴스는 어플리케이션내 1개만 둬야 하므로
//싱글톤 SingleTon 패턴으로 정의
package com.model2.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	//2) 생성자를 묶었으므로, 현재 클래스에서 인스턴스를 제공해야함getter
	private static MybatisConfigManager instance;
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	
	//1) 생성자를 묶어서, 아무나 new 못하게 만들기
	private MybatisConfigManager() {
		String resource = "com/model2/mybatis/config/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//3) getter는 제공했으나, 인스턴스 메서드이므로 new하고 호출 가능하기 때문에,
	//어떤 메서드도 호출 불가능
	//해결책? new하지 않아도 아래 메서드 호출할 수 있도록 static으로 정의
	public static MybatisConfigManager getInstance() {
		if(instance==null) {//4단계:: 이 메서드 호출시 instance가 null이면 생성
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

