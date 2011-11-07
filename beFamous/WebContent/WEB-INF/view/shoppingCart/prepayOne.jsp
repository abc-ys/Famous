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
<!-- <INPUT type="hidden" name="memberId" value=""> -->
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step001-b.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step002-b.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step003.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>選擇儲值點數</b></font></td></table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">方案名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">方案總價<br></font></td>
<tr>
<td>
<font size="2">
<INPUT type=radio name=prePay value="${prePay[1][0].pPrice}">${prePay[0][0].name}<br>
<INPUT type=radio name=prePay value="${prePay[1][1].pPrice}">${prePay[0][1].name}<br>
<INPUT type=radio name=prePay value="${prePay[1][2].pPrice}">${prePay[0][2].name}<br>
<INPUT type=radio name=prePay value="${prePay[1][3].pPrice}">${prePay[0][3].name}<br>
<INPUT type=radio name=prePay value="${prePay[1][4].pPrice}">${prePay[0][4].name}
</font></td>
<td><font size="2">
<pre>
NT$ ${prePay[1][0].pPrice}<br>
NT$ ${prePay[1][1].pPrice}<br>
NT$ ${prePay[1][2].pPrice}<br>
NT$ ${prePay[1][3].pPrice}<br>
NT$ ${prePay[1][4].pPrice}
</pre>
</font></td>
</table>
<br>
<b>您所儲值的點數無使用期限</b>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>請閱讀使用同意書</b></font></font></td></table>
<p>
<textarea cols="60" rows="6" name="textBlock"> 
同意書內容
</textarea> 
<p>
<INPUT type="checkbox" name="agree1" id="agree1" value="agree"><font size="2">我已閱讀同意書並要進行儲值</font>
<center><input type="submit" value="進行儲值" onclick="add()"/></center>
</form>
</body>
<script>
function add(){
	
	if($("#agree1").attr('checked')==undefined){
		alert('還未勾選已閱讀同意書');
		return;
	}
	
     document.fm.action="${pageContext.request.contextPath}/prepayTwo.do";
     document.fm.submit();
}
</script>
</html>