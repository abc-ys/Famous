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
<form name="fm" method="post">
<br>
兌換金額管理
<p>
<font size="2">&nbsp查詢歷史資料:</font>&nbsp
<select name="year"> 
<option value=""></option> 
<option value="99">99</option> 
<option value="100">100</option> 
<option value="101">101</option> 
<option value="102">102</option> 
</select>&nbsp年&nbsp
<select name="month"> 
<option value=""></option> 
<option value="1">1</option> 
<option value="2">2</option> 
<option value="3">3</option> 
<option value="4">4</option> 
</select>&nbsp月&nbsp&nbsp
<input type="submit" value="查詢" onclick="query()"/>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="80" Height="35"><font size="2">申請人</font></td>
	<td Width="80"><font size="2">申請日</font></td>
	<td Width="80"><font size="2">兌換區間</font></td>
	<td Width="60"><font size="2">兌換金額</font></td>
	<td Width="70"><font size="2">稅額</font></td>
	<td Width="70"><font size="2">實際給付</font></td>
	<td Width="100"><font size="2">處理狀態</font></td>
	<td Width="110"><font size="2">給付日期</font></td>
	<td Width="100"><font size="2">備註</font></td>
	<td Width="80"><font size="2"></font></td></tr>
	
	<c:forEach var="hm" items="${members}">
<tr><td><font size="2"><a href="javascript:identity('123','${hm.memberId}','${hm.identityName}')">${hm.userName}</a></font></td>
	<td><font size="2">${hm.gsiMoney.exchangeDate}</font></td>
	<td><font size="2">${hm.gsiMoney.exchangeStartDate}-${hm.gsiMoney.exchangeEndDate}</font></td>
	<td><font size="2">${hm.gsiMoney.exchange}</font></td>
	<td><input type="text" size="5" name="exchangeTax"></td>
	<td><input type="text" size="5" name="paid"></td>
	<td>
	<select name="handleStatus"> 
	<option value="未處理">未處理</option> 
	<option value="已處理">已處理</option> 
	<option value="匯款失敗">匯款失敗</option> 
	</select>
	</td>
	<td><input name="MOPEND" type="text" class="fillbox" size="8" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td>
	<td>
	<select name="note"> 
	<option value="帳號資料不齊">帳號資料不齊</option> 
	<option value="身分資料不齊">身分資料不齊</option> 
	</select>
	</td>
	<td><input type="submit" value="完成修改" onclick="update()"/></td>
	</tr>
	</c:forEach>

</table>
</form>
</body>
<script>
function identity(adminId,memberId,identityName){	
	if(identityName == '一般會員'){
		location.href="${pageContext.request.contextPath}/manageGMemberDetail/get/"+adminId+"/"+memberId+".do";
	}else{
		location.href="${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do";
	}
}
function query(){
     document.fm.action="${pageContext.request.contextPath}/exchangeCash.do";
     document.fm.submit();
}
function update(){
    document.fm.action="${pageContext.request.contextPath}/updateExchangeCash.do";
    document.fm.submit();
}
</script>
</html>