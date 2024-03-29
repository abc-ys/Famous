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
<form name="form" method="post">
	<h4>修改訊息</h4>
	消息標題:&nbsp;<input type="text" name="newsName" value=${news.newsName}><br><br>
	消息來源:&nbsp;<input type="text" name="newsSouce" value=${news.newsSouce}><br><br>
	<fmt:parseDate var="createDate" value="${news.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 			
	建立日期:&nbsp;<input type="text" name="createDate" value=<fmt:formatDate value='${createDate}' pattern='yyyy-MM-dd' /> disabled="true"><br><br>
	內文:<textarea rows="6" cols="40" name="content">${news.content}</textarea><br><br>
	<input type="hidden" name="newsId" value=${news.id}>
	<input type="button" value="取消" onclick="cancleNews()">
	<c:if test="${news.onStatus ==1}"><input type="button" value="儲存文章" onclick="updatePublishNews()"></c:if>
	<c:if test="${news.onStatus ==2}"><input type="button" value="儲存文章" onclick="updateNews()"><input type="button" value="發佈文章" onclick="updatePublishNews()"></c:if>	
</form>
</body>
<script type="text/javascript">
function cancleNews(){
	window.close();
}
function updateNews(){
	document.form.action="${pageContext.request.contextPath}/updateNews.do";
	document.form.submit();	
}
function updatePublishNews(){
	document.form.action="${pageContext.request.contextPath}/updatePublishNews.do";
	document.form.submit();
}
</script>
</html>