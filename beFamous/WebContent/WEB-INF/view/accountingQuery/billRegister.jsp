<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
訂單發票登錄
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
<tr><td Width="70" Height="35"><font size="2">訂單號碼</font></td>
	<td Width="90"><font size="2">訂購時間</font></td>
	<td Width="110"><font size="2">付款方式</font></td>
	<td Width="100"><font size="2">總金額</font></td>
	<td Width="100"><font size="2">付款狀態</font></td>
	<td Width="100"><font size="2">發票號碼</font></td>
	<td Width="100"><font size="2">備註</font></td>
	<td Width="80"><font size="2"></font></td></tr>
	
	<c:forEach var="hm" items="${orders}">
<tr><td><font size="2"><a href="${pageContext.request.contextPath}/cashOrderDetail.do">${hm[0].orderRid}</a></font></td>
	<td><font size="2">${hm[0].purchaseDate}</font></td>
	<td><font size="2">${hm[0].payMethod}</font></td>
	<td><font size="2">${hm[1]}</font></td>
	<td>
	<select name="pay"> 
	<option value="尚未付款">尚未付款</option> 
	<option value="扣款失敗">扣款失敗</option> 
	<option value="已付款">已付款</option> 
	<option value="已退款">已退款</option> 
	</select>
	</td>
	<td><input type="text" size="5" name="billNo">${hm[0].billNo}</td>
	<td>
	<textarea cols="30" rows="3" name="note">${hm[0].memo2}</textarea> 
	</td>
	<td><input type="submit" value="完成修改" onclick="update()"/></td>
	</tr>
	</c:forEach>

</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/billRegister.do";
     document.fm.submit();
}
function update(){
    document.fm.action="${pageContext.request.contextPath}/updateBill.do";
    document.fm.submit();
}
</script>
</html>