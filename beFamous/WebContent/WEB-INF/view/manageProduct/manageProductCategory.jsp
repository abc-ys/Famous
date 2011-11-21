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
商品類別管理
<p>
<font size="2">&nbsp新增商品類別:</font>&nbsp
<input type="text" name="productionClassificationName" size="15">
<input type="submit" value="新增" onclick="add()"/>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="50" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="250"><font size="2">名稱</font></td>
	<td valign="top" Width="70"><font size="2">子目錄</font></td>
	<td valign="top" Width="50"><font size="2">編輯</font></td>
	<td valign="top" Width="50"><font size="2">刪除</font></td></tr>
	
	<c:forEach var="pc" items="${productionClassification}" varStatus="status">
<tr><td Height="30" valign="top"><font size="2">${status.index+1}</font></td>
	<td valign="top"><font size="2">${pc.name}</font></td>
	<td valign="top"><font size="2"><a href="javascript:void(0)">子目錄</a></font></td>
	<td valign="top"><font size="2"><a href="javascript:void(0)" onclick="edit(${pc.id})">編輯</a></font></td>
	<td valign="top"><font size="2"><a href="javascript:void(0)" onclick="deleteCategory(${pc.id})">刪除</a></font></td></tr>
	</c:forEach>
</table>
</form>
</body>
<script>
function add(){
     document.fm.action="${pageContext.request.contextPath}/addProductCategory.do";
     document.fm.submit();
}
function edit(productionClassificationId){
	window.open("${pageContext.request.contextPath}/editProductCategory.do?productionClassificationId="+productionClassificationId,"parent","height=120,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no");
}
function deleteCategory(productionClassificationId){
	document.fm.action="${pageContext.request.contextPath}/deleteProductCategory.do?productionClassificationId="+productionClassificationId;
    document.fm.submit();
}
</script>
</html>