<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>


<table border="1">
<tr>
<td>編號</td>
<td>訂單號碼</td>
<td>會員帳號</td>
<td>儲值金額</td>
</tr>

<c:forEach var="hm" varStatus="status" items="${arOrder}" >

<c:forEach var="orderDetail" varStatus="status1" items="${hm.orderDetail}" >
<c:forEach var="price" varStatus="status2" items="${orderDetail.productionCategory.prePaid.prePaidPrice}" >
<tr>
<td>${status.count}</td>
<td>${hm.billNo}</td>
<td>${hm.member.email}</td>
<td>${price.pPrice}</td>
</tr>
</c:forEach>
</c:forEach>
</c:forEach>

</table>
<br>
活動儲值總金額:${totalPrice}

</body>
</html>