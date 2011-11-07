<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h4>變更帳號</h4>
<form name="form">
 <input type="hidden" name="oldPassword" value=${password}><br><br>
	新帳號: <input type="text" name="newMemberId"><br><br>
	活動說明:<input type="text" name="activityContent" >
	<input type="button" value="確定修改" onclick="modify()">
	<input type="button" value="不想改了" onclick="window.close()">
</form>	
</body>
<script type="text/javascript">


function modify(){
	var newMemberId = document.form.newMemberId.value;
	var activityContent = document.form.activityContent.value;
	$.ajax({
	    url: '${pageContext.request.contextPath}/modifyAddBanner.do',
	    type: 'POST',
	    data: {newMemberId: newMemberId,activityContent:activityContent},
	    error: function(xhr) {},
	    success: function(response) {
	    	
	    	window.close();
	    	window.opener.location.reload();
		    }
	});
}
</script>
</html>