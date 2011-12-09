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
<h4>GSiMoney收支表</h4>
<p>
<font size="2">&nbsp;查詢期間:</font>&nbsp;
<select name="queryYear" id="queryYear"> 
	<option value="0"></option> 
	<option value="2011" <c:if test="${year=='2011'}">selected = "true"</c:if> >2011</option>
	<option value="2012" <c:if test="${year=='2012'}">selected = "true"</c:if> >2012</option> 
	<option value="2013" <c:if test="${year=='2013'}">selected = "true"</c:if> >2013</option> 
	<option value="2014" <c:if test="${year=='2014'}">selected = "true"</c:if> >2014</option> 
</select>&nbsp;年&nbsp;
<select name="queryMonth" id="queryMonth"> 
	<option value="0"></option> 
	<option value="1" <c:if test="${month=='1'}">selected = "true"</c:if> >1</option> 
	<option value="2" <c:if test="${month=='2'}">selected = "true"</c:if> >2</option> 
	<option value="3" <c:if test="${month=='3'}">selected = "true"</c:if> >3</option> 
	<option value="4" <c:if test="${month=='4'}">selected = "true"</c:if> >4</option> 
	<option value="5" <c:if test="${month=='5'}">selected = "true"</c:if> >5</option> 
	<option value="6" <c:if test="${month=='6'}">selected = "true"</c:if> >6</option> 
	<option value="7" <c:if test="${month=='7'}">selected = "true"</c:if> >7</option> 
	<option value="8" <c:if test="${month=='8'}">selected = "true"</c:if> >8</option> 
	<option value="9" <c:if test="${month=='9'}">selected = "true"</c:if> >9</option> 
	<option value="10" <c:if test="${month=='10'}">selected = "true"</c:if> >10</option> 
	<option value="11" <c:if test="${month=='11'}">selected = "true"</c:if> >11</option> 
	<option value="12" <c:if test="${month=='12'}">selected = "true"</c:if> >12</option> 
</select>&nbsp;月&nbsp;&nbsp;
<input type="hidden" name="year" id="year"/>
<input type="hidden" name="month" id="month"/>
<input type="button" value="查詢" onclick="query()">
<p><fmt:parseDate var="lastMonth" value="${lastDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
GSiMoney結餘:&nbsp;${newOrder.balance} 元<p>
截至到&nbsp;<fmt:formatDate value='${lastMonth}' pattern='yyyy-MM-dd' />止您可兌換的GSiMoney金額:&nbsp;
	<c:if test="${noRecord == 'T'}">0元</c:if>
	<c:if test="${noRecord == 'F'}">${lastOrder.balance}元</c:if>
	
 <fmt:parseNumber var="i" type="number" value="${newOrder.balance}" />
 <fmt:parseNumber var="j" type="number" value="${lastOrder.balance}" />
<c:if test="${i >= j and j>=500 and noExchange=='T'}"><input type="submit" value="申請兌換" onclick="apply('${year}','${month}')"/></c:if>

<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<tr>
		<td Width="140" Height="35" valign="top"><font size="2">訂單號碼</font></td>
		<td valign="top" Width="100"><font size="2">交易日期</font></td>
		<td valign="top" Width="100"><font size="2">收入</font></td>
		<td valign="top" Width="100"><font size="2">支出</font></td>
		<td valign="top" Width="100"><font size="2">結餘</font></td>
		<td valign="top" Width="100"><font size="2">備註</font></td></tr>
	<c:forEach var="hm" items="${orders}" varStatus="status">
	<tr><td Width="100" Height="30" valign="top"><font size="2">
		<c:choose>
			<c:when test="${hm.gsiMoney.transactionType == 1}"><a href="${pageContext.request.contextPath}/cashPayDetail.do?orderId=${hm.id}">${hm.id}</a></c:when>
			<c:otherwise><a href="${pageContext.request.contextPath}/payDetailRecord.do?orderId=${hm.id}">${hm.id}</a></c:otherwise>
		</c:choose>			
		</font></td>
		<fmt:parseDate var="dateObj" value="${hm.billDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<td valign="top" Width="100"><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></td>
		<td valign="top" Width="100"><font size="2">${hm.gsiMoney.income}</font></td>		
		<td valign="top" Width="100"><font size="2">${hm.gsiMoney.outgo}</font></td>
		<td valign="top" Width="100"><font size="2">${hm.gsiMoney.balance}</font></td>
		<td valign="top" Width="140"><font size="2">${hm.gsiMoney.memo}</font></td></tr>
	</c:forEach>
</table>
<input type="hidden" name="userId" value="${userId}">
</form>
</body>
<script>
function query(){
	var nowDate = new Date();
	var nowYear = nowDate.getFullYear();
	var nowMonth = nowDate.getMonth()+1;
	if($('#queryYear').val()=="0" && $('#queryMonth').val()=="0" ){
		 document.fm.year.value=nowYear;
		 document.fm.month.value=nowMonth;
	}else{
		 document.fm.year.value=$('#queryYear').val();
		 document.fm.month.value=$('#queryMonth').val();
	}
     document.fm.action="${pageContext.request.contextPath}/queryIncomeOutgo.do";
     document.fm.submit();
}
function apply(year,month){
	 document.fm.year.value=year;
	 document.fm.month.value=month;
	 document.fm.action="${pageContext.request.contextPath}/applyExchange.do";
	 document.fm.submit();	
}
</script>
</html>