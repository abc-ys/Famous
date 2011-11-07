<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

您的訂單編號；&nbsp${orderNumber}，品名；&nbsp${productName}&nbspx&nbsp${amount}，申請退貨。<p>
請列印<a href="${pageContext.request.contextPath}/returnApply.doc">退貨申請單</a>，將未拆封的GSiSD卡連同退貨申請單於三天內一併寄回本公司以辦理退費程序。<p>
*感謝您的支持與愛護!
</body>
</html>