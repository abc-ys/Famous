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
<h4>贈送點數記錄</h4>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<td Width="200" valign="top">目前可用GSiBonus:&nbsp;
		<c:if test="${noRecord == 'T'}">0元</c:if>
		<c:if test="${noRecord == 'F'}">${lastBonus.balance}元</c:if>
	</td>
	<td valign="top" Width="200">待生效GSiBonus:&nbsp;${unBonus}元</td>
</table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單號碼</font></td>
	<td valign="top" Width="100"><font size="2">來源</font></td>
	<td valign="top" Width="100"><font size="2">待生效</font></td>
	<td valign="top" Width="100"><font size="2">已生效</font></td>
	<td valign="top" Width="100"><font size="2">失效</font></td>
	<td valign="top" Width="100"><font size="2">生效日期</font></td>
	<td valign="top" Width="100"><font size="2">到期日期</font></td></tr>
<c:forEach var="hm" items="${orders}">
<tr><td Width="100" Height="30" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/payDetailRecord.do?orderId=${hm.id}">${hm.id}</a></font></td>
	<td valign="top" Width="100"><font size="2"></font></td>
	<fmt:parseDate var="nDate" value="${nowDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td valign="top" Width="100"><font size="2">
		<c:choose>
			<c:when test="${hm.gsiBonus.onDate > nDate}">${hm.gsiBonus.reward}</c:when>
			<c:otherwise>0</c:otherwise>
		</c:choose>
	</font></td>
	<td valign="top" Width="100"><font size="2">
		<c:choose>
			<c:when test="${hm.gsiBonus.onDate < nDate}">${hm.gsiBonus.reward}</c:when>
			<c:otherwise>0</c:otherwise>
		</c:choose>
	</font></td>
	<td valign="top" Width="100"><font size="2">
		<c:choose>
			<c:when test="${hm.gsiBonus.offDate > nDate}">${hm.gsiBonus.reward}</c:when>
			<c:otherwise>0</c:otherwise>
		</c:choose>
	</font></td>
	<fmt:parseDate var="onDate" value="${hm.gsiBonus.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td valign="top" Width="100"><font size="2"><fmt:formatDate value='${onDate}' pattern='yyyy-MM-dd' /></font></td>
	<fmt:parseDate var="offDate" value="${hm.gsiBonus.offDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td valign="top" Width="140"><font size="2"><fmt:formatDate value='${offDate}' pattern='yyyy-MM-dd' /></font></td></tr>
</c:forEach>
</table>
<p>
目前可用GSiBonus:....<br>
待生效GSiBonus:....<br>
<p>
GSiBonus狀態說明:<br>
<p>
來源:...<br>
待生效:...<br>
已生效:...<br>
已失效:...
</form>
</body>
</html>