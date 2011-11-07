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
GSiMoney訂單明細
<p>
訂單編號:&nbsp${order.orderRid}&nbsp&nbsp交易日期:&nbsp${order.purchaseDate}<br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="130"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="130"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="130"><font size="2">單價</font></td>
	<td valign="top" Width="100"><font size="2">訂單價格</font></td>
	<td valign="top" Width="100"><font size="2">贈送Bonus</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">Bonus處理狀態</font></td></tr>
	
	<c:forEach var="hm" items="${orders}" varStatus="status">
    <td Height="35"><font size="2">${status.index+1}</font></td>
	
	<td><font size="2">${hm[0].productionCategory.song.name}</font></td>
	<td><font size="2"><a href="javascript:void(0)">${hm[0].productionCategory.song.album.name}</a></font></td>
	<td><font size="2">${hm[0].productionCategory.song.songPrice.price}</font></td>
	<td><font size="2">${hm[0].productionCategory.song.songPrice.price}</font></td>
	
	<td><font size="2">${hm[1]}</font></td>
	<td><font size="2">${hm[2]}</font></td>
	<td><font size="2">${hm[3].onDate}&nbsp待生效</font></td><tr>
	</c:forEach>
	
	<td Width="140" Height="35" align="center" colspan="11">
	<font size="2">總金額: NT: &nbsp ${price} &nbsp+&nbsp0點+運費(50)元=&nbsp${tPrice}元+&nbsp0點<br>贈送GSiBonus:&nbsp ${giftPrice}點</font></td>
	
</table>
</form>
</body>
</html>