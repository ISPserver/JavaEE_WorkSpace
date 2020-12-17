package movie.model;

public class MovieAdvisor {
	public String getAdvice(String movie) {
		String msg = null;
		if (movie != null) {
			if (movie.equals("마녀")) {
				msg = "마녀가 뭔데? 안봤는데 보고싶어서 리스트 추가";
			} else if (movie.equals("미션임파서블7")) {
				msg = "과연 성공할 수 있을까? 그 누구도 모를 예정이 시작된다!";
			} else if (movie.equals("분노의질주")) {
				msg = "진정한 상남자들의 질주가 시작된다!";
			} else if (movie.equals("존윅")) {
				msg = "존윅 빡빡이 대머리 깎아라";
			}			
		}
		return msg;
	}
}
