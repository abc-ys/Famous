<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<p>
<font size="2">&nbsp我要檢舉:</font>&nbsp
<select name="offenseType"> 
<option value=""></option>
<c:forEach var="hm" items="${offense}">
<option value="${hm.offenseType.offenseTypeName}">${hm.offenseType.offenseTypeName}</option> 
</c:forEach>
</select>
<br>
<textarea cols=60 rows=6 name=content></textarea>
<br>
<input type="submit" value="確定" onclick="add()"/><input type="submit" value="取消" onclick="cancelData()"/>
<p>
</form>
</body>
<script>
function add(){
	alert($("select[name='offenseType']").val());
	if($("select[name='offenseType']").val()==''){
		alert('還未選檢舉類型');
		return;
	}
	
	if(document.fm.content.value==''){
		alert("未填檢舉內容");
		return;
	}
	
	
     document.fm.action="${pageContext.request.contextPath}/addOffense.do";
     document.fm.submit();
}
function cancelData(){
    document.fm.action="${pageContext.request.contextPath}/cancleOffense.do";
    document.fm.submit();
}
</script>
</html>