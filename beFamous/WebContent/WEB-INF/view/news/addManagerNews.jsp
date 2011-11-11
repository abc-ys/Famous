<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<form name="form"  enctype="multipart/form-data"  method="post">
	<h4>新增最新消息</h4>
	消息類別:&nbsp;<select name="newsCategory">
	<option value=""></option>
	<option value="表演">表演</option>
	<option value="公告">公告</option>
	<option value="新聞">新聞</option>
	<option value="好康">好康</option>
	<option value="其他">其他</option>
	</select><p>
	消息標題:&nbsp;<input type="text" name="newsName" ><p>
	檔案:&nbsp;<input type="file" name="cover" size="20"><br>
	長700x高不限，檔案格式:JPG、JPEG。<br><br>
	消息來源:&nbsp;<input type="text" name="newsSouce" ><p>
	上架日期:&nbsp;<input type="text" name="date" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('form.date');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
	建立日期:&nbsp;<input type="text" name="createDate" value="${creatorDate}" readonly><p>
	內文:&nbsp;<textarea rows="6" cols="40" name="content"></textarea><p>
	<input type="button" value="儲存" onclick="saveCNews()">
</form>
</body>
<script type="text/javascript">
function saveCNews(){
	document.form.action="${pageContext.request.contextPath}/saveCNews.do";
	document.form.submit();	
}
</script>
</html>