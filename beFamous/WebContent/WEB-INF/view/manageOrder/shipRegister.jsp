<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" id='fm' method="post">
<input type="hidden" name="orderId" value="${orderId}"/>
<input type="hidden" name="adminId" value="${adminId}"/>
<br>
出貨登錄
<p>
<center>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="100" valign="top">訂單編號</td>
	<td valign="top" Width="100">${orderId}</td></tr>
	<td valign="top" Width="150">商品出貨日期</td>
	<td valign="top" Width="150"><input name="MOPEND" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td></tr>
	<td valign="top" Width="100">宅配單號</td>
	<td valign="top" Width="100"><input type="text" name="shipNo" size="15"></td></tr>
	<td valign="top" Width="100">發票號碼</td>
	<td valign="top" Width="100"><input type="text" name="billNo" size="15"></td></tr>
	<td valign="top" Width="100">配送貨運</td>
	<td valign="top" Width="100">${shipName}</td></tr>
</table>
<p>
<input type="submit" value="儲存" onclick="saveData()"/></center>
</form>
</body>
<script>
function saveData(){
     document.fm.action="${pageContext.request.contextPath}/updateShipRegister.do";
     document.fm.submit();
}
</script>
</html>