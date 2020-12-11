<%@ page contentType="text/html;charset=utf-8"%>
<%!
	//***재사용성이 높은 jsp 코드를 이 파일에 메서드로 모아놓음***

	//선언부에는 멤버변수와 메서드를 정의할 수 있다
	public String getMsgBack(String msg){ //alert, 이전페이지 보여주기
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");

		return sb.toString();
	}

	public String getMsgURL(String msg, String url){ //alert, 원하는 페이지 요청
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		//게시물 한건을 보기 위한 링크
		sb.append("location.href='"+url+"';");
		sb.append("</script>");

		return sb.toString();
	}
%>