<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
#boardListTable th {
	font-size: 16px;
}

#boardListTable td {
	font-size: 13px;
}

#boardListTable {
	border-color: yellow;
	margin-left: 10pt;
}

.subjectA:link{ color: white; text-decoration: none; }
.subjectA:visited{ color: white; text-decoration: none;}
.subjectA:hover{ color: cyan; text-decoration: underline;}
.subjectA:active{ color: white; text-decoration: none;}

#boardPagingDiv{
	text-align: center;
	font-size: 13pt;
	margin-top: 20px;
}

#boardPagingDiv span {
	margin: 0 5px;
	padding: 10px;
	border: 1px white solid;
} 

#currentPaging {
	color: red;
	cursor: pointer;
}

#paging {
	color: white;
	cursor: pointer;
}
</style>

<input type="hidden" id="pg" value="${pg }">

<table id="boardListTable" border="1" cellspacing="0" cellpadding="5" frame="hsides" rules="rows">
	<tr>
		<th width="100">글번호</th>
		<th width="300">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
	
</table>
<div id="boardPagingDiv"></div>

<div id="boardSearchDiv" style="text-align: center; margin: 10px;" >
<form id="boardSearchForm" >
   <input type="hidden" id="searchPg" name="pg" value="1">
   <select id="searchOption" name="searchOption" >
      <option value="subject">제목</option>
      <option value="id">작성자</option>
   </select>
   <input type="text" id="keyword" name="keyword">
   <input type="button" id="boardSearchBtn" value="검색">
</form>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/SpringProject/js/boardList.js"></script>
<script type="text/javascript">
function boardPaging(pg2){
	var keyword = $('#keyword').val();
		
	if(keyword == ''){
		location.href = '/SpringProject/board/boardList?pg='+pg2;
		
	}else{
		$('#searchPg').val(pg2); //<form 안에 searchPg의 값이 1로 고정되어 있기 때문에
		$('#boardSearchBtn').trigger('click'); //강제 이벤트 발생
		
		//다시 검색 버튼을 눌렀을 때 1페이지부터 검색을 할 수 있도록 pg를 1로 바꾸어야한다.
		$('#searchPg').val(1);
	}

}
</script>





















