<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
<h4>詳細現金消費記錄</h4>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<fmt:parseDate var="purchaseDate" value="${order.purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/>

<tr><td Width="140" Height="35" align="center" colspan="9"><font size="2">訂單號碼:&nbsp;${order.id}&nbsp;&nbsp;
	訂購時間:&nbsp;<fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' />&nbsp;&nbsp;
	付款方式:&nbsp;<c:if test="${order.payMethod == 1}">轉帳付款</c:if><c:if test="${order.payMethod == 2}">信用卡付款</c:if></font></td></tr>
	
<tr><td Width="40" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="140"><font size="2">商品名稱</font></td>
	<td valign="top" Width="100"><font size="2">單價</font></td>
	<td valign="top" Width="100"><font size="2">數量</font></td>
	<td valign="top" Width="100"><font size="2">訂單價格</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">發票狀態</font></td>
	<td valign="top" Width="100"><font size="2">取消/退貨</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
<c:forEach var="hm" items="${orderDetails}" varStatus="status">
<tr><td Height="30" valign="top"><font size="2">${status.index+1}</font></td>
	<td valign="top"><font size="2">${hm[1].name}</font></td>
	<td valign="top">
	<c:if test="${hm[0]==3}"><font size="2">${hm[1].sdCardPrice.price}</font></c:if>
	<c:if test="${hm[0]==4}"><font size="2">${hm[1].prePaidPrice.price}</font></c:if>
	</td>
	<td valign="top"><font size="2">${hm[2].amount}</font></td>
	<td valign="top"><font size="2">${hm[2].cash}</font></td>
	<td valign="top"><font size="2">
		<c:if test="${order.handleStatus == 1}">待處理</c:if>
		<c:if test="${order.handleStatus == 2}">處理中</c:if>
		<c:if test="${order.handleStatus == 3}">調貨中</c:if>
		<c:if test="${order.handleStatus == 4}">已出貨</c:if>
		<c:if test="${order.handleStatus == 5}">已取消</c:if>
		<c:if test="${order.handleStatus == 6}">退貨已退款</c:if>
		<c:if test="${order.handleStatus == 11}">已付款待處理</c:if>
		<c:if test="${order.handleStatus == 12}">已送達</c:if>
	</font></td>
	<td valign="top"><font size="2">
		<c:if test="${order.billStatus == 1}">未寄送</c:if>
		<c:if test="${order.billStatus == 2}">已寄送</c:if>
		<c:if test="${order.billStatus == 3}">已作廢</c:if>
	</font></td>
	<td valign="top"><font size="2">
	<c:if test="${hm[0]==3 and order.handleStatus ==2 or order.handleStatus ==3 or order.handleStatus ==8 or order.handleStatus ==11 }"><a href="javascript:void(0)" onclick="cancel('${order.id}')">取消</a><br></c:if>
	<c:if test="${hm[0]==3 and back=='y' }"><a href="javascript:void(0)" onclick="returnGoods('${order.id}','${hm[1].name}','${hm[2].amount}')">退貨</a></c:if></font></td>
	<td valign="top"><font size="2">${order.memo1}</font></td>
</tr>
<tr>
<td Width="140" Height="35" align="center" colspan="9"><font size="2">商品總金額: NT&nbsp; ${hm[2].cash}&nbsp;元</font></td></tr>
</c:forEach>
</table>
</form>
</body>
<script>
function cancel(orderId){
	document.fm.action="${pageContext.request.contextPath}/cancelGoods.do?orderId="+orderId;
	document.fm.submit();
}
function returnGoods(orderNumber,productName,amount){
	//alert("退貨");
	var url = "${pageContext.request.contextPath}/returnGoods.do?orderNumber="+orderNumber+"&productName="+productName+"&amount="+amount;    
	//以下兩行是將中文字做編碼
	url = encodeURI(url); 
	url = encodeURI(url);
	window.open(url,"parent","height=300,width=600,location=no,scrollbars=yes,toolbar=no,directories=no,menubar=no,directories=no");
}
</script>
</html>