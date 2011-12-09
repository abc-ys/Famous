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
<form name="fm" id='fm' method="post">
<br>
現金訂單明細
<p>
<input type="hidden" name="shipName" value="${order.shipName}"/>
<input type="hidden" name="orderId" value="${order.id}"/>
<fmt:parseDate var="purchaseDate" value="${order.purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
基本資料/付款方式&nbsp;&nbsp;&nbsp;&nbsp;訂單編號:&nbsp;${order.id}&nbsp;&nbsp;交易日期:&nbsp;<fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' /><br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
    <td Width="140" Height="35" valign="top"><font size="2">訂購者會員帳號</font></td>
	<td valign="top" Width="100"><font size="2">收件者</font></td>
	<td valign="top" Width="100"><font size="2">聯絡電話</font></td>
	<td valign="top" Width="200"><font size="2">付款方式</font></td><tr>
	
	<td Height="35"><font size="2"><a href="javascript:void(0)" onclick="creator('${member.id}','${adminId}')">${member.email}</a></font></td>
	<td><font size="2">${order.receiver}</font></td>
	<td><font size="2">${order.receiverTel}</font></td>	
	<td><font size="2">
		<c:if test="${order.payMethod == 1}">轉帳付款</c:if>
		<c:if test="${order.payMethod == 2}">信用卡付款</c:if>
	</font></td><tr>
	
	<tr><td Width="140" Height="35" valign="top"><font size="2">發票種類</font></td>
	<td valign="top" Width="100"><font size="2">公司統編</font></td>
	<td valign="top" Width="100"><font size="2">配送時間</font></td>
	<td valign="top" Width="200"><font size="2">寄送地址</font></td></tr>
	
    <td Height="35"><font size="2">
	 	<c:if test="${order.billType == 1}">二聯式電子發票</c:if>
		<c:if test="${order.billType == 2}">二聯式紙本發票</c:if>
		<c:if test="${order.billType == 3}">三聯式發票</c:if>
   </font></td>
	<td><font size="2">${order.threeBillNo}</font></td>
	<td><font size="2">${order.shipTime}</font></td>	
	<td><font size="2">${order.shipCity}${order.shipRegionNo}${order.shipRegion}${order.shipAddress}</font></td><tr>
