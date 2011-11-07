<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="form" method="post">
	<h4>訊息刊登</h4>
	GSiBefamous提供....<br><br>	
	會員編號:<input type="text" name="memberID" ><br><br>
	消息標題:<input type="text" name="newsName" ><br><br>
	消息來源:<input type="text" name="newsSouce" ><br><br>
	建立日期:<input type="text" name="createDate" ><br><br>
	內文:<textarea rows="6" cols="40" name="content"></textarea><br><br>
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