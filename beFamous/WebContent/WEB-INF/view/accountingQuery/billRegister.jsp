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
訂單發票登錄
<p>
<input type="hidden" name="adminId" value="${adminId}">
<font size="2">&nbsp;查詢歷史資料:</font>&nbsp;
<select name="queryYear" id="queryYear"> 
	<option value="0"></option> 
	<option value="2011" <c:if test="${year=='2011'}">selected = "true"</c:if> >2011</option>
	<option value="2012" <c:if test="${year=='20121'}">selected = "true"</c:if> >2012</option> 
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
<input type="submit" value="查詢" onclick="query()"/>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="100" Height="35"><font size="2">訂單號碼</font></td>
	<td Width="90"><font size="2">訂購時間</font></td>
	<td Width="110"><font size="2">付款方式</font></td>
	<td Width="100"><font size="2">總金額</font></td>
	<td Width="100"><font size="2">付款狀態</font></td>
	<td Width="100"><font size="2">發票號碼</font></td>
	<td Width="100"><font size="2">備註</font></td>
	<td Width="80"><font size="2"></font></td></tr>
	
	<c:forEach var="hm" items="${orders}">
<tr><td><font size="2"><a href="${pageContext.request.contextPath}/cashOrderDetail.do?orderId=${hm[0].id}">${hm[0].id}</a></font></td>
	<fmt:parseDate var="purchaseDate" value="${hm[0].purchaseDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td><font size="2"><fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd' /></font></td>
	<td><font size="2">
		<c:if test="${hm[0].payMethod==1}">銀行轉帳</c:if>
		<c:if test="${hm[0].payMethod==2}">信用卡</c:if>
	</font></td>
	<td><font size="2">${hm[1].cash}</font></td>
	<td>
	<select name="payStatus"> 
		<option value="1">尚未付款</option> 
		<option value="2">扣款失敗</option> 
		<option value="3">已付款</option> 
		<option value="4">已退款</option> 
	</select>
	</td>
	<td><input type="text" size="15" name="billNo">${hm[0].billNo}</td>
	<td>
	<textarea cols="30" rows="3" name="memo2">${hm[0].memo2}</textarea> 
	</td>
	<td><input type="submit" value="完成修改" onclick="update(${hm[0].id})"/></td>
	</tr>
	</c:forEach>

</table>
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
     document.fm.action="${pageContext.request.contextPath}/billRegisters.do";
     document.fm.submit();
}
function update(orderId){
    document.fm.action="${pageContext.request.contextPath}/updateBill.do?orderId="+orderId;
    document.fm.submit();
}
</script>
</html>