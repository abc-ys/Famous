<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gsimedia" uri="http://www.gsi-media.com/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/cell.css" type="text/css"/>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/js/cell.js"></script>
</head>
<body>
消息
<hr>
<table border="0">
<c:forEach var="news" items="${arNews}" >
<tr>
<td> 
<a href="javascript:void(0)" onclick="showNews('${news.id}')">${news.newsName}</a>
<br>
更新日期:
<c:if test="${news.modifyDate != null}">
<fmt:parseDate var="dateObj" value="${news.modifyDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font>
</c:if>
<c:if test="${news.modifyDate == null}">
<fmt:parseDate var="dateObj" value="${news.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font>
</c:if>
</td>
</tr>
<tr>
<td>
${news.content}
<a href="javascript:void(0)" onclick="showNews('${news.id}')">瀏覽全文</a>
</td>
</tr>
</c:forEach>
</table>

</body>
<script>
function showNews(newsId){
	window.open("${pageContext.request.contextPath}/newsDetail.do?newsId="+newsId);
}
</script>
</html>