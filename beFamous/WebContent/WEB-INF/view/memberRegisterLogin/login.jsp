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
<h3>會員登入</h3>
<p>
	email:&nbsp;<input type="text" name="email" id="email"><br><br>
	密碼:&nbsp;<input type="password" name="password" id="password"><br><br>
	<center><input type="button" value="登入" onclick="login()"/></center>
	<a href="${pageContext.request.contextPath}/forgetPassword.do">忘記密碼</a>
	還不是會員?<a href="${pageContext.request.contextPath}/registerOne.do">我要加入</a>
</form>
</body>
<script>
function login(){	
	alert("成功登入");   
}
</script>
</html>