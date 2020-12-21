<%@page import="com.model2.domain.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//포워딩이 요청을 유지했는지 테스트
	//유저가 선택한 글의 pk 넘겨받기
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
.reply-box{
	background:yellow;
}
.reply-list p{
	overflow: hidden;
}
.reply-list p{
	float: left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
$(function(){ //onload
	CKEDITOR.replace("content");//textarea의 id가 content인것에 ckEditor 설정	
	
	$($("input[type='button']")[0]).click(function() {
		edit();
	})
	
	getCommentList();//비동기로 댓글목록 가져오기
})

function edit(){
	$("form").attr({		
		action:"/board/edit.do",
		method:"post"
	});
	$("form").submit();
}
//댓글 목록 가져오기
function getCommentList(){
	$.ajax({
		url:"/comment/list.do",
		type:"get",
		data:{
			board_id:<%=board.getBoard_id()%>
		},
		success:function(result){
			//서버에서 전송되어온 데이터는 json 객체가 아닌 문자열임			
			console.log(result.list);
			//result에는 서버에서 전송한 json 배열이 들어있음 
			
			$("#list-area").html(""); //초기화 시킨 후
			
			var tag="";
			for(var i=0; i<result.list.length; i++){
				var json = result.list[i];
				tag+="<div class=\"reply-list\">";
				tag+="<p style=\"width:75%\">"+json.msg+"</p>";
		    	tag+="<p style=\"width:15%\">"+json.author+"</p>";
		    	tag+="<p style=\"width:10%\">"+json.cdate+"</p>";
		    	tag+="</div>";
			}
	    	$("#list-area").html(tag); //innerHTML과 동일
		}
	})	;
}

//현재 페이지가 새로고침(reloading 되지 않게, 비동기 ajax로 등록요청)
function registComment(){
	$.ajax({
		url:"/comment/regist.do",
		type:"post",
		data:{
			msg: $("input[name='msg']").val(),
			author:$("input[name='author']").val(),
			board_id: <%=board.getBoard_id()%>
		},
		//피드백은 success로 받는다. 즉 서버에서 에러 없이 데이터가 결과값이 전송되면
		//success 우측에 명시된 익명함수가 동작
		success: function(result){
			if(result==1){
				getCommentList();
			}else{
				alert("등록실패");
			}
		}
		
	});
}


</script>
</head>
<body>

<h3>글 상세보기</h3>

<div class="container">
  <form>
    <input type="hidden" name="board_id" value="<%=board.getBoard_id() %>">    
    
    <input type="text" name="title" value="<%=board.getTitle()%>">
    <input type="text" name="writer" value="<%=board.getWriter()%>">
    <textarea name="content"  style="height:200px"><%=board.getContent() %></textarea>    

        
    <input type="button" value="수정하기">
    <input type="button" value="삭제하기">
    <input type="button" value="목록보기" onClick="location.href='/board/list.do'">
    <div class="reply-box">
    	<input type="text" name="msg" placeholder="댓글 입력..." style="width: 75%">
    	<input type="text" name="author" placeholder="작성자 입력..." style="width: 15%">
    	<button type="button" onClick="registComment()">댓글등록</button>
    </div>
    <div id="list-area"></div>
  </form>
</div>

</body>
</html>