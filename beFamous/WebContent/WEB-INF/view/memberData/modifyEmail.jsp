<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>變更email</title>
</head>
<body>
<h4>變更email</h4>
<form name="form">
 <input type="hidden" name="userId" value=${member.id}>
 <input type="hidden" name="oldPassword" value=${member.password}>
 <br><br>
	新email: <input type="text" name="newEmail"><br><br>
	變更email必須重新認證!<br><br>
	密碼: <input type="password" name="password"><br><br>
	填寫你目前的密碼<br><br>
	<input type="button" value="確定修改" onclick="modify('${member.password}')">
	<input type="button" value="不想改了" onclick="window.close()">
</form>	
</body>
<script type="text/javascript">
function modify(oldPassword){
	var input = document.form.password.value;
	if(input != oldPassword){
		alert("密碼輸入錯誤，請重新輸入");		
	}else{
		document.form.action="${pageContext.request.contextPath}/saveEmail.do";
		document.form.submit();
	}
}
</script>
</html>