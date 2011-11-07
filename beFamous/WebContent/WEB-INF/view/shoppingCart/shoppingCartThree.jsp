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
結帳完成，請下載您所購買的歌曲!<br>您本次消費可獲得GSiBonus ${tBonus}點，將於明日入帳。
<p>
以下為您購買後尚未下載的歌曲，請選擇您要下載的歌曲再按下載按鈕即可。<br>若現在不想下載，可以之後到<a href="http://tw.yahoo.com">下載管理區中</a>下載。
</form>
</body>
<script>
function add(){
     document.fm.action="${pageContext.request.contextPath}/shoppingCartThree.do";
     document.fm.submit();
}
</script>
</html>