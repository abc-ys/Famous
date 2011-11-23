<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<h4>新增廣告</h4> 
<form name="addAd" enctype="multipart/form-data" method="post">
	banner類別:
 	<select name="bannerType" id="bannerType">
 		<option selected="selected"></option>
		<option value="1" <c:if test="${Ad.adType.id==1}">selected</c:if>>主banner</option>
		<option value="2" <c:if test="${Ad.adType.id==2}">selected</c:if>>創作人主打banner</option>
		<option value="3" <c:if test="${Ad.adType.id==3}">selected</c:if>>超樂活動banner</option>
	</select><br><br>
	 活動名稱:<input type="text" name="activityName" value="${Ad.activityName}"><br><br>
	<c:if test="${Ad.picture!=null}">
	<img alt="" src="/${initParam.ImageWeb}/${Ad.picture}"><p></c:if>
	 檔案:<input type="file" name="file" size="20" ><br><br>
	 活動期間<input name="activityStartDate" type="text" class="fillbox" value="<fmt:parseDate var="dateObj" value="${Ad.activityStartDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;<A HREF="javascript:show_calendar('addAd.activityStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
	 			&nbsp-&nbsp<input name="activityEndDate" type="text" class="fillbox" value="<fmt:parseDate var="dateObj" value="${Ad.activityEndDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly >&nbsp;<A HREF="javascript:show_calendar('addAd.activityEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	 連結網址:<input type="text" name="website" value="${Ad.website}"><br><br>
	 上架日期:<input type="text" name="onDate" class="fillbox" value="<fmt:parseDate var="dateObj" value="${Ad.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;<A HREF="javascript:show_calendar('addAd.onDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	 下架日期:<input type="text" name="offDate" class="fillbox" value="<fmt:parseDate var="dateObj" value="${Ad.offDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;<A HREF="javascript:show_calendar('addAd.offDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	 建立日期:<input type="text" name="createDate" 
	 <c:if test="${Ad.createDate!=null}">value="<fmt:parseDate var="dateObj" value="${Ad.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />"</c:if>
	 <c:if test="${Ad.createDate==null}">value=<fmt:formatDate value="<%=new java.util.Date() %>"pattern ="yyyy-MM-dd"/></c:if>> <br><br>
	<input type="hidden" name="adminID" value="${adminID}" >
	<input type="hidden" name="picture" value="${Ad.picture}" >
	 <input type="button" value="儲存" onclick="onSubmit('${adminID}');">
</form>
</body>
<script type="text/javascript">
function onSubmit(adminID){
	document.addAd.action="${pageContext.request.contextPath}/saveAdBanner.do?adminID="+adminID ;
	document.addAd.submit();
}
</script>
</html>