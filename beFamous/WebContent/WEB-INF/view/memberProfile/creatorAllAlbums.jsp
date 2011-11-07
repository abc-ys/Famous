<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gsimedia" uri="http://www.gsi-media.com/" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}"/>/css/cell.css" type="text/css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/js/cell.js"></script>
</head>
<body>
	<br>
	<h4>所有專輯</h4>
    <gsimedia:albumTable listName="albumList"></gsimedia:albumTable>
	<br><br>
	<h4>最受歡迎歌曲</h4> 	
	<gsimedia:songTable listName="songList"></gsimedia:songTable> 	
	<br>
</body>
</html>