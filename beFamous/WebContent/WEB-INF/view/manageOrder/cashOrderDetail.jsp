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
<form name="fm" id='fm' method="post">
<br>
現金訂單明細
<p>
<input type="hidden" name="shipName" value="${order.shipName}"/>
<input type="hidden" name="orderRid" value="${order.orderRid}"/>
基本資料/付款方式&nbsp&nbsp&nbsp&nbsp訂單編號:&nbsp${order.orderRid}&nbsp&nbsp交易日期:&nbsp${order.purchaseDate}<br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
    <td Width="140" Height="35" valign="top"><font size="2">訂購者會員帳號</font></td>
	<td valign="top" Width="100"><font size="2">收件者</font></td>
	<td valign="top" Width="100"><font size="2">聯絡電話</font></td>
	<td valign="top" Width="200"><font size="2">付款方式</font></td><tr>
	
	<td Height="35"><font size="2"><a href="">${order.member.email}</a></font></td>
	<td><font size="2">${order.receiver}</font></td>
	<td><font size="2">${order.receiverTel}</font></td>	
	<td><font size="2">${order.payMethod}</font></td><tr>
	
	<tr><td Width="140" Height="35" valign="top"><font size="2">發票種類</font></td>
	<td valign="top" Width="100"><font size="2">公司統編</font></td>
	<td valign="top" Width="100"><font size="2">配送時間</font></td>
	<td valign="top" Width="200"><font size="2">寄送地址</font></td></tr>
	
    <td Height="35"><font size="2">${order.billType}</font></td>
	<td><font size="2">${order.threeBillNo}</font></td>
	<td><font size="2">${order.shipTime}</font></td>	
	<td><font size="2">${order.shipCity}${order.shipRegionNo}${order.shipRegion}${order.shipAddress}</font></td><tr>
</table>
<p>
消費明細&nbsp&nbsp<input type="submit" value="出貨登錄" onclick="shipUpdate()"/><br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="130"><font size="2">商品名稱</font></td>
	<td valign="top" Width="130"><font size="2">商品價格</font></td>
	<td valign="top" Width="130"><font size="2">訂單價格</font></td>
	<td valign="top" Width="100"><font size="2">贈送Bonus</font></td>
	<td valign="top" Width="100"><font size="2">付款狀態</font></td>
	<td valign="top" Width="100"><font size="2">商品處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">發票</font></td>
	<td valign="top" Width="100"><font size="2">Bonus處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">申請取消/退貨</font></td>
	<td valign="top" Width="100"><font size="2">退貨時間</font></td></tr>
	
    <td Width="140" Height="35" valign="top"><font size="2">1</font></td>
	<c:forEach var="hm2" items="${order.orderDetail}">
	<td valign="top" Width="100"><font size="2">${hm2.productionCategory.sdCard.name}</font></td>
	<td><font size="2">${hm2.productionCategory.sdCard.sdCardPrice.discountPrice}元+&nbsp${hm2.productionCategory.sdCard.sdCardPrice.discountBonus}點</font></td>
	<td><font size="2">${hm2.productionCategory.sdCard.sdCardPrice.discountPrice}元+&nbsp${hm2.productionCategory.sdCard.sdCardPrice.discountBonus}點</font></td>
	</c:forEach>
	<td><font size="2">${giftPrice}</font></td>
	<td><font size="2">
	<select name="payStatus"> 
	<option value="處理中">處理中</option> 
	<option value="已寄送">已寄送</option>
	</select>
	</font></td>
	<td><font size="2">
	<select name="handleStatus"> 
	<option value="尚未付款">尚未付款</option> 
	<option value="已付款">已付款</option> 
	<option value="扣款失敗">扣款失敗</option> 
	</select>
	</font></td>
	<td><font size="2">
	<select name="billStatus"> 
	<option value="未寄送">未寄送</option>
	<option value="已寄送">已寄送</option>  
	<option value="已作廢">已作廢</option> 
	</select>
	</font></td>
	<td><font size="2">${gsiBonus.onDate}&nbsp待生效</font></td>
	<td><font size="2">${order.applyReturnGoodDate}</font></td>
	<td><font size="2">${order.actualReturnGoodDate}</font></td><tr>
	<td Width="140" Height="35" align="center" colspan="11">
	<c:forEach var="hm3" items="${order.orderDetail}">
	<font size="2">總金額: NT: &nbsp${hm3.productionCategory.sdCard.sdCardPrice.discountPrice}&nbsp+&nbsp${hm3.productionCategory.sdCard.sdCardPrice.discountBonus}點+運費(50)元=&nbsp${tPrice}元+&nbsp${hm3.productionCategory.sdCard.sdCardPrice.discountBonus}點<br>贈送GSiBonus:&nbsp ${giftPrice}點</font></td>
</c:forEach>
</table>
<p>
出貨明細<br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">商品出貨日</font></td>
	<td valign="top" Width="100"><font size="2">發票寄送日</font></td>
	<td valign="top" Width="100"><font size="2">宅配單號</font></td>
	<td valign="top" Width="100"><font size="2">配送貨運</font></td></tr>
	
	<td Height="35"><font size="2">${order.shipDate}</font></td>
	<td><font size="2">${order.billShipDate}</font></td>
	<td><font size="2">${order.shipNo}</font></td>	
	<td><font size="2">${order.shipName}</font></td><tr>
</table>
<p>
訂單備註1&nbsp&nbsp備註訂購者可見<br>
<textarea cols="60" rows="6" name="memo1"></textarea> 
<p>
訂單備註2&nbsp&nbsp備註只有管理者可見<br>
<textarea cols="60" rows="6" name="memo2"></textarea> 

<center><input type="submit" value="儲存" onclick="saveData()"/></center>

</form>
</body>
<script>
function shipUpdate(){
     document.fm.action="${pageContext.request.contextPath}/shipRegister.do";
     document.fm.submit();
}
function saveData(){
    document.fm.action="${pageContext.request.contextPath}/cashOrderDetail.do";
    document.fm.submit();
}
</script>
</html>