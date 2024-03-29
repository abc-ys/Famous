<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<c:forEach var="hm" items="${album}" varStatus="status">
<c:if test="${status.count%3==1}"><tr></c:if>
<td>
<img alt="" src="/${initParam.ImageWeb}/${hm.cover}"  width="150" height="150"><br>
<a href="javascript:editAlbumContent('${hm.pid}','${creatorId}')">${hm.name}</a><br>
<fmt:parseDate var="dateObj" value="${hm.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /><br>
<c:if test="${hm.hidden.id != null}">
<font color="ff0000">被管理員隱藏<br>${hm.hidden.hiddenReason}</font>
</c:if>
</td>
<c:if test="${status.count%3==0}"></tr></c:if>
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