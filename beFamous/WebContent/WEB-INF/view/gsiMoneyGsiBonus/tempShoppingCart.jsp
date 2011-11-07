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
<tr><td Width="200" Height="30">${albumSong[0].name}</td>
<td Width="200" Height="30">${albumSong[2]}元</td></tr>
<tr><td Width="200" Height="30">${albumSong[1].name}</td>
<td Width="200" Height="30">${albumSong[1].songPrice.discountPrice}元+&nbsp${albumSong[1].songPrice.discountBonus}點</td></tr>
</table>
<hr size="5" align="center" noshade width="90%" color="0000ff">
<table border="0" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" align="right">總價:&nbsp${albumSong[3]}元+&nbsp${albumSong[4]}點</td>
</table>
<center><input type="submit" value="開始結帳" onclick="add()"/></center>

</form>
</body>
<script>
function add(){
     document.fm.action="${pageContext.request.contextPath}/shoppingCartOne.do";
     document.fm.submit();
}
</script>
</html>