<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css" >
.guestbookTable th{
	font-size: 16px;
}

.guestbookTable td{
	font-size: 13px;
}

.guestbookTable{
	border-color:yellow;
	margin:10pt;
}


#guestbookPagingDiv span {
	margin: 5px;
	padding: 5px;
	border: 1px white solid;
}

#currentPaging{
	color:red;
	cursor:pointer;
}

#paging{
	cursor:pointer;
}

#guestbookPagingDiv{
font-size:13pt;
margin-top: 10px;
}
</style>      
<div id="guestbookPagingDiv"></div>
<div id="guestbookListDiv"></div>
<input type="text" id="pg" value="${pg }">

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
$.ajax({
	type:'post',
	url:'/SpringProject/guestbook/getGuestbookList',
	data:'pg='+$('#pg').val(),
	dataType:'json',
	success:function(data){
		$.each(data.list,function(){
			$('<table/>',{
				class:"guestbookTable"
			}).append($('<tr/>')
			.append($('<th/>',{
				
			}))
		})
	}
	
})	
})
</script>