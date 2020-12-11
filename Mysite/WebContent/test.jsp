<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@ page import="java.sql.*,
javax.sql.*,
java.io.*,
javax.naming.InitialContext,
javax.naming.Context" %>
</head>
<body>
<h1>JDBC JNDI Resource Test</h1>

<%
/*
JNDI란?
java Naming Directory Interface : 어떤 정보를 프로그래밍 언어인 자바코드에 넣지말고,
외부의 xml과 같은 자원으로 관리하는 방법(즉, 자바코드말고 )
*/

InitialContext initCtx = new InitialContext(); //검색 객체

DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/myoracle");

//새로운 Connection이 아니라, 이미 pool에 존재하는 접속 객체를 얻어오는것임
Connection conn = ds.getConnection();//커넥션풀로부터 하나의 커넥션을 얻는 작업
Statement stmt = conn.createStatement();
ResultSet rset = stmt.executeQuery("select * from board");
while (rset.next()) {
	out.println("title=="+rset.getString("title")+"<br>");
}
rset.close();
stmt.close();
conn.close();//여기서 pool로 다시 돌려보내는것(종료가 아님)
initCtx.close();
%>
</body>
</html>