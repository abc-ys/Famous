<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="form">
	<h4>審核失敗理由</h4><tr>
	請填入審核失敗理由:
	<textarea rows="6" cols="40" name="reason" id="reason"></textarea><tr><tr>	
	<input type="button" value="送出" onclick="saveModification()"><tr>
	<input type="hidden" name="adminId" id="adminId" value=${admin}><tr>		
	<input type="hidden" name="adId" id="adId" value=${adId}><tr>		
</form>
</body>
<script type="text/javascript">
function saveModification(adminId, adId){
	var content = document.all.reason.value;
	if(content == ""){
		alert("請輸入審核失敗理由");
	}else{
		document.form.action="${pageContext.request.contextPath}/saveCheckReason.do";
		document.form.submit();
	}	
}
</script>
</html>