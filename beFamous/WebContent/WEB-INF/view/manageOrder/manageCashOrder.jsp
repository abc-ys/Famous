<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" id='fm' method="post">
<br>
現金訂單管理
<p>
訂購者帳號:&nbsp;<input type="text" name="userEmail" value="${userEmail}">&nbsp;<p>
訂單編號:&nbsp;<input type="text" name="orderId" value="${orderId}">&nbsp;<p>
訂購日期區段:&nbsp;
<input name="startDate" type="text" class="fillbox" value="${startDate}">&nbsp;
<A HREF="javascript:show_calendar('fm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
<input name="endDate" type="text" class="fillbox" value="${endDate}">&nbsp;
<A HREF="javascript:show_calendar('fm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
處理狀態:&nbsp;
<select name="handleStatus"> 
	<option value="0"></option> 
	<option value="1" <c:if test="${hStatus=='1'}">selected = "true"</c:if>>待處理</option> 
	<option value="7" <c:if test="${hStatus=='7'}">selected = "true"</c:if>>已儲值</option> 
	<option value="4" <c:if test="${hStatus=='4'}">selected = "true"</c:if>>已出貨</option> 
</select>
<p>
<center><input type="submit" value="查詢" onclick="query()"/>&nbsp;&nbsp;<input type="reset" value="全部清除"/></center>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單編號</font></td>
	<td valign="top" Width="100"><font size="2">訂購者</font></td>
	<td valign="top" Width="100"><font size="2">付款方式</font></td>
	<td valign="top" Width="100"><font size="2">訂購日期</font></td>
	<td valign="top" Width="100"><font size="2">總金額</font></td>
	<td valign="top" Width="100"><font size="2">商品內容</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
<c:forEach var="hm" items="${orders}">	
<tr><td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/cashOrderDetail.do?orderId=${hm[0].id}&adminId=${adminId}">${hm[0].id}</a></font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)"  onclick="identity('123','${hm[1].id}','${hm[1].identityName}')">${hm[1].email}</a></font></td>	
	<td valign="top" Width="100"><font size="2">
		<c:if test="${hm[0].payMethod == 1}">轉帳付款</c:if>
		<c:if test="${hm[0].payMethod == 2}">信用卡付款</c:if>
	</font></td>	
	<fmt:parseDate var="purchaseDate" value="${hm[0].purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
	<td valign="top" Width="100"><font size="2"><fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' /></font></td>	
	<c:forEach var="hm2" items="${hm[2]}">
	<td valign="top" Width="100"><font size="2">${hm2[2].cash}</font></td>
	<td valign="top" Width="100"><font size="2">${hm2[1].name}</font></td>
	</c:forEach> 
	<td valign="top" Width="100"><font size="2">
		<c:if test="${hm[0].handleStatus == 1}">待處理</c:if>
		<c:if test="${hm[0].handleStatus == 2}">處理中</c:if>
		<c:if test="${hm[0].handleStatus == 3}">調貨中</c:if>
		<c:if test="${hm[0].handleStatus == 4}">已出貨</c:if>
		<c:if test="${hm[0].handleStatus == 5}">已取消</c:if>
		<c:if test="${hm[0].handleStatus == 6}">退貨已退款</c:if>
		<c:if test="${hm[0].handleStatus == 11}">已付款待處理</c:if>
		<c:if test="${hm[0].handleStatus == 12}">已送達</c:if>
	</font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].memo2}</font></td>
</tr></c:forEach>
<tr>
</table>
<input type="hidden" name="adminId" value="${adminId}">
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/manageCashOrders.do";
     document.fm.submit();
}
function identity(adminId,memberId,identityName){	
	if(identityName == '一般會員'){
		location.href="${pageContext.request.contextPath}/manageGMemberDetail/get/"+adminId+"/"+memberId+".do";
	}else{
		location.href="${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do";
	}
}
</script>
</html>