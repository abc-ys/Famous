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
GSiMoney訂單管理
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
<option value="未下載">未下載</option> 
<option value="已下載">已下載</option> 
</select>
<p>
<center><input type="submit" value="查詢" onclick="query()"/>&nbsp;&nbsp;<input type="reset" value="全部清除"/></center>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單編號</font></td>
	<td valign="top" Width="100"><font size="2">總金額</font></td>
	<td valign="top" Width="100"><font size="2">訂購者</font></td>
	<td valign="top" Width="100"><font size="2">訂購日期</font></td>
	<td valign="top" Width="100"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
	
	<td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/gsimoneyOrderDetail.do">${orders[0].orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${tPrice}</font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)"  onclick="identity('123','${orders[0].member.memberId}','${orders[0].member.identityName}')">${orders[0].member.email}</a></font></td>	
	<td valign="top" Width="100"><font size="2">${orders[0].purchaseDate}</font></td>	
	<td valign="top" Width="100"><c:forEach var="hm" items="${orders[0].orderDetail}">
	<font size="2">${hm.productionCategory.song.name}</font><br>
	</c:forEach></td>
	<td valign="top" Width="100"><c:forEach var="hm" items="${orders[0].orderDetail}">
	<font size="2">${hm.productionCategory.song.album.name}</font><br>
	</c:forEach></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)" onclick="note()">編輯</a></font></td><tr>
	
    <td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/gsimoneyOrderDetail.do">${orders[1].orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${tPrice2}</font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)"  onclick="identity('123','${orders[1].member.memberId}','${orders[1].member.identityName}')">${orders[1].member.email}</a></font></td>	
	<td valign="top" Width="100"><font size="2">${orders[1].purchaseDate}</font></td>
	<td valign="top" Width="100"><c:forEach var="hm2" items="${orders[1].orderDetail}">
	<font size="2">${hm2.productionCategory.song.name}</font><br>
	</c:forEach></td>
	<td valign="top" Width="100"><c:forEach var="hm2" items="${orders[1].orderDetail}">
	<font size="2">${hm2.productionCategory.album.name}</font><br>
	</c:forEach></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)" onclick="note()">編輯</a></font></td><tr>
</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/manageGSiMoneyOrder.do";
     document.fm.submit();
}
function note(){
	window.open("${pageContext.request.contextPath}/gsimoneyOrderNote.do","parent","height=400,width=600,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no");
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