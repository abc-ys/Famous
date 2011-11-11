<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
贈送點數記錄
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" valign="top">目前可用GSiBonus:&nbsp200</td><td valign="top" Width="200">待生效GSiBonus:&nbsp50</td>
</table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單號碼</font></td>
	<td valign="top" Width="100"><font size="2">來源</font></td>
	<td valign="top" Width="100"><font size="2">待生效</font></td>
	<td valign="top" Width="100"><font size="2">已生效</font></td>
	<td valign="top" Width="100"><font size="2">失效</font></td>
	<td valign="top" Width="100"><font size="2">生效日期</font></td>
	<td valign="top" Width="100"><font size="2">到期日期</font></td></tr>
<tr><td Width="100" Height="30" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/queryPayDetailRecord.do">${orderDetail.order.orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">test</font></td>
	<td valign="top" Width="100"><font size="2">${waitOnBonus}</font></td>
	<td valign="top" Width="100"><font size="2">${OnBonus}</font></td>
	<td valign="top" Width="100"><font size="2">${offBonus}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.gsiBonus.onDate}</font></td>
	<td valign="top" Width="140"><font size="2">${orderDetail.gsiBonus.offDate}</font></td></tr>

</table>
</form>
</body>
</html>