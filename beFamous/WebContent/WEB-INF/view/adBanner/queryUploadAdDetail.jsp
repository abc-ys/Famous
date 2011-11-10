<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<h4>詳細上傳廣告內容</h4>
<form name="fm" method="post">
	<table>
	<td>會員帳號:${adDetail[0].memberCreator.email}&nbsp&nbsp&nbsp專輯數:${adDetail[1]}</td><tr><tr>
	<td>banner類別:&nbsp ${adDetail[0].adType.adTypeName}</td><tr><tr>
	<td>活動名稱:&nbsp ${adDetail[0].activityName}</td><tr><tr>
	<td>檔案:&nbsp <img alt="" src="/${initParam.ImageWeb}/${adDetail[0].picture}"></td><tr><tr>
	<td>活動期間:&nbsp ${adDetail[0].activityStartDate}&nbsp~&nbsp ${adDetail[0].activityEndDate}</td><tr><tr>
	<td>連結網址:&nbsp ${adDetail[0].website}</td><tr><tr>
	<td>建立日期:&nbsp ${adDetail[0].createDate}</td><tr><tr>
	<td>活動說明:&nbsp ${adDetail[0].activityContent}</td><tr><tr>
	<td>審核狀態:
	<select name="status" id="status" onchange="addReason('${admin.adminId}','${adDetail[0].adId}')">		
		<c:if test="${adDetail[0].checkStatus == '尚未審核'}">				
				<option value="尚未審核" selected="selected">尚未審核</option>
				<option value="審核成功" >審核成功</option>	
				<option value="審核失敗" >審核失敗</option>								
		</c:if>
		<c:if test="${adDetail[0].checkStatus == '審核成功'}">				
				<option value="審核成功" selected="selected">審核成功</option>
				<option value="尚未審核" >尚未審核</option>				
				<option value="審核失敗" >審核失敗</option>	
		</c:if>
		<c:if test="${adDetail[0].checkStatus == '審核失敗'}">				
				<option value="審核失敗" selected="selected">審核失敗</option>
				<option value="尚未審核" >尚未審核</option>		
				<option value="審核成功" >審核成功</option>			
		</c:if>
	</select></td><tr><tr>
	<td><c:if test="${adDetail[0].checkStatus == '審核失敗'}">	
		失敗理由:&nbsp ${adDetail[0].note}
	</c:if></td><tr><tr>
	<input type="hidden" name="checkStatus" id="checkStatus"><tr><tr>
	<input type="hidden" name="adminId" id="adminId" value=${admin.adminId}><tr><tr>		
	<input type="hidden" name="adId" id="adId" value=${adDetail[0].adId}><tr><tr>
	</table>
</form>	
</body>
<script type="text/javascript">
function addReason(adminId, adId){
	document.fm.checkStatus.value = document.getElementById("status").value;
	if(document.getElementById("status").value=="審核失敗")
	{			
		window.open("${pageContext.request.contextPath}/addCheckReason/"+adminId+"/"+adId+".do",'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');
	}else if(document.getElementById("status").value=="審核成功"){
		if (confirm("儲存")){
			document.fm.action="${pageContext.request.contextPath}/publishAd.do";
			document.fm.submit();
		}else{
			
		}		
	}  
}
</script>
</html>