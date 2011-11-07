<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h4>兌換記錄</h4>
<p>
<form name="form" method="post">
查詢歷史資料:
<select name="year" id="year">
<option value="2011" selected="selected">2011</option>
<option value="2010">2010</option>
</select>年
<select name="month" id="month">
<option value="1" selected="selected">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select>月
<input type="button" value="查詢" onclick="queryExchange()">
<input type="hidden" name="year"><br><br>
<input type="hidden" name="month"><br><br>

</form>
<p>

<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<tr><td Width="100" Height="35" valign="top"><font size="2">申請日</font></td>
	<td valign="top" Width="140"><font size="2">兌換區間</font></td>
	<td valign="top" Width="100"><font size="2">兌換金額</font></td>
	<td valign="top" Width="100"><font size="2">稅額</font></td>
	<td valign="top" Width="100"><font size="2">實際給付</font></td>
	<td valign="top" Width="100"><font size="2">給付日期</font></td>
	<td valign="top" Width="100"><font size="2">餘額</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
	<c:forEach var="hm" items="${exchangeMoneys}">
		<tr><td valign="top" Width="100"><font size="2">${hm.exchangeDate}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.exchangeStartDate}</font>-<font size="2">${hm.exchangeEndDate}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.exchange}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.exchangeTax}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.paid}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.paidDate}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.balance}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm.memo}</font></td></tr>
	</c:forEach> 
</table>
</body>
<script type="text/javascript">

function queryExchange(){
	var year =$('#year :selected').val();
	var month =$('#month :selected').val();
	document.form.year.value = year;
	document.form.month.value = month;
	document.form.action="${pageContext.request.contextPath}/exchangeRecord.do";
	document.form.submit();  
}
</script>
</html>