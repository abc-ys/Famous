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
<form name="form">
	回覆者:&nbsp;${admin.adminName}<br><p>
	日期:&nbsp;<fmt:formatDate value="<%=new java.util.Date() %>" pattern ="yyyy-MM-dd"/><br><p>
	內容:&nbsp;
	<textarea rows="6" cols="40" name="answer" id="answer"></textarea><br><p>
	<input type="button" value="送出" onclick="saveAnswer()">
	<input type="button" value="取消" onclick="window.close()">
	<input type="hidden" name="adminId" id="adminId" value=${admin.adminId}>	
	<input type="hidden" name="qId" id="memberId" value=${qId}>
</form>
</body>
<script type="text/javascript">
function saveAnswer(adminId, memberId){
	document.form.action="${pageContext.request.contextPath}/saveAnswer.do";
	document.form.submit();
}
</script>
</html>