<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<fmt:parseDate var="createDate" value="${news.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 	
<fmt:formatDate value='${createDate}' pattern='yyyy-MM-dd' /> &nbsp;${news.newsSouce}<br><br>
展覽名稱:${news.newsName}<br><br>
展覽介紹:${news.content}<br><br>
</body>
</html>