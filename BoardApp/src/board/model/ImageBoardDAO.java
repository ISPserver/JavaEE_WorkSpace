/*ImageBoard 테이블에 대해 CRUD만을 전담하는 DAO 정의*/
package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class ImageBoardDAO {
	DBManager dbManager = new DBManager();//ImageBoardDAO인스턴스가 실행될때
																		 //DBManager의 인스턴스도 같이 생성됨
	
	//create(insert)
	public int insert(ImageBoard board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result = 0;
		String sql="insert into imageboard(author,title,content,filename) values(?,?,?,?)";
		
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getAuthor());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	//selectAll()
	public ArrayList<ImageBoard> selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from imageboard";
		ArrayList<ImageBoard> list = new ArrayList<ImageBoard>();
		
		con=dbManager.getConnection();
		try {			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ImageBoard board = new ImageBoard();
				board.setBoard_id(rs.getInt("board_id"));
				board.setAuthor(rs.getString("author"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				board.setFilename(rs.getString("filename"));
				
				list.add(board);//ArrayList에 VO 추가
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//select
	public ImageBoard select(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql="select * from imageboard where board_id=?";
		ImageBoard board=null;
		
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//레코드가 존재하는 것임!! 따라서 이때 VO를 올리자!!!!!!
				board = new ImageBoard(); //텅빈 empty상태의 vo 생성
				//notice에  rs 의 정보를 모두~~~옮겨심자!!!
				board.setBoard_id(rs.getInt("board_id"));
				board.setAuthor(rs.getString("author"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				board.setFilename(rs.getString("filename"));
			}
			
			//조회수 증가
			sql="update notice set hit=hit+1 where board_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return board;
	}
	
	//update
	public int update(ImageBoard board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		String sql="update imageboard set author=?, title=?, content=?, filename=? where board_id=?";
		
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getAuthor());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			pstmt.setInt(5, board.getBoard_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//delete
	public int delete(int board_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		String sql="delete from imageboard where board_id=?";
		con = dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		
		return result;
	}
	
}
