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
<h4>詳細廣告內容</h4>
<form name="modifyAd" enctype="multipart/form-data" method="post">
	<table border="0" cellpadding="0" cellspacing="0"> 	
		<td>banner類別:		
			<select name="bannerType" id="bannerType">		
			<c:if test="${adDetail.adType.id == '1'}">				
					<option value="1" selected="selected">主banner</option>
					<option value="2" >創作人主打banner</option>	
					<option value="3" >超樂活動banner</option>								
			</c:if>
			<c:if test="${adDetail.adType.id == '2'}">				
					<option value="1" selected="selected">創作人主打banner</option>
					<option value="2" >主banner</option>				
					<option value="3" >超樂活動banner</option>	
			</c:if>
			<c:if test="${adDetail.adType.id == '3'}">				
					<option value="1" selected="selected">超樂活動banner</option>
					<option value="2" >主banner</option>		
					<option value="3" >創作人主打banner</option>			
			</c:if>
			</select>
		</td><tr><tr>
		<td>活動名稱:&nbsp <input type="text" name="activityName" value='<c:out value="${adDetail.activityName}"></c:out>'></td><tr><tr>
		<input type="hidden" name="picture" value="${adDetail.picture}">
		<td>檔案:&nbsp <img alt="" src="${adDetail.picture}"></td><tr><tr>
		<td><input type="file" name="file" size="20"></td><tr><tr>
		<td>活動期間:&nbsp <input name="activityStartDate" type="text" value="<fmt:parseDate var="dateObj" value="${adDetail.activityStartDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('modifyAd.activityStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
		  &nbsp~&nbsp <input name="activityEndDate" type="text" value="<fmt:parseDate var="dateObj" value="${adDetail.activityEndDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" class="fillbox" readonly >&nbsp;<A HREF="javascript:show_calendar('modifyAd.activityEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
		</td><tr><tr>
		<td>連結網址:&nbsp <input type="text" name="website" value='<c:out value="${adDetail.website}"></c:out>'></td><tr><tr>
		<td>上架日期:&nbsp <input type="text" name="onDate"  value="<fmt:parseDate var="dateObj" value="${adDetail.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('modifyAd.onDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td><tr><tr>
		<td>下架日期:&nbsp <input type="text" name="offDate"  value="<fmt:parseDate var="dateObj" value="${adDetail.offDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('modifyAd.offDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td><tr><tr>
		<td>建立日期:&nbsp 
		<fmt:parseDate var="dateObj" value="${adDetail.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td><tr><tr>
		<input type="hidden" name="picture" id="picture" value="${adDetail.picture}">
		<input type="hidden" name="adminId" id="adminId" value="${admin}"><tr><tr>		
		<input type="hidden" name="adId" id="adId" value="${adDetail.id}"><tr><tr>	
		<td><input type="button" value="儲存" onclick="saveAdDetail(${admin})"></td><tr><tr>
	</table>
</form>	
</body>
<script type="text/javascript">
function saveAdDetail(adminID){
	document.modifyAd.action="${pageContext.request.contextPath}/saveAdDetail.do?adminID="+adminID;
	document.modifyAd.submit();
}
</script>
</html>