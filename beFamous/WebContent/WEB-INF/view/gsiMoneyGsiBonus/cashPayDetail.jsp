<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
詳細現金消費記錄
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" align="center" colspan="9"><font size="2">訂單號碼:&nbsp${orderDetail.order.orderRid}&nbsp&nbsp訂購時間:&nbsp${orderDetail.order.purchaseDate}&nbsp&nbsp付款方式:&nbsp${orderDetail.order.payMethod}</font></td></tr>
<tr><td Width="40" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="140"><font size="2">商品名稱</font></td>
	<td valign="top" Width="100"><font size="2">單價</font></td>
	<td valign="top" Width="100"><font size="2">數量</font></td>
	<td valign="top" Width="100"><font size="2">訂單價格</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td>
	<td valign="top" Width="100"><font size="2">發票狀態</font></td>
	<td valign="top" Width="100"><font size="2">取消/退貨</font></td>
	<td valign="top" Width="100"><font size="2">備註</font></td></tr>
<tr><td Height="30" valign="top"><font size="2">1</font></td>
	<td valign="top"><font size="2">${orderDetail.productionCategory.sdCard.name}</font></td>
	<td valign="top"><font size="2">${orderDetail.productionCategory.sdCard.sdCardPrice.pPrice}</font></td>
	<td valign="top"><font size="2">${orderDetail.amount}</font></td>
	<td valign="top"><font size="2">${orderDetail.productionCategory.sdCard.sdCardPrice.pPrice}</font></td>
	<td valign="top"><font size="2">${orderDetail.order.handleStatus}</font></td>
	<td valign="top"><font size="2">${orderDetail.order.billStatus}</font></td>
	<td valign="top"><font size="2">
	<a href="javascript:void(0)" onclick="cancel()">取消</a><br>
	<a href="javascript:void(0)" onclick="returnGoods('${orderDetail.order.orderRid}','${orderDetail.productionCategory.sdCard.name}','${orderDetail.amount}')">退貨</a></font></td>
	<td valign="top"><font size="2">${orderDetail.order.memo1}</font></td></tr>
<tr><td Width="140" Height="35" align="center" colspan="9"><font size="2">商品總金額: NT&nbsp ${tPrice} &nbsp元</font></td></tr>
</table>
</form>
</body>
<script>
function cancel(){
	alert("取消");
	document.fm.action="${pageContext.request.contextPath}/cashPayDetail.do";
	document.fm.submit();
}
function returnGoods(orderNumber,productName,amount){
	//alert("退貨");
	var url = "${pageContext.request.contextPath}/returnGoods.do?orderNumber="+orderNumber+"&productName="+productName+"&amount="+amount;    
	//以下兩行是將中文字做編碼
	url = encodeURI(url); 
	url = encodeURI(url);
	window.open(url,"parent","height=300,width=600,location=no,scrollbars=yes,toolbar=no,directories=no,menubar=no,directories=no");
}
</script>
</html>