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
GSiMoney收支表
<p>
<font size="2">&nbsp查詢期間:</font>&nbsp
<select name="year"> 
<option value="0"></option> 
<option value="99">99</option> 
<option value="100">100</option> 
<option value="101">101</option> 
<option value="102">102</option> 
</select>&nbsp年&nbsp
<select name="month"> 
<option value="0"></option> 
<option value="1">1</option> 
<option value="2">2</option> 
<option value="3">3</option> 
<option value="4">4</option> 
</select>&nbsp月&nbsp&nbsp
<input type="submit" value="查詢" onclick="query()"/>
<p>
GSiMoney結餘: ${orderDetail2.gsiMoney.balance}元<br>
20111231止您可兌換的GSiMoney金額: 200元
<input type="submit" value="申請兌換" onclick="apply()"/>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單號碼</font></td>
	<td valign="top" Width="100"><font size="2">交易日期</font></td>
	<td valign="top" Width="100"><font size="2">收入</font></td>
	<td valign="top" Width="100"><font size="2">支出</font></td>
	<td valign="top" Width="100"><font size="2">結餘</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
<tr><td Width="100" Height="30" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/payDetailRecord.do">${orderDetail.order.orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.order.purchaseDate}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.gsiMoney.income}</font></td>
	<td valign="top" Width="100"><font size="2">0</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.gsiMoney.balance}</font></td>
	<td valign="top" Width="140"><font size="2">${orderDetail.gsiMoney.memo}</font></td></tr>
<tr><td Width="100" Height="30" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/payDetailRecord.do">${orderDetail2.order.orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.order.purchaseDate}</font></td>
	<td valign="top" Width="100"><font size="2">0</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.gsiMoney.exchange}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.gsiMoney.balance}</font></td>
	<td valign="top" Width="140"><font size="2">${orderDetail2.gsiMoney.memo}</font></td></tr>

</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/incomeOutgo.do";
     document.fm.submit();
}
function apply(){
    document.fm.action="${pageContext.request.contextPath}/applyExchange.do";
    document.fm.submit();
}
</script>
</html>