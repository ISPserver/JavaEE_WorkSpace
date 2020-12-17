<%@page import="blood.model.BloodAdvisor"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//service메서드 영역 고양이가 이 jsp로 부터 생성한 서블릿 클래스에는
	//각종 예외가 throws 처리 되있기 때문에, try catch 안해도 됬던것
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Movie</title>
<style>
</style>
<script>
function send(){
	var form = document.querySelector("form");
	form.action="/movie.do";
	form.method="post";
	form.submit();
}

</script>
</head>
<body>
	<form>
		<select name="movie">
			<option>영화를 선택하세요</option>
			<option value="마녀">마녀</option>
			<option value="미션임파서블7">미션임파서블7</option>
			<option value="분노의질주">분노의질주</option>
			<option value="존윅">존윅</option>
		</select>
		<button type="button" onClick="send()">분석확인</button>
	</form>
</body>
</html>