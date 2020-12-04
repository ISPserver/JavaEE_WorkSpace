package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardContent extends Page{
	JTextField t_author;
	JTextField t_title;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_list, bt_edit, bt_del;
	Notice notice;
	BoardList boardList;
	NoticeDAO noticeDAO;
	
	public BoardContent(BoardMain boardMain) {
		super(boardMain);		
		//생성
		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_list = new JButton("목록으로");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		noticeDAO = new NoticeDAO();
		
		//스타일
		t_author.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		t_title.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 500));
		
		//조립
		add(t_author);
		add(t_title);
		add(scroll);
		
		add(bt_list);
		add(bt_edit);
		add(bt_del);
		
		//목록 버튼에 리스너 연결
		bt_list.addActionListener((e)-> {
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		});
		
		//수정 버튼에 리스너 연결
		bt_edit.addActionListener((e)-> {
			if(JOptionPane.showConfirmDialog(BoardContent.this, "수정하시겠습니까?")==JOptionPane.OK_OPTION) {
				edit();				
			}		
		});
		
		bt_del.addActionListener((e)-> {
			del();
		});
	}
	//삭제하고 목록 보여주기
	public void del() {
		int result = noticeDAO.delete(notice.getNotice_id());
		if(result==0) {
			JOptionPane.showMessageDialog(BoardContent.this, "삭제 실패");
		}else {
			JOptionPane.showMessageDialog(BoardContent.this, "삭제 성공");
			BoardList boardList = (BoardList)boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
			boardList.getList();//데이터 가져오기
			boardList.table.updateUI();//화면 갱신
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		}
	}
	public void edit() {
		//DAO를 이용해 수정작업 실행
		notice.setAuthor(t_author.getText());
		notice.setTitle(t_title.getText());
		notice.setContent(area.getText());
		int result = noticeDAO.update(notice);
		if(result==0) {
			JOptionPane.showMessageDialog(this, "수정성공");
		}else {
			JOptionPane.showMessageDialog(this, "수정성공");
			BoardList boardList = (BoardList)boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
			boardList.getList();//데이터 가져오기
			boardList.table.updateUI();//화면 갱신
		}		
		boardMain.showPage(Pages.valueOf("BoardList").ordinal());
	}
	//컴포넌트에 데이터 채워넣기
	//이 메서드를 호출하는 자는, 한 건의 데이터를 VO에 담아서 호출하면 된다.
	public void setData(Notice notice) {
		this.notice=notice;//나중에 써먹을거 대비해서 보관
		t_author.setText(notice.getAuthor());
		t_title.setText(notice.getTitle());
		area.setText(notice.getContent());
	}
}
