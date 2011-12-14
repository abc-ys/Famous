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
<INPUT type=hidden name=pay value="${pay}">
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step001.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step002-b.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step003-b.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
<font size="2">請確認您所要購買的品項</font>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top">
<font size="2">產品名稱</font></td>
<td valign="top" Width="100"><font size="2">價格</font></td>
<td valign="top" Width="100"><font size="2">數量</font></td>
<td valign="top" Width="100"><font size="2">贈送GSiBonus</font></td></tr>
<tr>
<td Width="140" Height="50" valign="top">
<font size="2">${SDCard.name}點</font>
</td>
<td valign="top" Width="100">
<font size="2">${sdPrice}元 + ${sdBonus}點</font>
</td>
<td valign="top" Width="100">
<font size="2">${amount}</font>
</td>
<td valign="top" Width="100">
<font size="2">${SDCard.reward}</font>
</td>
</tr>
<%--  <tr><td Width="140" Height="50" valign="top"><font size="2">${sdName}</font></td><td valign="top" Width="100"><font size="2">${price}</font></td><td valign="top" Width="100"><font size="2">${discountBonus}</font></td></tr> --%>
<tr><td colspan="4" align="right" valign="top" Height="35"><font size="2">處理費/郵資  NT$ ${shipPrice}元</font></td></tr>
<tr><td colspan="4" align="right" valign="top" Height="35"><font size="2">本筆訂單需付款金額  NT$ ${totalPrice}元 + ${totalBonus}點</font></td></tr>
</table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>付款方式與寄送資訊</b></font></font></td></table>
<p>
<table border="0" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="70" Height="35" valign="top"><font size="2">付款方式:</font></td><td valign="top" colspan="3"><font size="2">&nbsp;${pay}</font></td></tr>
<tr><td Width="70" Height="35" valign="top"><font size="2">收件者:</font></td><td valign="top" colspan="3"><font size="2">&nbsp;${memberUser.receiver}</font></td></tr>
<tr><td Width="70" Height="35" valign="top"><font size="2">email:</font></td><td valign="top" colspan="3"><font size="2">&nbsp;${memberUser.email}</font></td></tr>
<tr><td Width="70" Height="35" valign="top"><font size="2">連絡電話:</font></td><td valign="top" colspan="3"><font size="2">&nbsp;${memberUser.tel}</font></td></tr>
<tr><td Width="70" Height="35" valign="top"><font size="2">送貨地址:</font></td><td valign="top" colspan="3"><font size="2">&nbsp;${memberUser.city} ${number}&nbsp;${region}&nbsp;${memberUser.address}</font></td></tr>
<tr><td Width="70" Height="35" valign="top" rowspan="3"><font size="2">發票資訊:</font></td>
<td valign="top"><font size="2">&nbsp;${billData}</font></td></tr>

</table>
<input type="hidden" name="receiver" value="${memberUser.receiver}">
<input type="hidden" name="receiverEmail" value="${memberUser.email}">
<input type="hidden" name="receiverTel" value="${memberUser.tel}">
<input type="hidden" name="shipAddress" value="${memberUser.address}">
<input type="hidden" name="productType" value="sdCard">
<input type="hidden" name="amount" value="${amount}">
<br>
<p>
<center><input type="submit" value="確認無誤結帳" onclick="add()"/></center>
</form>
</body>
<script>
function add(){
	var page;
	if(document.fm.pay.value=='VISA'){
		page="${pageContext.request.contextPath}/cardPay.do";
	}else if(document.fm.pay.value=='ATM'){
		page="${pageContext.request.contextPath}/aATM.do";
	}
	//alert(document.fm.pay.value);
	document.fm.action=page;
	//document.fm.action="prepayFinal.do";
    document.fm.submit();
}
</script>
</html>