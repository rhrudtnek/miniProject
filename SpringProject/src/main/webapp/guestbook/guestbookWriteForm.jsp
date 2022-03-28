<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<style type="text/css">
#guestbookWriteForm div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>
<h3>글쓰기</h3>
<form name="geustbookWriteForm" id="geustbookWriteForm">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">작성자</td>
			<td>
				<input type="text" name="name" id="name" size="30" placeholder="작성자 입력">
				<div id="nameDiv"></div>
			</td>
		</tr>
		<tr>
			<td width="100" align="center">이메일</td>
			<td>
				<input type="text" placeholder="이메일 입력" name="email" id="email">
				<div id="emailDiv"></div>
			</td>
		</tr>
		<tr>
			<td width="100" align="center">홈페이지</td>
			<td>
				<input type="text" placeholder="http://" name="homepage" id="homepage">
				<div id="homepageDiv"></div>
			</td>
		</tr>
		<tr>
			<td width="100" align="center">제목</td>
			<td>
				<input type="text" name="subject" id="subject" size="30" placeholder="제목입력">
				<div id="subjectDiv"></div>
			</td>
		</tr>
		<tr>
			<td width="100" align="center">내용</td>
			<td>
				<textarea rows="15" cols="50" placeholder="내용입력" name="content" id="content"></textarea>
				<div id="contentDiv"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" id="boardWriteBtn" value="글쓰기"> 
			<input type="reset" id="resetBtn" value="다시작성">
			<input type="button" id="back" value="뒤로가기" onclick="history.back()">
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$('#boardWriteBtn').click(function() {
		$('#subjectDiv').empty();
		$('#contentDiv').empty();
		
		if ($('#subject').val() == '')
			$('#subjectDiv').text('제목를 입력하세요');
		else if ($('#content').val() == '')
			$('#contentDiv').text('글내용을 입력하세요');
		else{
			$.ajax({
				type: 'post',
				url: '/SpringProject/guestbook/guestbookWrite',
				data: {
					'name': $('#name').val(),
					'email': $('#email').val(),
					'homepage': $('#homepage').val(),
					'subject': $('#subject').val(),
					'content': $('#content').val()
				},
				success: function(){
					alert('방명록 쓰기 성공');
					location.href = '/SpringProject/guestbook/guestbookList'
				},
				error: function(err){
					alert(err);
				}
			});
		}
			
	});
	
	$('#resetBtn').click(function(){
		$('#nameDiv').empty();
		$('#emailDiv').empty();
		$('#homepageDiv').empty();
		$('#subjectDiv').empty();
		$('#contentDiv').empty();
		
	});
</script>
</body>
</html>