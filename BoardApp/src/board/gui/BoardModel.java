package board.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import board.model.Notice;

public class BoardModel extends AbstractTableModel{
	String[] column = {"notice_id","작성자","제목","등록일","조회수"};
	ArrayList<Notice> list = new ArrayList<Notice>();
	public int getRowCount() {
		return list.size();
	}
	
	public int getColumnCount() {
		return column.length;
	}
	
	public String getColumnName(int col) {
		return column[col];
	}

	public Object getValueAt(int row, int col) {
		Notice notice = list.get(row);//각 방에 있는 VO 추출
		String obj = null;
		
		if(col==0) {
			obj = Integer.toString(notice.getNotice_id());
		}else if(col==1) {//작성자
			obj = notice.getAuthor();
		}else if(col==1) {//제목
			obj = notice.getTitle();
		}else if(col==1) {//등록일
			obj = notice.getRegdate();
		}else if(col==1) {//조회수
			obj = Integer.toString(notice.getHit());
		}
		return obj;
	}
	
}
