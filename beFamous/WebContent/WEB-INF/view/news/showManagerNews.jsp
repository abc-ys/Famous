<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
	消息類別:&nbsp;${news.newsCategory}<p>
	消息標題:&nbsp;${news.newsName}<p>
	圖片:&nbsp;<img alt="" src=${pageContext.request.contextPath}/${news.picture} width="200"　height="100" ><p>
	消息來源:&nbsp;${news.newsSouce}<p>
	上架日期:&nbsp;${news.onDate}<p>
	建立日期:&nbsp;${news.createDate}<p>
	內文:<br>${news.content}<p>
</body>
</html>