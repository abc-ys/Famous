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
訂單詳細記錄
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" align="center" colspan="6"><font size="2">訂單號碼:&nbsp${orderDetail[0].order.orderRid}&nbsp&nbsp訂購時間:&nbsp${orderDetail[0].order.purchaseDate}&nbsp&nbsp</font></td></tr>
<tr><td Width="40" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="200"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="200"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="40"><font size="2">單價</font></td>
	<td valign="top" Width="80"><font size="2">訂單價格</font></td>
	<td valign="top" Width="100"><font size="2">下載狀態</font></td></tr>
<tr><td Height="30" valign="top"><font size="2">1</font></td>
	<td valign="top"><font size="2">${orderDetail[0].productionCategory.song.name}</font></td>
	<td valign="top"><font size="2">${orderDetail[0].productionCategory.album.name}</font></td>
	<td valign="top"><font size="2">${orderDetail[0].productionCategory.song.songPrice.price}</font></td>
	<td valign="top"><font size="2">${orderDetail[0].productionCategory.song.songPrice.price}</font></td>
	<td valign="top"><font size="2">未下載</font></td></tr>
<tr><td Height="30" valign="top"><font size="2">2</font></td>
	<td valign="top"><font size="2">${orderDetail[1].productionCategory.song.name}</font></td>
	<td valign="top"><font size="2">${orderDetail[1].productionCategory.album.name}</font></td>
	<td valign="top"><font size="2">${orderDetail[1].productionCategory.song.songPrice.price}</font></td>
	<td valign="top"><font size="2">${orderDetail[1].productionCategory.song.songPrice.price}</font></td>
	<td valign="top"><font size="2">已下載</font></td></tr>
<tr><td Height="35" align="center" colspan="6"><font size="2">商品總金額:GSiMoney&nbsp${tPrice}&nbsp元</font></td></tr>
</table>
</form>
</body>
</html>