<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h4>變更密碼</h4>
<form name="form">
	<input type="hidden" name="userId" value=${member.id}>
 	<input type="hidden" name="oldPassword" value=${member.password}>
	目前密碼: <input type="password" name="password"><br><br>
	新密碼: <input type="password" name="newPassword"><br><br>
	長度最少6個字元。<br><br>
	確認密碼: <input type="password" name="confirmPassword"><br><br>
	<input type="button"  value="確定修改" onclick="modify('${member.password}')">
	<input type="button"  value="不想改了" onclick="window.close()">
</form>	
</body>
<script type="text/javascript">
function modify(oldPassword){
	var input = document.form.password.value;
	var newPassword = document.form.newPassword.value;
	var confirm =  document.form.confirmPassword.value;
	if(input != oldPassword){
		alert("目前密碼輸入錯誤，請重新輸入");		
	}else if(newPassword != confirm){
		alert("新密碼與確認密碼不符，請重新輸入");		
	}else{
		document.form.action="${pageContext.request.contextPath}/savePassword.do";
		document.form.submit();
	}
}
</script>
</html>