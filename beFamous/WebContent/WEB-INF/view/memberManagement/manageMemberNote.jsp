<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<p class="topic">修改記錄</p>
<form name="note">
	<c:forEach var="hm" items="${mds}">
	時間:${hm.createDate}<br>
	管理者:${hm.creator}<br>
	修正事項:${hm.content}<br><hr>
	</c:forEach> 
</form>
</body>
</html>