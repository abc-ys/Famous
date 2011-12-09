<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<br>
<h4>現金消費記錄</h4>
<p>
<form name="fm" method="post">
查詢條件:&nbsp;
<input type="radio" name="choice" id="choice" value="1" <c:if test="${choice=='1'}">checked</c:if> /> 未出貨及一個月內訂單
<input type="radio" name="choice" id="choice" value="2" <c:if test="${choice=='2'}">checked</c:if> /> 未出貨訂單
<input type="radio" name="choice" id="choice" value="3" <c:if test="${choice=='3'}">checked</c:if> /> 退換貨訂單
<input type="radio" name="choice" id="choice" value="4" <c:if test="${choice=='4'}">checked</c:if> /> 六個月內訂單<br><br>
訂單編號:&nbsp;<input type="text" name="orderId" id="orderId" value="${orderId}"><br><br>
目前僅....
<input type="hidden" name="finalChoice" value="">
<input type="hidden" name="userId" value="${userId}">
<input type="button" value="查詢" onclick="queryOrder()">
</form>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單號碼</font></td>
	<td valign="top" Width="100"><font size="2">交易日期</font></td>
	<td valign="top" Width="100"><font size="2">付款方式</font></td>
	<td valign="top" Width="100"><font size="2">訂單金額</font></td>
	<td valign="top" Width="100"><font size="2">商品名稱</font></td>
	<td valign="top" Width="100"><font size="2">數量</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td></tr>
<c:forEach var="hm" items="${orders}">
<tr><td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/cashPayDetail.do?orderId=${hm[0].id}">${hm[0].id}</a></font></td>
	<fmt:parseDate var="purchaseDate" value="${hm[0].purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td valign="top" Width="100"><font size="2"><fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' /></font></td>
	<td valign="top" Width="100"><font size="2"><c:if test="${hm[0].payMethod==1}">ATM轉帳</c:if><c:if test="${hm[0].payMethod==2}">信用卡付款</c:if></font></td>
	<c:forEach var="hm2" items="${hm[1]}" varStatus="status">
	<td valign="top" Width="100"><font size="2">${hm2[2].cash}</font></td>
	<td valign="top" Width="100"><font size="2">${hm2[1].name}</font></td>
	<td valign="top" Width="100"><font size="2">${hm2[2].amount}</font></td></c:forEach>
	<td valign="top" Width="100"><font size="2">
		<c:if test="${hm[0].handleStatus == 1}">待處理</c:if>
		<c:if test="${hm[0].handleStatus == 2}">處理中</c:if>
		<c:if test="${hm[0].handleStatus == 3}">調貨中</c:if>
		<c:if test="${hm[0].handleStatus == 4}">已出貨</c:if>
		<c:if test="${hm[0].handleStatus == 5}">已取消</c:if>
		<c:if test="${hm[0].handleStatus == 6}">退貨已退款</c:if>
		<c:if test="${hm[0].handleStatus == 11}">已付款待處理</c:if>
		<c:if test="${hm[0].handleStatus == 12}">已送達</c:if>
	</font></td></tr>
</c:forEach>
</table>
</body>
<script type="text/javascript">
function queryOrder(){
	document.fm.action="${pageContext.request.contextPath}/cashPays.do";
  	document.fm.submit();
}
</script>
</html>