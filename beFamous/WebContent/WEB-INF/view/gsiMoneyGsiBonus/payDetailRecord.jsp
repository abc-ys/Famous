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
<h4>訂單詳細記錄</h4>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<fmt:parseDate var="purchaseDate" value="${order.purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
<tr><td Width="140" Height="35" align="center" colspan="6"><font size="2">訂單號碼:&nbsp;${order.id}&nbsp;&nbsp;訂購時間:&nbsp;<fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' />&nbsp;&nbsp;</font></td></tr>
<tr><td Width="40" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="200"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="200"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="40"><font size="2">單價</font></td>
	<td valign="top" Width="80"><font size="2">訂單價格</font></td></tr>
<c:forEach var="hm" items="${orderDetails}" varStatus="status">
<tr><td Height="30" valign="top"><font size="2">${status.index+1}</font></td>
	<c:if test="${hm[0]==1}">
	<td valign="top"><font size="2"></font></td>
	<td valign="top"><font size="2">${hm[1].name}</font></td>
	<td valign="top"><font size="2">0</font></td>
	<td valign="top"><font size="2">${hm[2].gsiMoney}</font></td>
	</c:if>
	<c:if test="${hm[0]==2}">
	<td valign="top"><font size="2">${hm[1].name}</font></td>
	<td valign="top"><font size="2">${hm[1].album.name}</font></td>
	<td valign="top"><font size="2">${hm[1].songPrice.price}</font></td>
	<td valign="top"><font size="2">${hm[2].gsiMoney}</font></td>
	</c:if>
</tr>
</c:forEach>
<tr><td Height="35" align="center" colspan="6"><font size="2">商品總金額:GSiMoney&nbsp;${order.gsiMoney.outgo}&nbsp;元</font></td></tr>

</table>
</form>
</body>
</html>