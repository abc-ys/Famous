<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step001.jpg width="100" height="35"></td>
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step002-b.jpg width="100" height="35"></td>
<td Width="200" Height="30"><img alt="" src=${pageContext.request.contextPath}/images/step003-b.jpg width="100" height="35"></td>
</table>
<br>
您本次的訂購程序已經完成!<br>您獲得的 GSiBonus ${discountBonus} 點將於明天入帳!
<p>
感謝您的支持並承蒙訂購，訂單編號: ${order.id}。您可以在[現金消費記錄]，查詢您的訂單以及處理進度。
<p>
</body>
</html>