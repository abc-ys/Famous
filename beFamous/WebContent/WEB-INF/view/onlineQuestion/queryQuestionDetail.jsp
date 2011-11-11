<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<br>
<h4>問題內容描述</h4>
<p>
<form name="fm" method="post">
	問題內容:&nbsp;${questionDetail.questionContent}<br>
	<hr>
	<p>
	回覆時間:&nbsp;${questionDetail.answerDate}<br>
	回覆者:&nbsp;${questionDetail.answerPerson}<br>
	回覆內容:&nbsp;${questionDetail.answerContent}<br>
	<hr>
	<p>
	備註時間:&nbsp;${questionDetail.noteDate}<br>
	備註者:&nbsp;${questionDetail.notePerson}<br>
	備註內容:&nbsp;${questionDetail.noteContent}<br>
	<p>
	<input type="button" value="回覆" onclick="addAnswer('${admin}','${questionDetail.questionId}')">
	<input type="button" value="備註"  onclick="addNote('${admin}','${questionDetail.questionId}')">
</form>

</body>
<script type="text/javascript">

function addAnswer(admin, qId){	
	window.open("${pageContext.request.contextPath}/addAnswer.do?adminId="+admin+"&qId="+qId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function addNote(admin, qId){  
	window.open("${pageContext.request.contextPath}/addNote.do?adminId="+admin+"&qId="+qId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
</script>
</html>