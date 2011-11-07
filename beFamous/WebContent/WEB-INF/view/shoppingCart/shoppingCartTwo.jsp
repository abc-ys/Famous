<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/001.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/002.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/003.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
點選結帳後即可享受您購買的專輯
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">專輯封面/歌曲名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">專輯名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">創作人<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">價格<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus<br></font></td>
<tr>
<td Width="200" Height="30" border="0"><font size="2"><img alt="" src=${cover} width="100" height="35"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${aName}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${aUserName}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${aprice}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus<br></font></td>
<tr>
<td Width="200" Height="30" border="0"><font size="2">${sName}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${aName2}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${sUserName}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${sprice}<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus<br></font></td>
<tr>
<INPUT type=hidden name=tBonus value="${tBonus}">
<td Width="200" Height="30" border="0" colspan="5" align="right"><font size="2">購物車共有 件商品，總金額 GSiMoney$ ${tPrice}元+ ${tBonus}點<br></font></td>
</table>
<br>
<center><input type="submit" value="確認無誤結帳" onclick="add()"/></center>
</form>
</body>
<script>
function add(){
     document.fm.action="${pageContext.request.contextPath}/shoppingCartThree.do";
     document.fm.submit();
}
</script>
</html>