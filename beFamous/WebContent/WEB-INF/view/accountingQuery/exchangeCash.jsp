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
<form name="fm" method="post">
<br>
兌換金額管理
<p>
<input type="hidden" name="adminId" value="${adminId}">
<font size="2">&nbsp;查詢歷史資料:</font>&nbsp;
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
<input type="button" value="查詢" onclick="query()"/>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="80" Height="35"><font size="2">申請人</font></td>
	<td Width="80"><font size="2">申請日</font></td>
	<td Width="60"><font size="2">兌換金額</font></td>
	<td Width="70"><font size="2">稅額</font></td>
	<td Width="70"><font size="2">實際給付</font></td>
	<td Width="100"><font size="2">處理狀態</font></td>
	<td Width="110"><font size="2">給付日期</font></td>
	<td Width="100"><font size="2">備註</font></td>
	<td Width="80"><font size="2"></font></td></tr>
	
	<c:forEach var="hm" items="${gsiMoneys}">
<tr><td><font size="2"><a href="javascript:identity('123','${hm[1].id}','${hm[1].identityName}')">${hm[1].userName}</a></font></td>
	<fmt:parseDate var="exchangeDate" value="${hm[0].exchangeDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td><font size="2"></font><fmt:formatDate value='${exchangeDate}' pattern='yyyy-MM-dd' /></td>
	<td><font size="2">${hm[0].outgo}</font></td>
	<td><input type="text" size="5" name="exchangeTax"></td>
	<td><input type="text" size="5" name="paid"></td>
	<td>
	<select name="exchangeStatus"> 
	<option value="1">未處理</option> 
	<option value="2">已處理</option> 
	<option value="3">匯款失敗</option> 
	</select>
	</td>
	<td><input name="paidDate" type="text" class="fillbox" size="8" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.paidDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td>
	<td>
	<select name="memo" id="memo">
		<option value="0"></option> 
		<option value="帳號資料不齊">帳號資料不齊</option> 
		<option value="身分資料不齊">身分資料不齊</option> 
	</select>
	</td>
	<td><input type="button" value="完成修改" onclick="update('${hm[0].id}')"/></td>
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
     document.fm.action="${pageContext.request.contextPath}/exchangeCashs.do";
     document.fm.submit();
}
function update(gsiMoneyId){
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
	document.fm.action="${pageContext.request.contextPath}/updateExchangeCash.do?gsiMoneyId="+gsiMoneyId;
	document.fm.submit();    
}
</script>
</html>