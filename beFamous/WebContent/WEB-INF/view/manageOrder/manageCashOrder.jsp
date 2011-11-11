<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" id='fm' method="post">
<br>
現金訂單管理
<p>
訂購者帳號:&nbsp<input type="text" name="userMail">&nbsp<p>
訂單編號:&nbsp<input type="text" name="orderNumber">&nbsp<p>
訂購日期區段:&nbsp
<input name="MOPEND" type="text" class="fillbox" readonly>&nbsp;
<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
<input name="MCLOSED" type="text" class="fillbox" readonly >&nbsp;
<A HREF="javascript:show_calendar('fm.MCLOSED');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
處理狀態:&nbsp
<select name="handleStatus"> 
<option value="0"></option> 
<option value="處理中">處理中</option> 
<option value="已儲值">已儲值</option> 
<option value="已出貨">已出貨</option> 
</select>
<p>
<center><input type="submit" value="查詢" onclick="query()"/>&nbsp;&nbsp;<input type="reset" value="全部清除"/></center>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單編號</font></td>
	<td valign="top" Width="100"><font size="2">總金額</font></td>
	<td valign="top" Width="100"><font size="2">訂購者</font></td>
	<td valign="top" Width="100"><font size="2">付款方式</font></td>
	<td valign="top" Width="100"><font size="2">訂購日期</font></td>
	<td valign="top" Width="100"><font size="2">商品內容</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
	
	<td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/cashOrderDetail.do">${orders[0].orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${tPrice}</font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)"  onclick="identity('123','${orders[0].member.memberId}','${orders[0].member.identityName}')">${orders[0].member.email}</a></font></td>	
	<td valign="top" Width="100"><font size="2">${orders[0].payMethod}</font></td>	
	<td valign="top" Width="100"><font size="2">${orders[0].purchaseDate}</font></td>	
	<c:forEach var="hm2" items="${orders[0].orderDetail}">
	<td valign="top" Width="100"><font size="2">${hm2.productionCategory.sdCard.name}</font></td>
	</c:forEach> 
	<td valign="top" Width="100"><font size="2">${orders[0].payStatus}<br>${orders[0].handleStatus}</font></td>
	<td valign="top" Width="100"><font size="2">${orders[0].memo2}</font></td><tr>
	
    <td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/cashOrderDetail.do">${orders[1].orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${tPrice2}</font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)" onclick="identity('123','${orders[1].member.memberId}','${orders[1].member.identityName}')">${orders[1].member.email}</a></font></td>	
	<td valign="top" Width="100"><font size="2">${orders[1].payMethod}</font></td>	
	<td valign="top" Width="100"><font size="2">${orders[1].purchaseDate}</font></td>	
	<c:forEach var="hm3" items="${orders[1].orderDetail}">
	<td valign="top" Width="100"><font size="2">${hm3.productionCategory.prePaid.name}</font></td>
	</c:forEach> 
	<td valign="top" Width="100"><font size="2">${orders[1].payStatus}<br>${orders[1].handleStatus}</font></td>
	<td valign="top" Width="100"><font size="2">${orders[1].memo2}</font></td><tr>
</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/manageCashOrder.do";
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