</table>
<p>
消費明細&nbsp;&nbsp;<input type="submit" value="出貨登錄" onclick="shipUpdate('${order.id}','${order.shipName}')"/><br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="130"><font size="2">商品名稱</font></td>
	<td valign="top" Width="130"><font size="2">商品價格</font></td>
	<td valign="top" Width="130"><font size="2">訂單價格</font></td>
	<td valign="top" Width="50"><font size="2">贈送Bonus</font></td>
	<td valign="top" Width="100"><font size="2">付款狀態</font></td>
	<td valign="top" Width="100"><font size="2">商品處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">發票</font></td>
	<td valign="top" Width="250"><font size="2">Bonus處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">申請取消/退貨</font></td>
	<td valign="top" Width="100"><font size="2">退貨時間</font></td></tr>
	
    <td Width="140" Height="35" valign="top"><font size="2">1</font></td>
	<c:forEach var="hm2" items="${orderDetails}">
		<td valign="top" Width="100"><font size="2">${hm2[1].name}</font></td>
		<td><font size="2">${hm2[1].sdCardPrice.price}元</font></td>
		<td><font size="2">${hm2[2].cash}元</font></td>
		<td><font size="2">${hm2[1].reward}</font></td>
	</c:forEach>

	<td><font size="2">
	<select name="payStatus"> 
		<option value="1" <c:if test="${order.payStatus=='1'}">selected = "true"</c:if> >尚未付款</option> 
		<option value="2" <c:if test="${order.payStatus=='2'}">selected = "true"</c:if> >扣款失敗</option> 
		<option value="3" <c:if test="${order.payStatus=='3'}">selected = "true"</c:if> >已付款</option> 
		<option value="4" <c:if test="${order.payStatus=='4'}">selected = "true"</c:if> >已退款</option> 
	</select>
	</font></td>
	<td><font size="2">
	<select name="handleStatus">
		<option value="1" <c:if test="${order.handleStatus == 1}">selected = "true"</c:if>>待處理(SD)</option> 
		<option value="2" <c:if test="${order.handleStatus == 2}">selected = "true"</c:if>>處理中(SD)</option> 
		<option value="3" <c:if test="${order.handleStatus == 3}">selected = "true"</c:if>>調貨中(SD)</option> 
		<option value="4" <c:if test="${order.handleStatus == 4}">selected = "true"</c:if>>已出貨 (SD)</option> 
		<option value="5" <c:if test="${order.handleStatus == 5}">selected = "true"</c:if>>已取消(SD)</option> 
		<option value="6" <c:if test="${order.handleStatus == 6}">selected = "true"</c:if>>退貨已退款(SD)</option> 
		<option value="7" <c:if test="${order.handleStatus == 7}">selected = "true"</c:if>>已儲值</option> 
		<option value="8" <c:if test="${order.handleStatus == 8}">selected = "true"</c:if>>尚未付款</option> 
		<option value="9" <c:if test="${order.handleStatus == 9}">selected = "true"</c:if>>已付款</option> 
		<option value="10" <c:if test="${order.handleStatus == 10}">selected = "true"</c:if>>扣款失敗</option> 
		<option value="11" <c:if test="${order.handleStatus == 11}">selected = "true"</c:if>>已付款待處理(SD)</option> 
		<option value="12" <c:if test="${order.handleStatus == 12}">selected = "true"</c:if>>已送達(SD)</option> 
	</select>
	</font></td>
	<td><font size="2">
	<select name="billStatus"> 
	<option value="1" <c:if test="${order.billStatus == 1}">selected = "true"</c:if>>未寄送</option>
	<option value="2" <c:if test="${order.billStatus == 2}">selected = "true"</c:if>>已寄送</option>  
	<option value="3" <c:if test="${order.billStatus == 3}">selected = "true"</c:if>>已作廢</option> 
	</select>
	</font></td>
	<td><font size="2">&nbsp;
		<fmt:parseDate var="createDate" value="${order.gsiBonus.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
		<c:if test="${order.gsiBonus.createDate<nowDate}"><fmt:formatDate value='${createDate}' pattern='yyyy-MM-dd' />待生效</c:if>
		<fmt:parseDate var="onDate" value="${order.gsiBonus.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
		<c:if test="${order.gsiBonus.onDate<nowDate}"><fmt:formatDate value='${onDate}' pattern='yyyy-MM-dd' />已生效</c:if>
		<fmt:parseDate var="offDate" value="${order.gsiBonus.offDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
		<c:if test="${order.gsiBonus.offDate<nowDate}"><fmt:formatDate value='${offDate}' pattern='yyyy-MM-dd' />已失效</c:if>
		<fmt:parseDate var="dropDate" value="${order.gsiBonus.dropDate}" type="DATE" pattern="yyyyMMddHHmmss"/>	
		<c:if test="${order.gsiBonus.dropDate<nowDate}"><fmt:formatDate value='${dropDate}' pattern='yyyy-MM-dd' />已取消</c:if>
	</font></td>
	<fmt:parseDate var="applyReturnGoodDate" value="${order.applyReturnGoodDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
	<td><font size="2"><fmt:formatDate value='${applyReturnGoodDate}' pattern='yyyy-MM-dd' /></font></td>
	<fmt:parseDate var="actualReturnGoodDate" value="${order.actualReturnGoodDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
	<td><font size="2"><fmt:formatDate value='${actualReturnGoodDate}' pattern='yyyy-MM-dd' /></font></td><tr>
	<td Width="140" Height="35" align="center" colspan="11">
	<c:forEach var="hm3" items="${orderDetails}">
	<font size="2">總金額: NT: &nbsp;${hm3[2].cash}</font></td>
</c:forEach>
</table>
<p>
出貨明細<br>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">商品出貨日</font></td>
	<td valign="top" Width="100"><font size="2">發票寄送日</font></td>
	<td valign="top" Width="100"><font size="2">宅配單號</font></td>
	<td valign="top" Width="100"><font size="2">配送貨運</font></td></tr>
	
	<fmt:parseDate var="shipDate" value="${order.shipDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
	<td Height="35"><font size="2"><fmt:formatDate value='${shipDate}' pattern='yyyy-MM-dd' /></font></td>
	<fmt:parseDate var="billShipDate" value="${order.billShipDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
	<td><font size="2"><fmt:formatDate value='${billShipDate}' pattern='yyyy-MM-dd' /></font></td>
	<td><font size="2">${order.shipNo}</font></td>	
	<td><font size="2">${order.shipName}</font></td><tr>
</table>
<p>
訂單備註1&nbsp&nbsp備註訂購者可見<br>
<textarea cols="60" rows="6" name="memo1">${order.memo1}</textarea> 
<p>
訂單備註2&nbsp&nbsp備註只有管理者可見<br>
<textarea cols="60" rows="6" name="memo2">${order.memo2}</textarea> 
<input type="hidden" name="adminId" value="${adminId}">
<center><input type="submit" value="儲存" onclick="saveData()"/></center>

</form>
</body>
<script>
function shipUpdate(orderId, shipName){
     document.fm.action="${pageContext.request.contextPath}/shipRegister.do";
     document.fm.submit();
}
function saveData(){
    document.fm.action="${pageContext.request.contextPath}/saveCashOrderDetail.do";
    document.fm.submit();
}
function creator(memberId,adminId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do");
}
</script>
</html>