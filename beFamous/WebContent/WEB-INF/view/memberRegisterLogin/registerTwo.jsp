<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" align="center">
	<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/001.jpg width="100" height="35"></td>
	<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/002.jpg width="100" height="35"></td>
	<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/003.jpg width="100" height="35"></td>
</table>

<h3>上傳圖片</h3>
<form name="upload" enctype="multipart/form-data" method="post">
 <input type="hidden" name="memberId" value="${memberId}"><br><br>
 <input type="hidden" name="picture2" value="image/memberPicture/111111.jpg">
上傳一張代表您的圖片作為在GSibefamous的顯示圖片。
<p><input type="file" name="file" size="20" /> 
<p> <input type="button" value="上傳" onclick="add(${memberId})"> 
<input type="button" value="稍後上傳" onclick="add(${memberId})"> 
</form>
</body>
<script>
function add(memberId){
	document.upload.action="${pageContext.request.contextPath}/saveRegisterTwo.do?memberId="+memberId;
	document.upload.submit();
}
</script>
</html>