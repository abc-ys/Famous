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
銷售記錄
<p>
<font size="2">&nbsp查詢歷史資料:</font>&nbsp
<select name="year"> 
<option value="0"></option> 
<option value="99">99</option> 
<option value="100">100</option> 
<option value="101">101</option> 
<option value="102">102</option> 
</select>&nbsp年&nbsp
<select name="month"> 
<option value="0"></option> 
<option value="1">1</option> 
<option value="2">2</option> 
<option value="3">3</option> 
<option value="4">4</option> 
</select>&nbsp月&nbsp&nbsp
<input type="submit" value="查詢" onclick="query()"/>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="100"><font size="2">交易日期</font></td>
	<td valign="top" Width="100"><font size="2">單價</font></td>
	<td valign="top" Width="100"><font size="2">數量</font></td>
	<td valign="top" Width="100"><font size="2">總金額</font></td>
	<td valign="top" Width="100"><font size="2">淨收益</font></td></tr>
<tr><td Width="100" Height="30" valign="top"><font size="2">${orderDetail.productionCategory.song.name}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.productionCategory.album.name}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.order.purchaseDate}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.productionCategory.song.songPrice.price}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail.amount}</font></td>
	<td valign="top" Width="140"><font size="2">${tPrice}</font></td>
	<td valign="top" Width="140"><font size="2">${income}</font></td></tr>
<tr><td Width="100" Height="30" valign="top"><font size="2">${orderDetail2.productionCategory.song.name}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.productionCategory.album.name}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.order.purchaseDate}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.productionCategory.song.songPrice.price}</font></td>
	<td valign="top" Width="100"><font size="2">${orderDetail2.amount}</font></td>
	<td valign="top" Width="140"><font size="2">${tPrice2}</font></td>
	<td valign="top" Width="140"><font size="2">${income2}</font></td></tr>

</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/saleRecord.do";
     document.fm.submit();
}
</script>
</html>