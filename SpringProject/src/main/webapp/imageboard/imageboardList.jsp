<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
#imageboardListTable th {
	font-size: 16px;
}

#imageboardListTable td {
	font-size: 13px;
}

#imageboardListTable {
	border-color: yellow;
	margin-left: 10pt;
}

#currentPaging {
	color: red;
	text-decoration: underline;
	cursor: pointer;
}

#paging {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.imageNameA:link {
	color: white;
	text-decoration: none;
}

.imageNameA:visited {
	color: white;
	text-decoration: none;
}

.imageNameA:hover {
	color: cyan;
	text-decoration: underline;
}

.imageNameA:active {
	color: cyan;
	text-decoration: none;
}
</style>

<form id="imageboardListForm" method=""
	action="/SpringProject/imageboard/imageboardDelete">

	<input type="hidden" id="pg" value="${pg }">

	<table id="imageboardListTable" border="1" cellspacing="0"
		cellpadding="5" frame="hsides" rules="rows">
		<tr>
			<th width="100"><input type="checkbox" id="all">글번호</th>
			<th width="100">이미지</th>
			<th width="150">상품명</th>
			<th width="150">단가</th>
			<th width="100">개수</th>
			<th width="150">합계</th>
		</tr>
	</table>

	<input id="imageboardDeleteBtn" type="button" value="선택삭제"
		style="float: left; margin: 5px 10px;">

	<div id="imageboardPagingDiv"
		style="text-align: center; width: 750px; font-size: 15pt;"></div>
</form>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function imageboardPaging(pg2){
	location.href='/SpringProject/imageboard/imageboardList?pg=' + pg2;
}
</script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: 'post',
		url: '/SpringProject/imageboard/getImageboardList',
		data: 'pg='+$('#pg').val(), //hidden의 값 얻어오기
		//data: 'pg=${pg}', - 컨트롤러로부터 넘어오는 값
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.seq
					
					}).prepend($('<input/>',{
						type: 'checkbox',
						name: 'check',
						value: items.seq,
					}))
				
				).append($('<td/>',{
					align: 'center',
					}).append($('<img/>',{
						src: '/SpringProject/storage/' + items.image1,
						style: 'width: 70px; height: 70px; cursor: pointer;',
						//class: 'img_'+items.seq //이미지 클릭 했을 때 - 첫번째
						class: 'img' //이미지 클릭 했을 때 - 두번째
					}))
				).append($('<td/>',{
					align: 'center',
					text: items.imageName
					
				})).append($('<td/>',{
					align: 'center',
					text: items.imagePrice.toLocaleString()
					
				})).append($('<td/>',{
					align: 'center',
					text: items.imageQty.toLocaleString()
					
				})).append($('<td/>',{
					align: 'center',
					text: (items.imagePrice * items.imageQty).toLocaleString()
					
				})).appendTo($('#imageboardListTable'));
				
				//이미지 클릭 했을 때 - 첫번째
				$('.img_'+items.seq).click(function(){
					alert('번호 = ' + items.seq);
					location.href = '/SpringProject/imageboard/imageboardView?seq='+items.seq+'&pg='+$('#pg').val();
				});
			
			});//$.each
			
			//이미지 클릭 했을 때 - 두번째
			$('.img').click(function(){
				//alert($(this).parent().prev().prop('tagName'));
				//alert($(this).parent().prev().text());
				
				var seq = $(this).parent().prev().text();
				location.href = '/SpringProject/imageboard/imageboardView?seq='+seq+'&pg='+$('#pg').val();
			});
			
			//페이징 처리
			$('#imageboardPagingDiv').html(data.imageboardPaging.pagingHTML);
		},
		error: function(err){
			console.log(err);
		}
	}); //$.ajax
});

//전체 선택 또는 해제
$('#all').click(function(){
	//alert($('#all').attr('checked')) - checked라는 속성이 없어서 undefind로 나온다
	//alert($('#all').prop('checked')) - true 또는 false
	
	if($('#all').prop('checked')){
		$('input[name=check]').prop('checked', true);
	}else{
		$('input[name=check]').prop('checked', false);
	}
});
//선택삭제
$('#imageboardDeleteBtn').click(function(){
	var count = ($('input[name=check]:checked').length); //checked 된 개수를 구하기
	
	if(count == 0)
		alert('삭제할 항목을 선택하세요')
	else
		if(confirm('정말로 삭제하시겠습니까?'))
            $('#imageboardListForm').submit();

});

</script>



<%--
attr()
- HTML에 작성된 속성값을 문자열로 가져온다

prop()
- 자바스크립트이 프로퍼티를 가져온다
- 자바스크립트의 프로퍼티 값이 넘어오므로 boolean, date, function등을 가져올 수 있다.

[형식]
prop(key) 			-> 속성값을 가져온다
prop(key, value)	-> 속성값을 추가한다

 --%>


