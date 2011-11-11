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
<td>單曲名稱</td>
<td>專輯名稱</td>
<td>創作者</td>
<td>總次數</td>
</tr>


<c:forEach var="arSong" varStatus="status" items="${arSong}" >
<tr>
<td>${status.count}</td>
<td>${arSong.name}</td>
<td><a href="${pageContext.request.contextPath}/queryAlbumData.do?albumid=${arSong.album.albumID}">${arSong.album.name }</a></td>
<td>${arSong.album.creator.userName }</td>
<td>${purchaseCount}</td>
</tr>

</c:forEach>
</table>
活動單取被購買總次數:${totalCount }
</body>
</html>