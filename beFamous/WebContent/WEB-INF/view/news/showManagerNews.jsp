<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
	消息類別:&nbsp;${news.newsCategory}<p>
	消息標題:&nbsp;${news.newsName}<p>
	圖片:&nbsp;<img alt="" src="/${initParam.ImageWeb}/${news.picture}" width="200" height="100"><p>
	消息來源:&nbsp;${news.newsSouce}<p>
	上架日期:&nbsp;${news.onDate}<p>
	建立日期:&nbsp;
	<fmt:parseDate var="dateObj" value="${news.createDate}" type="DATE" pattern="yyyyMMddhhmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /><p>
	內文:<br>${news.content}<p>
</body>
</html>