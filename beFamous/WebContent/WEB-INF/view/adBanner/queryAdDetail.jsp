<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<h4>詳細廣告內容</h4>
	<table border="0" cellpadding="0" cellspacing="0"> 	
	<form>
		<td >banner類別:&nbsp 
		<c:if test="${adDetail.adType.id==1}">主banner</c:if>
		<c:if test="${adDetail.adType.id==2}">創作人主打banner</c:if>
		<c:if test="${adDetail.adType.id==3}">超樂活動banner</c:if></td><tr><tr>
		<td >活動名稱:&nbsp ${adDetail.activityName}</td><tr><tr>
		<td >檔案:&nbsp <img alt="" src="/${initParam.ImageWeb}/${adDetail.picture}"></td><tr><tr>
		<td >活動期間:&nbsp 
		<fmt:parseDate var="dateObj" value="${adDetail.activityStartDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />&nbsp~&nbsp 
		<fmt:parseDate var="dateObj" value="${adDetail.activityEndDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
		<td >連結網址:&nbsp <a href="http://${adDetail.website}">${adDetail.website}</a></td><tr><tr>
		<td >上架日期:&nbsp 
		<fmt:parseDate var="dateObj" value="${adDetail.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
		<td >下架日期:&nbsp 
		<fmt:parseDate var="dateObj" value="${adDetail.offDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
		<td >建立日期:&nbsp 
		<fmt:parseDate var="dateObj" value="${adDetail.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
		<td >刊登狀態:&nbsp 
		<c:if test="${adDetail.onStatus==1}">未刊登</c:if>
		<c:if test="${adDetail.onStatus==2}">刊登中</c:if>
		<c:if test="${adDetail.onStatus==3}">已刊登</c:if></td><tr><tr>
		<td >點閱數:&nbsp 30</td><tr><tr>	
		<td ><input type="button" value="編輯" onclick="modifyAdDetail('${admin}','${adDetail.id}')"></td><tr><tr>
	</form>
</table>	
</body>
<script type="text/javascript">
function modifyAdDetail(adminId, adId){
	location.href="${pageContext.request.contextPath}/queryAdDetail/modify/"+adminId+"/"+adId+".do";
}
</script>
</html>