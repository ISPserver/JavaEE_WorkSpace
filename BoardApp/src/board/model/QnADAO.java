package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
/*1.기존에 내가본글보다 rank가 큰 글의 rank는 모두 1씩 증가되시오!! (공간확보 )
update  qna  rank=rank+1 where rank > 내본글 rank and 
team=내본team

2.빈 공간을 내가 차지!!(답변)
insert  qna(~team, rank, depth) values(내본team,내본rank+1,내본depth+1)
*/

public class QnADAO {
	DBManager dbManager = new DBManager();
	
	//insert : 원글 등록(첫번째)
	public int insert(QnA qna) {
		Connection con=null;
		PreparedStatement pstmt = null;		
		int result=0;
		
		String sql="insert into qna(writer,title,content) values(?,?,?)";		
		try {
			con = dbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			result = pstmt.executeUpdate();//실행
			
			//team을 방금 들어간 레코드에 의해 발생한 pk 값으로 업데이트
			sql="update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt = con.prepareStatement(sql);//pstmt는 쿼리문 1:1 대응
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	//답변글
	public int reply() {
		int result=0;
		return result;
	}
	//selectAll
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnA> list = new ArrayList<QnA>();
		String sql="select * from qna order by team desc, rank asc";
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				QnA qna = new QnA();//레코드만큼 VO 생성해야함
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
				
				list.add(qna); //리스트에 추가
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(con,pstmt,rs);
		}
		return list;
	}
	//select
	public QnA select(int qna_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnA qna = null;
		
		String sql="select * from qna where qna_id=?";
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);//바인드 변수 값 지정
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//레코드가 있다면
				qna = new QnA();//레코드만큼 VO 생성해야함
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));								
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.release(con,pstmt,rs);
		}
		return qna;
	}
	//update
	public int update() {
		int result=0;
		return result;
	}
	//delete
	public int delete() {
		int result=0;
		return result;
	}
}
