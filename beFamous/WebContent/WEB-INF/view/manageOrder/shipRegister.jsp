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
<br>
出貨登錄
<p>
<center>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單編號</font></td>
	<td valign="top" Width="100"><font size="2">${orderRid}</font></td></tr>
	<td valign="top" Width="100"><font size="2">商品出貨日期</font></td>
	<td valign="top" Width="100"><input name="MOPEND" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td></tr>
	<td valign="top" Width="100"><font size="2">宅配單號</font></td>
	<td valign="top" Width="100"><font size="2"><input type="text" name="shipNo" size="15"></font></td></tr>
	<td valign="top" Width="100"><font size="2">發票號碼</font></td>
	<td valign="top" Width="100"><font size="2"><input type="text" name="billNo" size="15""></font></td></tr>
	<td valign="top" Width="100"><font size="2">配送貨運</font></td>
	<td valign="top" Width="100"><font size="2">${shipName}</font></td></tr>
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