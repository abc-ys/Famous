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
<font size="2">&nbsp;我要檢舉:</font><p>
	<select name="offenseTypeId">
		<c:forEach var="hm" items="${offense[0]}">
			<option value="${hm.id}">${hm.offenseTypeName}</option> 
		</c:forEach>
	</select>
	<p>
	<textarea cols=60 rows=6 name=reason></textarea>
	<input type="text" name="userId" id="userId" value=1>
	<input type="text" name="productionCategoryId" id="productionCategoryId" value=4>
	<br>
	<input type="submit" value="確定" onclick="save()"><input type="submit" value="取消" onclick="window.close()">
	<p>
</form>
</body>
<script type="text/javascript">
function save(){
	
	if($("select[name='offenseType']").val()==''){
		alert('還未選檢舉類型');
		return;
	}
	
	if(document.fm.reason.value==''){
		alert("未填檢舉內容");
		return;
	}
     document.fm.action="${pageContext.request.contextPath}/addOffense.do";
     document.fm.submit();
}
</script>
</html>