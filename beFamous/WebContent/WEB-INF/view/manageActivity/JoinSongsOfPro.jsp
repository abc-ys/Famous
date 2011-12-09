<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>

<table border="1">
<tr>
<td>編號</td>
<td>單曲名稱</td>
<td>專輯名稱</td>
<td>創作者</td>
<td>總次數</td>
</tr>


<c:forEach var="list" varStatus="status" items="${arSong}" >
<tr>
<td>${status.count}</td>
<td>${list[0].name}</td>
<td><a href="${pageContext.request.contextPath}/queryAlbumData.do?albumid=${list[0].album.id}">${list[0].album.name }</a></td>
<td>${list[0].album.creator.userName }</td>
<td>${list[1]}</td>
<c:set var="number" value="${list[1]}"/>
<tr>
</c:forEach>
</table>
<input type="hidden" value="<c:set var="total" value="${total+number}"/>"/>
活動單取被購買總次數:${total+number}
</body>
</html>