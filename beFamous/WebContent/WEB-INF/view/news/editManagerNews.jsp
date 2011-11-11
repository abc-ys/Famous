<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<form name="form"  enctype="multipart/form-data"  method="post">
	<h4>編輯消息</h4>
	消息類別:&nbsp;<select name="newsCategory">
	<option value="表演" <c:if test="${news.newsCategory == '表演'}">selected</c:if>>表演</option>
	<option value="公告" <c:if test="${news.newsCategory == '公告'}">selected</c:if>>公告</option>
	<option value="新聞" <c:if test="${news.newsCategory == '新聞'}">selected</c:if>>新聞</option>
	<option value="好康" <c:if test="${news.newsCategory == '好康'}">selected</c:if>>好康</option>
	<option value="其他" <c:if test="${news.newsCategory == '其他'}">selected</c:if>>其他</option>
	</select><p>
	消息標題:&nbsp;<input type="text" name="newsName" value="${news.newsName}"><p>
	圖片:&nbsp;<input type="file" name="cover" size="20"><br>
	長700x高不限，檔案格式:JPG、JPEG。<br>
	<img alt="" src=${pageContext.request.contextPath}/${news.picture} width="200"　height="100" ><p>
	消息來源:&nbsp;<input type="text" name="newsSouce" value="${news.newsSouce}"><p>
	上架日期:&nbsp;<input type="text" name="date" value="${news.onDate}" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('form.date');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
	建立日期:&nbsp;<input type="text" name="createDate" value="${news.createDate}" readonly><p>
	內文:&nbsp;<textarea rows="6" cols="40" name="content">${news.content}</textarea><p>
	<input type="button" value="儲存" onclick="saveEditedNews()">
</form>
</body>
<script type="text/javascript">
function saveEditedNews(){
	document.form.action="${pageContext.request.contextPath}/saveEditedNews.do";
	document.form.submit();	
}
</script>
</html>