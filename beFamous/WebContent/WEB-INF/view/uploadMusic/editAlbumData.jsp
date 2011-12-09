<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<form name="form" enctype="multipart/form-data" method="post">
	<h4>編輯專輯資訊</h4><p>	
	*專輯型式:&nbsp;
	<input type="radio" name="albumType" id="albumType" value="1" <c:if test="${album.type == '1'}"> checked </c:if>/>單曲
	<input type="radio" name="albumType" id="albumType" value="2" <c:if test="${album.type == '2'}"> checked </c:if>/>EP
	<input type="radio" name="albumType" id="albumType" value="3" <c:if test="${album.type == '3'}"> checked </c:if>/>專輯<br><br>
	*專輯名稱:&nbsp;<input type="text" name="name" value="${album.name}"><br><br>
	*發行日期:&nbsp;<input type="text" name="date" value="<fmt:parseDate var="dateObj" value="${album.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('form.date');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	廠牌:&nbsp;<input type="text" name="brand" value="${album.brand}"><br><br>
	*專輯類型&nbsp;
	<select name="musicCategory" id="musicCategory">
	<c:forEach var="hm" items="${mType}">
	<option value="${hm.id}" <c:if test="${album.musicCategory.id == hm.id}"> selected </c:if>>${hm.name}</option>
	</c:forEach>
	</select><br><br>
	*標籤:&nbsp <input type="text" name="tag" value="${album.tag}"><br>
	<font size=2>請輸入與此唱片相關的標籤，並用半形逗點(,)隔開。例如:流行,愛情,自由,創作等</font><br><br>
	*專輯封面:&nbsp<input type="file" name="cover" size="20"><br>
	大小:300pixel*300pixel的JPGC檔<br><br>
	<img alt="" src="/${initParam.ImageWeb}/${album.cover}" width="90" height="70"><input type="radio" name="cover2" id="defaultCover" value="${album.cover}" checked/>
	<c:forEach var="hm" items="${cover}">
	<img alt="" src="/${initParam.ImageWeb}/${hm.cover}" width="90" height="70"><input type="radio" name="cover2" id="defaultCover" value="${hm.cover}"/>
	</c:forEach>
	<br><br>
	專輯介紹/創作理念:&nbsp<br><textarea rows="6" cols="40" name="introduction">${album.introduction}</textarea><br><br>
	*專輯狀態:&nbsp
	<input type="radio" name="status" id="status" value="1" <c:if test="${album.status == '1'}"> checked </c:if>/>公開
	<input type="radio" name="status" id="status" value="2" <c:if test="${album.status == '2'}"> checked </c:if>/>隱藏<br><br>
	公開發表後，為確保消費者權益，設定收費的作品內容及資訊將只能變更<b>專輯介紹、標籤、專輯狀態、試聽長度、作詞人、作曲人、製作人和曲序</b>。<br><br>
	<center><input type="button" value="完成" onclick="saveAlbumInfo('${albumID}','${creatorId}')"></center>
</form>
</body>
<script>
function saveAlbumInfo(albumID,creatorId){
	document.form.action="${pageContext.request.contextPath}/saveAlbumInfo.do?albumID="+albumID+"&creatorId="+creatorId;
	document.form.submit();	
}
</script>
</html>