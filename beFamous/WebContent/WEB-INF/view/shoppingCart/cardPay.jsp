<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<INPUT type=hidden name=discountBonus value="${discountBonus}">
<INPUT type=hidden name="amount" value="${amount}">

<p>
<table border="3" BorderColor="white" align="center">
<td Width="100" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/card001.jpg width="100" height="35"></td>
<td Width="100" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/card002.jpg width="100" height="35"></td>
<td Width="100" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/card003.jpg width="100" height="35"></td>
<td Width="100" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/card004.jpg width="100" height="35"></td>
</table>
<center><font size="2">請輸入您的信用卡資料(Please enter your Credit Card Information)</font></center>
<center><table border="3" BorderColor="white">
<tr><td Width="150" align="right"><font size="2">數量(Merchant)</font></td><td align="left" Width="200" colspan="3"><font size="2">${amount}</font></td></tr>
<tr><td Width="150" align="right"><font size="2">特約商店(Merchant)</font></td><td align="left" Width="200" colspan="3"><font size="2">全球商業網股份有限公司</font></td></tr>
<tr><td Width="150" align="right"><font size="2">訂單編號(Order Number)</font></td><td align="left" Width="200" colspan="3"><font size="2">20110905004584</font></td></tr>
<tr><td Width="150" align="right"><font size="2">交易日期(Trans. Date)</font></td><td align="left" Width="200" colspan="3"><font size="2">20110905</font></td></tr>
<tr><td Width="150" align="right"><font size="2">交易金額(Trans. Amount)</font></td><td align="left" Width="200" colspan="3"><font size="2">新台幣(NT$)394元</font></td></tr>
<tr><td Width="150" align="right"><font size="2">信用卡卡號(Card Number)*</font></td><td align="left" Width="200" colspan="3"><input type="text" name="msg" size="15"></td></tr>
<tr><td Width="150" align="right"><font size="2">有效期限(Expiration Date)*</font></td>


<td align="left" Width="200" colspan="3">
<select name="city"> 
<option value="0"></option> 
<option value="1">01</option> 
<option value="2">02</option> 
<option value="3">03</option> 
<option value="4">04</option> 
<option value="1">05</option> 
<option value="2">06</option> 
<option value="3">07</option> 
<option value="4">08</option> 
<option value="1">09</option> 
<option value="2">10</option> 
<option value="3">11</option> 
<option value="4">12</option> 
</select>&nbsp<font size="2">月(Month)</font>&nbsp
<select name="region"> 
<option value="0"></option> 
<option value="1">10</option> 
<option value="2">11</option> 
<option value="3">12</option> 
<option value="4">13</option> 
</select>&nbsp<font size="2">年(Year)</font></td></tr>
<tr><td Width="200" align="right"><font size="2">卡片背面後三碼(AE卡不需輸入)
<br>The last 3-digit number on or next to<br>the signature panel (No need for AE Card)</font></td>
<td align="left" valign="top"><input type="text" name="msg" size="5"></td>
<td align="left" Width="80"><img alt="" src=images/card005.jpg width="100" height="35"></td></tr>
</table></center>

<center><input type="button" value="確認付款(Confirm)" onclick="add('${productType}')"><input type="reset" value="清除(Clear)"></center>
</form>
</body>
<script>
function add(productType){
	var page;
	if(productType=='sdCard'){
		page="${pageContext.request.contextPath}/sdCardFinal.do";
	}else{
		page="${pageContext.request.contextPath}/prepayFinal.do";
	}
    document.fm.action=page;
    document.fm.submit();
}
</script>
</html>