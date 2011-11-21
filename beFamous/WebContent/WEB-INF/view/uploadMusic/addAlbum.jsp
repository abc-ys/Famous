<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<form name="form" enctype="multipart/form-data" method="post">
	<h4>新增專輯</h4>
	新增專輯後即可上傳你的音樂創作，販售歌曲<br><br>	
	*專輯型式:&nbsp
	<input type="radio" name="albumType" id="albumType" value="1"/>單曲
	<input type="radio" name="albumType" id="albumType" value="2" />EP
	<input type="radio" name="albumType" id="albumType" value="3"/>專輯<br><br>
	*專輯名稱:&nbsp<input type="text" name="name" ><br><br>
	*發行日期:&nbsp<input type="text" name="date" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('form.date');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	廠牌:&nbsp<input type="text" name="brand" ><br><br>
	*專輯類型&nbsp
	<select name="musicCategory" id="musicCategory">
		<option value="1" >Rock & roll 搖滾樂</option>
		<option value="2" >Electronic 電子音樂</option>
		<option value="3" >Hip-Hop 嘻哈音樂</option>
		<option value="4" >R&B 節奏藍調</option>
		<option value="5" >Jazz 爵士樂</option>
		<option value="6" >World music世界音樂</option>
		<option value="7" >Folk 民歌</option>
		<option value="8" >Classical 古典音樂</option>
		<option value="9" >New Age music 新世紀音樂</option>
		<option value="10" >Kids songs兒歌</option>
	</select><br><br>
	*標籤:&nbsp <input type="text" name="tag" ><br>
	<font size=2>請輸入與此唱片相關的標籤，並用半形逗點(,)隔開。例如:流行,愛情,自由,創作等</font><br><br>
	*專輯封面:&nbsp<input type="file" name="cover" size="20"><br>
	大小:300pixel*300pixel的JPGC檔<br><br>
	<img alt="" src="${pageContext.request.contextPath}/${defaultCover[0]}" width="90" height="70"><input type="radio" name="defaultCover" id="defaultCover" value="${defaultCover[0]}" checked/>
	<img alt="" src="${pageContext.request.contextPath}/${defaultCover[1]}" width="90" height="70"><input type="radio" name="defaultCover" id="defaultCover" value="${defaultCover[1]}"/>
	<img alt="" src="${pageContext.request.contextPath}/${defaultCover[2]}" width="90" height="70"><input type="radio" name="defaultCover" id="defaultCover" value="${defaultCover[2]}"/>
	<br><br>
	專輯介紹/創作理念:&nbsp<textarea rows="6" cols="40" name="introduction"></textarea><br><br>
	*專輯狀態:&nbsp
	<input type="radio" name="status" id="status" value="1"/>公開
	<input type="radio" name="status" id="status" value="2" checked/>隱藏<br><br>
	公開發表後，為確保消費者權益，設定收費的作品內容及資訊將只能變更<b>專輯介紹、標籤、專輯狀態、試聽長度、作詞人、作曲人、製作人和曲序</b>。<br><br>
	<input type="button" value="完成，上傳歌曲" onclick="saveAlbum('${creatorId}')">
</form>
</body>
<script type="text/javascript">
function saveAlbum(creatorId){
	document.form.action="${pageContext.request.contextPath}/saveAlbum.do?creatorId="+creatorId;
	document.form.submit();	
}
</script>
</html>