<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<p class="topic">上傳圖片</p>
<form name="upload" enctype="multipart/form-data" method="post">
 <input type="hidden" name="memberId" value=${memberId}><br><br>
上傳一張代表您的圖片作為在GSibefamous的顯示圖片。
<p><input type="file" name="file" size="20" /> 
<p> <input type="submit" value="上傳" onclick="add(${memberId})"> 
<input type="reset" value="稍後上傳" onclick="window.close()"> 
</form>
</body>
<script type="text/javascript">
function add(memberId){
	document.upload.action="${pageContext.request.contextPath}/handleUploadPicture.do?memberId="+memberId;
	document.upload.submit();
}
</script>
</html>