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


<c:set var="totalPrice" value="${1000}"/>
<table border="1">
<tr>
<td>編號</td>
<td>會員帳號</td>
<td>儲值總次數</td>
<td>儲值總金額</td>
</tr>
<c:forEach var="hm" varStatus="status" items="${arMember}" >
<tr>
<td>${status.count}</td>
<td>${hm.email}</td>
<td>${fn:length(hm.order)}</td>
<td>${totalPrice}</td>
</tr>
</c:forEach>


</table>
<br>
活動儲值總次數:${totalCount}
</body>
</html>