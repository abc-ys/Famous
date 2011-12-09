<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
GSiMoney訂單明細
<p>
<fmt:parseDate var="purchaseDate" value="${order.purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
訂單編號:&nbsp;${order.id}&nbsp;&nbsp;交易日期:&nbsp;<fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' /><br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="130"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="130"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="130"><font size="2">單價</font></td>
	<td valign="top" Width="100"><font size="2">訂單價格</font></td>
	<td valign="top" Width="100"><font size="2">贈送Bonus</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td></tr>
<c:forEach var="hm" items="${orderDetails}" varStatus="status">
<tr><td Height="30" valign="top"><font size="2">${status.index+1}</font></td>
	<c:if test="${hm[0]==1}">
	<td valign="top"><font size="2"></font></td>
	<td valign="top"><font size="2">${hm[1].name}</font></td>
	<td valign="top"><font size="2">&nbsp;</font></td>
	<td valign="top"><font size="2">${hm[2].gsiMoney}</font></td>
	<td valign="top"><font size="2">&nbsp;</font></td>
	<td valign="top"><font size="2">&nbsp;</font></td>
	</c:if>
	<c:if test="${hm[0]==2}">
	<td valign="top"><font size="2">${hm[1].name}</font></td>
	<td valign="top"><font size="2">${hm[1].album.name}</font></td>
	<td valign="top"><font size="2">${hm[1].songPrice.price}</font></td>
	<td valign="top"><font size="2">${hm[2].gsiMoney}</font></td>
	<td valign="top"><font size="2">${hm[1].songPrice.reward}</font></td>
	<td valign="top"><font size="2">&nbsp;</font></td>
	</c:if>
</tr>
</c:forEach>
<td Width="140" Height="35" align="center" colspan="11">
	<font size="2">總金額: NT:&nbsp;${order.totalMoney}元<c:if test="${order.totalBonus>0}">+${order.totalBonus}點</c:if><br>贈送GSiBonus:&nbsp;${order.gsiBonus.reward}點</font></td>	
</table>
</form>
</body>
</html>