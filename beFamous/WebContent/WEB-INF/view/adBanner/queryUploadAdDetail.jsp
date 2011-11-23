<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
	<td>會員帳號:${adDetail.memberCreator.email}&nbsp&nbsp&nbsp&nbsp專輯數:&nbsp 30</td><tr><tr>
	<td>banner類別:&nbsp 
	<c:if test="${adDetail.adType.id==1}">主banner</c:if>
	<c:if test="${adDetail.adType.id==2}">創作人主打banner</c:if>
	<c:if test="${adDetail.adType.id==3}">超樂活動banner</c:if></td><tr><tr>
	<td>活動名稱:&nbsp ${adDetail.activityName}</td><tr><tr>
	<td>檔案:&nbsp <img alt="" src="/${initParam.ImageWeb}/${adDetail.picture}"></td><tr><tr>
	<td>活動期間:&nbsp 
	<fmt:parseDate var="dateObj" value="${adDetail.activityStartDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />&nbsp~&nbsp 
	<fmt:parseDate var="dateObj" value="${adDetail.activityEndDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
	<td>連結網址:&nbsp ${adDetail.website}</td><tr><tr>
	<td>建立日期:&nbsp 
	<fmt:parseDate var="dateObj" value="${adDetail.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
	<td>活動說明:&nbsp ${adDetail.activityContent}</td><tr><tr>
	<td>審核狀態:
	<select name="status" id="status" onchange="addReason('${adminId}','${adDetail.id}')">		
		<c:if test="${adDetail.checkStatus == '1'}">				
				<option value="1" selected="selected">尚未審核</option>
				<option value="2" >審核成功</option>	
				<option value="3" >審核失敗</option>								
		</c:if>
		<c:if test="${adDetail.checkStatus == '2'}">				
				<option value="2" selected="selected">審核成功</option>
				<option value="1" >尚未審核</option>				
				<option value="3" >審核失敗</option>	
		</c:if>
		<c:if test="${adDetail.checkStatus == '3'}">				
				<option value="3" selected="selected">審核失敗</option>
				<option value="1" >尚未審核</option>		
				<option value="2" >審核成功</option>			
		</c:if>
	</select></td><tr><tr>
	<td><c:if test="${adDetail.checkStatus == '3'}">	
		失敗理由:&nbsp ${adDetail.note}
	</c:if></td><tr><tr>
	<input type="hidden" name="checkStatus" id="checkStatus"><tr><tr>
	<input type="hidden" name="adminId" id="adminId" value=${adminId}><tr><tr>		
	<input type="hidden" name="adId" id="adId" value=${adDetail.id}><tr><tr><tr><tr>
	</table>
</form>	
</body>
<script type="text/javascript">
function addReason(adminId, adId){
	document.fm.checkStatus.value = document.getElementById("status").value;
	if(document.getElementById("status").value=="3")
	{			
		window.open("${pageContext.request.contextPath}/addCheckReason/"+adminId+"/"+adId+".do",'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');
	}else if(document.getElementById("status").value=="2"){
		if (confirm("儲存")){
			document.fm.action="${pageContext.request.contextPath}/publishAd.do";
			document.fm.submit();
		}else{
			
		}		
	}  
}
</script>
</html>