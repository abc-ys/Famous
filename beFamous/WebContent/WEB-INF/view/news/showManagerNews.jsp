<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
	消息類別:&nbsp;${news.newsCategory}<p>
	消息標題:&nbsp;${news.newsName}<p>
	圖片:&nbsp;<c:if test="${news.picture==' '}"><img alt="" src="/${initParam.ImageWeb}/${news.picture}" width="200" height="100"></c:if><p>
	消息來源:&nbsp;${news.newsSouce}<p>
	<fmt:parseDate var="onDate" value="${news.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	上架日期:&nbsp;<fmt:formatDate value='${onDate}' pattern='yyyy-MM-dd' /><p>
	<fmt:parseDate var="createDate" value="${news.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	建立日期:&nbsp;<fmt:formatDate value='${createDate}' pattern='yyyy-MM-dd' /><p>
	內文:<br>${news.content}<p>
</body>
</html>