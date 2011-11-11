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
<td>會員帳號</td>
<td>會員ID</td>
<td>身份</td>
<td>加入會員時間</td>
</tr>

<c:forEach var="arMember" varStatus="status" items="${arMember}" >
<td>${status.count}</td>
<td>${arMember.email }</td>
<td>${arMember.memberId }</td>
<td>${arMember.identityName }</td>
<td>${arMember.createDate }</td>
</c:forEach>
</table>
購買推薦專輯會員總人數:${fn:length(arMember)}
<input type="button" name="666" value="回上頁" onclick="javascript:history.back(-1);">
</body>
</html>