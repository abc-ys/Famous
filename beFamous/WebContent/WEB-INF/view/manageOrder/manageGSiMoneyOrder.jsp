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
GSiMoney訂單管理
<p>
訂購者帳號:&nbsp;<input type="text" id="userEmail" name="userEmail" value="${userEmail}">&nbsp;<p>
訂單編號:&nbsp;<input type="text" id="orderId" name="orderId" value="${orderId}">&nbsp;<p>
訂購日期區段:&nbsp;
<input name="startDate" id="startDate" type="text" class="fillbox" value="${startDate}">&nbsp;
<A HREF="javascript:show_calendar('fm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp;-&nbsp;
<input name="endDate" id="endDate" type="text" class="fillbox" value="${endDate}">&nbsp;
<A HREF="javascript:show_calendar('fm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
<p>
<center><input type="button" value="查詢" onclick="query()"/>&nbsp;&nbsp;<input type="reset" value="全部清除"/></center>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單編號</font></td>
	<td valign="top" Width="100"><font size="2">總金額</font></td>
	<td valign="top" Width="100"><font size="2">訂購者</font></td>
	<td valign="top" Width="100"><font size="2">訂購日期</font></td>
	<td valign="top" Width="100"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
<c:forEach var="hm" items="${orders}">
	<tr><td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/gsimoneyOrderDetail.do?orderId=${hm[0].id}">${hm[0].id}</a></font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].totalMoney}元<c:if test="${hm[0].totalBonus>0}">+${hm[0].totalBonus}點</c:if></font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)"  onclick="identity('123','${hm[1].id}','${hm[1].identityName}')">${hm[1].email}</a></font></td>	
	<fmt:parseDate var="purchaseDate" value="${hm[0].purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/>
	<td valign="top" Width="100"><font size="2"><fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' /></font></td>	
	<td valign="top" Width="100">
	<c:forEach var="hm2"  items="${hm[2]}">
		<c:if test="${hm2[0]==1}">
			<font size="2"></font><br>
		</c:if>
		<c:if test="${hm2[0]==2}">
			<font size="2">${hm2[1].name}</font><br>
		</c:if>		
	</c:forEach></td>
	<td valign="top" Width="100">
	<c:forEach var="hm3"  items="${hm[2]}">
		<c:if test="${hm3[0]==1}">
			<font size="2">${hm3[1].name}</font><br>
		</c:if>
		<c:if test="${hm3[0]==2}">
			<font size="2">${hm3[1].album.name}</font><br>
		</c:if>		
	</c:forEach></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:void(0)" onclick="note('${adminId}','${hm[0].id}')">編輯</a></font></td></tr>
	</c:forEach>
</table>
<input type="hidden" name="adminId" value="${adminId}">
</form>
</body>
<script>
function query(){
	if($("#userEmail").val() == "" && $("#orderId").val() == "" && $("#startDate").val() == "" && $("#endDate").val() == "")  
    {
		alert("請輸入查詢條件");
		return false; 
    }else{
    	document.fm.action="${pageContext.request.contextPath}/manageGSiMoneyOrders.do";
    	document.fm.submit();
    }
}
function note(adminId, orderId){
	window.open("${pageContext.request.contextPath}/gsimoneyOrderNote.do?adminId="+adminId+"&orderId="+orderId,"parent","height=400,width=600,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no");
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