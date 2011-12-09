<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="form" method="post">
	<h4>訊息刊登</h4>
	GSiBefamous提供....<br><br>	
	會員編號:&nbsp;<input type="text" name="email" value="${member[0].email}" disabled="true"><br><br>
	消息標題:&nbsp;<input type="text" name="newsName" ><br><br>
	消息來源:&nbsp;<input type="text" name="newsSouce" ><br><br>
	建立日期:&nbsp;<input type="text" name="createDate" value=<fmt:formatDate value="<%=new java.util.Date() %>" pattern ="yyyy-MM-dd"/> disabled="true"><br><br>
	內文:&nbsp;<textarea rows="6" cols="40" name="content"></textarea><br><br>
	<input type="hidden" name="userId" value="${member[0].id}">
	<input type="button" value="取消" onclick="cancleNews()">
	<input type="button" value="儲存文章" onclick="saveNews()">
	<input type="button" value="發佈文章" onclick="publishNews()">
</form>
</body>
<script type="text/javascript">
function cancleNews(){
	if (confirm("您是否要放棄已填寫的資料?")){
		document.form.action="${pageContext.request.contextPath}/addNews.do";
		document.form.submit();
	}
}
function saveNews(){
	document.form.action="${pageContext.request.contextPath}/saveNews.do";
	document.form.submit();	
}
function publishNews(){
	document.form.action="${pageContext.request.contextPath}/publishNews.do";
	document.form.submit();
}
</script>
</html>