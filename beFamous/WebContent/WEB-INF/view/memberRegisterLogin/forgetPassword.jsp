<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<h3>沒關係，再給你一組新的密碼</h3>
<p>
	email:&nbsp;<input type="text" name="email" id="email"><br><br>
	<center><input type="button" value="快送給我吧!" onclick="add()"/></center>
</form>
</body>
<script>
function add(){	
	alert("成功送出新密碼");   
}
</script>
</html>