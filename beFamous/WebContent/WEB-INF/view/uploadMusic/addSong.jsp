<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<p class="topic">歌曲上傳</p>
<form name="form" enctype="multipart/form-data" method="post">
	<input type="hidden" id="creatorId" name="creatorId" value="${creatorId}"><br><br>
	<input type="hidden" id="albumId" name="albumId" value="${albumId}"><br><br>
	<p>檔案:<input type="file" name="file" id="fileToUpload" size="20" /><br><br>
	<p><input type="checkbox" name="agree" id="agree" value="agree">我已詳閱內容提供合約並同意授權此音樂作品予GSiMedia販售<br><br>
	<p><input type="button" value="確定上傳" onclick="add()">
	<p><input type='button' value='結束上傳' onclick="stopUpload('${albumId}','${creatorId}')">
			
</form>
</body>
<script type="text/javascript">
function add(){
	if($("#agree").attr('checked')==undefined){
		alert('尚未勾選詳閱同意書');
	}		
		document.form.action="${pageContext.request.contextPath}/saveSong.do";
		document.form.submit();			
	
}
function stopUpload(albumId,creatorId){
	document.form.action="${pageContext.request.contextPath}/editAlbumContent.do?albumID="+albumId+"&creatorId="+creatorId;	
	document.form.submit();	
}
</script>
</html>