/*
 * 커넥션 풀 사용을 보다 편하게 처리해야 DAO에서 고생을 안한다.
 * */
package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
	InitialContext context; //JNDI 검색을 담당하는 객체
	DataSource ds;//커넥션 풀
	private static PoolManager instance;
	
	//이 시점부터는 아무도 new 할 수 없음
	private PoolManager() {
		try {
			context = new InitialContext();//검색객체 생성
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle");//찾기 성공 and 풀반환
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}//하지만, 사용할 수 있게 인스턴스를 가져갈 기회를 현재클래스에 부담하기
	
	public static PoolManager getInstance() {
		if(instance==null) {
			instance = new PoolManager();//싱글톤 패턴
		}
		return instance;
	}
	
	
	//커넥션이 필요한 자에게 Connection을 반환해주는 (대여)메서드
	public Connection getConnection() {
		Connection con=null;
		try {
			con = ds.getConnection();//대여
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//반납
	public void release(Connection con){
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	//반납
	public void release(Connection con,PreparedStatement pstmt){
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	//반납
	public void release(Connection con,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}
