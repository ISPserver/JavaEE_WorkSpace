package movie.model;

public class MovieAdvisor {
	public String getAdvice(String movie) {
		String msg = null;
		if (movie != null) {
			if (movie.equals("����")) {
				msg = "���డ ����? �Ⱥôµ� ����; ����Ʈ �߰�";
			} else if (movie.equals("�̼����ļ���7")) {
				msg = "���� ������ �� ������? �� ������ �� ������ ���۵ȴ�!";
			} else if (movie.equals("�г�������")) {
				msg = "������ ���ڵ��� ���ְ� ���۵ȴ�!";
			} else if (movie.equals("����")) {
				msg = "���� ������ ��Ӹ� ��ƶ�";
			}			
		}
		return msg;
	}
}
