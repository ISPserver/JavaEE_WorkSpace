package com.webApp1216.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webApp1216.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();//싱글톤 객체 얻기
	
	//insert
	public int insert(Notice notice) {
		int result=0;
		//jdbc가 아닌 mybatis로 쿼리문 수행
		SqlSession sqlSession = manager.getSqlSession();//쿼리 담당 객체(xml의 쿼리 호출가능)
		result = sqlSession.insert("Notice.insert",notice);
		sqlSession.commit();//DML인 경우 commit 처리
		manager.close(sqlSession);
		return result;
	}
	
	//select *
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}
	
	//select
	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession = manager.getSqlSession();
		notice = sqlSession.selectOne("Notice.select",notice_id);
		manager.close(sqlSession);
		return notice;
	}
	
	//update
	public int update(Notice notice) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Notice.update", notice);
		sqlSession.commit();
		manager.close(sqlSession);
		return result;
	}
	
	//delete
	public int delete(int notice_id) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Notice.delete",notice_id);
		sqlSession.commit();//DML이므로 commit;
		manager.close(sqlSession);
		return result;
	}
	
	
}
