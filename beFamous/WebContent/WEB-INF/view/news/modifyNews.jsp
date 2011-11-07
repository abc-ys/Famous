<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="form" method="post">
	<h4>修改訊息</h4>
	消息標題:<input type="text" name="newsName" value=${news.newsName}><br><br>
	消息來源:<input type="text" name="newsSouce" value=${news.newsSouce}><br><br>
	建立日期:<input type="text" name="createDate" value=${news.createDate}><br><br>
	內文:<textarea rows="6" cols="40" name="content">${news.content}</textarea><br><br>
	<input type="hidden" name="newsId" value=${news.newsId}>
	<input type="button" value="取消" onclick="cancleNews()">
	<input type="button" value="儲存文章" onclick="updateNews()">
	<input type="button" value="發佈文章" onclick="updatePublishNews()">
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