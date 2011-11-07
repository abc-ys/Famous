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
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step001-b.jpg width="100" height="35"></td>
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step002-b.jpg width="100" height="35"></td>
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step003.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>請選擇您要購買的品項</b></font></font></td></table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">商品名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">商品總價<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">GSiBonus折抵價<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">數量<br></font></td>
<tr>
<td><font size="2.5">
<pre>
${sdCard[0][0].name}<br>
${sdCard[0][1].name}<br>
${sdCard[0][2].name}
</pre>
</font></td>
<td>
<font size="2">
<INPUT type=radio name=price id="price" value="${sdCard[1][0].pPrice}">NT$ ${sdCard[1][0].pPrice}<br>
<INPUT type=radio name=price id="price" value="${sdCard[1][1].pPrice}">NT$ ${sdCard[1][1].pPrice}<br>
<INPUT type=radio name=price id="price" value="${sdCard[1][2].pPrice}">NT$ ${sdCard[1][2].pPrice}
</font></td>
<td>
<font size="2">
<INPUT type=radio name=price id="price" value="${sdCard[1][0].discountPrice}">NT$ ${sdCard[1][0].discountPrice}+${sdCard[1][0].discountBonus}點<br>
<INPUT type=radio name=price id="price" value="${sdCard[1][1].discountPrice}">NT$ ${sdCard[1][1].discountPrice}+${sdCard[1][1].discountBonus}點<br>
<INPUT type=radio name=price id="price" value="${sdCard[1][2].discountPrice}">NT$ ${sdCard[1][2].discountPrice}+${sdCard[1][2].discountBonus}點
</font></td>
<td>
<input type="text" name="msg1" size="2"><br>
<input type="text" name="msg2" size="2"><br>
<input type="text" name="msg3" size="2">
</td>
</table>
<br>
<p>
<b>購買GSiSD卡我們送您GSiBonus，可用來折抵日後的消費!</b>
<center><input type="submit" value="購買" onclick="add()"/></center>

<INPUT type=hidden name=sdprice value="">
<INPUT type=hidden name=sdmsg value="">
<INPUT type=hidden name=discountBonus value="">
<INPUT type=hidden name=sdName value="">

</form>
</body>
<script>
function add(){
	var price;
	var msg;
	var sdName;
	var discountBonus = '50';
	if($('input[name=price]').get(0).checked == true){
		price=document.fm.price[0].value;
		msg=document.fm.msg1.value;
		sdName='${sdCard[0][0].name}';
	}
	if($('input[name=price]').get(1).checked == true){
		price=document.fm.price[1].value;
		msg=document.fm.msg2.value;
		sdName='${sdCard[0][1].name}';
	}
	if($('input[name=price]').get(2).checked == true){
		price=document.fm.price[2].value;
		msg=document.fm.msg3.value;
		sdName='${sdCard[0][2].name}';
	}
	if($('input[name=price]').get(3).checked == true){
		price=document.fm.price[3].value;
		msg=document.fm.msg1.value;
		sdName='${sdCard[0][0].name}';
	}
	if($('input[name=price]').get(4).checked == true){
		price=document.fm.price[4].value;
		msg=document.fm.msg2.value;
		sdName='${sdCard[0][1].name}';
	}
	if($('input[name=price]').get(5).checked == true){
		price=document.fm.price[5].value;
		msg=document.fm.msg3.value;
		sdName='${sdCard[0][2].name}';
	}
	document.fm.sdprice.value=price;
	document.fm.sdmsg.value=msg;
	document.fm.discountBonus.value=discountBonus;
	document.fm.sdName.value=sdName;
	
     document.fm.action="${pageContext.request.contextPath}/sdcardTwo.do";
     document.fm.submit();
}
</script>
</html>