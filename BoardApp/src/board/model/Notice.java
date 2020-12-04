package board.model;

import java.sql.ResultSet;

//DTO(Data Transfer Object-데이터 전달객체),
//VO(Value Object-데이터 담는 객체)는 데이터 1건을 담기 위한 객체이다. 
public class Notice {
	private int notice_id;
	private String author;
	private String title;
	private String content;
	private String regdate;
	private int hit;	
	private ResultSet resultSet;
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
