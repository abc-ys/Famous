<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step001-b.jpg width="100" height="35"></td>
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step002-b.jpg width="100" height="35"></td>
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step003.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
<form name="fm" method="post">
	<input type="hidden" name="amount" >
	<input type="hidden" name="price" >
	<input type="hidden" name="userId" value="1">
</form>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>請選擇您要購買的品項</b></font></font></td></table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<td Width="200" Height="30" border="0"><font size="2">商品名稱<br></font></td>
	<td Width="200" Height="30" border="0"><font size="2">商品總價<br></font></td>
	<td Width="200" Height="30" border="0"><font size="2">GSiBonus折抵價<br></font></td>
<tr>
<c:forEach var="hm" items="${sdCard}">
	<td><font size="2.5"><pre>${hm.name}</pre></font></td>
	<td><font size="2"><INPUT type="radio" name="price" id="price" value="${hm.pid}_1" onclick="document.fm.price.value=this.value;">NT$ ${hm.sdCardPrice.price}</font></td>
	<td><font size="2"><INPUT type="radio" name="price" id="price" value="${hm.pid}_2"  onclick="document.fm.price.value=this.value;">NT$ ${hm.sdCardPrice.discountPrice}+${hm.sdCardPrice.discountBonus}點</font></td>
	<tr>
</c:forEach>
</table><p>
	數量:&nbsp; <input type="text" name="amount" size="5" onchange="document.fm.amount.value=this.value;">

<p>
<b>購買GSiSD卡我們送您GSiBonus，可用來折抵日後的消費!</b>
<center><input type="button" name="111" value="購買" onclick="javaScript:addShoppingCart()"/></center>
</body>
<script>
function addShoppingCart(){
	 document.fm.action="${pageContext.request.contextPath}/sdCardTwo.do";
	 document.fm.submit();
}
</script>
</html>