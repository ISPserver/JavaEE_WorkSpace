<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>
<body>
당신이 선택한 영화에 대한 분석 결과 <p>
<%=session.getAttribute("msg") %>
<a href="/movie/movie_form.jsp">선택화면으로 돌아가기</a>
</body>
</html>