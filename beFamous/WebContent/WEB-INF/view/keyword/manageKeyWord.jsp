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
<br>
排除字詞組設定
<p>
<font size="2">&nbsp新增字詞組:</font>&nbsp
<input type="text" name="keyWordName" size="15">
<input type="button" value="新增" onclick="add('${adminID}')"/>
<p>
查詢結果<br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="50" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="250"><font size="2">字詞組</font></td>
	<td valign="top" Width="50"><font size="2">刪除</font></td></tr>
	
	<c:forEach var="kw" items="${keyword}" varStatus="status">
<tr><td Height="30" valign="top"><font size="2">${status.index+1}</font></td>
	<td valign="top"><font size="2">${kw.name}</font></td>
	<td valign="top"><font size="2"><a href="javascript:void(0)" onclick="deleteWord('${kw.id}','${adminID}')">刪除</a></font></td></tr>
	</c:forEach>
</table>
</form>
</body>
<script>
function add(adminID){
     document.fm.action="${pageContext.request.contextPath}/addKeyWord.do?adminID="+adminID;
     document.fm.submit();
}
function deleteWord(ID,adminID){
	document.fm.action="${pageContext.request.contextPath}/deleteKeyWord.do?ID="+ID+"&adminID="+adminID;
    document.fm.submit();
}
</script>
</html>