package com.webApp1216.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webApp1216.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();//�̱��� ��ü ���
	
	//insert
	public int insert(Notice notice) {
		int result=0;
		//jdbc�� �ƴ� mybatis�� ������ ����
		SqlSession sqlSession = manager.getSqlSession();//���� ��� ��ü(xml�� ���� ȣ�Ⱑ��)
		result = sqlSession.insert("Notice.insert",notice);
		sqlSession.commit();//DML�� ��� commit ó��
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
		sqlSession.commit();//DML�̹Ƿ� commit;
		manager.close(sqlSession);
		return result;
	}
	
	
}
