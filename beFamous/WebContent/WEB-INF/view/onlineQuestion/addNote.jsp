<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="form" method="post">
	回覆者:&nbsp;${admin.adminName}<br><p>
	日期:&nbsp;<fmt:formatDate value="<%=new java.util.Date() %>" pattern ="yyyy-MM-dd"/><br><p>
	備註:&nbsp;
	<textarea rows="6" cols="40" name="note" id="note"></textarea><br><p>
	<input type="button" value="送出" onclick="saveNote()">
	<input type="button" value="取消" onclick="window.close()">
	<input type="hidden" name="adminId" id="adminId" value="${adminId}">
	<input type="hidden" name="adminName" id="adminName" value="${admin.adminName}">	
	<input type="hidden" name="qId" id="qId" value="${qId}">
</form>
</body>
<script type="text/javascript">
function saveNote(){
	document.form.action="${pageContext.request.contextPath}/saveNote.do";
	document.form.submit();
}
</script>
</html>