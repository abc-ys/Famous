<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<br>
<h4>現金消費記錄</h4>
<p>
<form name="fm" method="post">
查詢條件:
<input type="radio" name="choice" id="choice" value="1" /> 一個月內訂單
<input type="radio" name="choice" id="choice" value="2" /> 未出貨訂單
<input type="radio" name="choice" id="choice" value="3" /> 退換貨訂單
<input type="radio" name="choice" id="choice" value="4" /> 六個月內訂單<br><br>
訂單編號:<input type="text" name="orderNo" id="orderNo"><br><br>
目前僅....
<input type="button" value="查詢" onclick="queryOrder()">
</form>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">訂單號碼</font></td>
	<td valign="top" Width="100"><font size="2">交易日期</font></td>
	<td valign="top" Width="100"><font size="2">付款方式</font></td>
	<td valign="top" Width="100"><font size="2">訂單金額</font></td>
	<td valign="top" Width="100"><font size="2">商品名稱</font></td>
	<td valign="top" Width="100"><font size="2">數量</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td></tr>
	<c:forEach var="hm" items="${orders}">
	<td Width="140" Height="35" valign="top"><font size="2"><a href="${pageContext.request.contextPath}/cashPayDetail.do">${hm.orderRid}</a></font></td>
	<td valign="top" Width="100"><font size="2">${hm.purchaseDate}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm.payMethod}</font></td>	
	<c:forEach var="hm2" items="${hm.orderDetail}">
	<td valign="top" Width="100"><font size="2">${hm2.productionCategory.sdCard.sdCardPrice.pPrice}</font></td>
	<td valign="top" Width="100"><font size="2">${hm2.productionCategory.sdCard.name}</font></td>
	<td valign="top" Width="100"><font size="2">${hm2.amount}</font></td>
	</c:forEach> 
	<td valign="top" Width="100"><font size="2">${hm.payStatus}</font></td>	
		</c:forEach> 
</table>
</body>
<script type="text/javascript">

function queryOrder(){          
	var obj =$('input[name=choice]:checked').val();   
  	var orderno = $("#orderNo").val(); ;

  	if(obj == undefined && orderno == ''){
  	  	alert("請輸入查詢條件");
  	}else{
  		document.fm.action="${pageContext.request.contextPath}/cashPay.do";
  		document.fm.submit();
    }
}
</script>
</html>