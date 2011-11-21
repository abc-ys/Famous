<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
編輯專輯
<p>
編輯專輯資訊可修改專輯基本資訊及歌曲詳細資訊並可將歌曲重新排序。
<p><p>
<table>
<c:forEach var="hm" items="${album}">
<td>
<img alt="" src="${hm.cover}"><br>
<a href="javascript:editAlbumContent('${hm.id}','${creatorId}')">${hm.name}</a><br>
${hm.createDate}<br>
<c:if test="${hm.hidden.id != null}">
<font color="ff0000">被管理員隱藏<br>${hm.hidden.hiddenReason}</font>
</c:if>
</td>
</c:forEach>
</table>
</form>
</body>
<script>
function editAlbumContent(albumID,creatorId){
	document.fm.action="${pageContext.request.contextPath}/editAlbumContent.do?albumID="+albumID+"&creatorId="+creatorId;
    document.fm.submit();
}
</script>
</html>