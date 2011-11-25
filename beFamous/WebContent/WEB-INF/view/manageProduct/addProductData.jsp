<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<p class="topic">新增商品資料</p>
<form name="fm" enctype="multipart/form-data" method="post">
<p>上傳excel檔案:&nbsp<input type="file" name="file" size="20" /> 
<p><input type="button" value="確定上傳" onclick="add()"> 
</form>
</body>
<script type="text/javascript">
function add(){
	document.fm.action="${pageContext.request.contextPath}/handleUploadFile.do";
	document.fm.submit();
}
</script>
</html>