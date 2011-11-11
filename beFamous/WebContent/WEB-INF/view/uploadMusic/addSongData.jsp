<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
	<p class="topic">曲目已上傳成功，請填寫歌曲資訊</p>
		<form name="form" method="post">
		<table border=0>
			<input type="hidden" id="albumId" name="albumId" value=${albumId}><br><br>
			<tr>
				<td width=400>歌曲名稱:&nbsp;<input type= "text" name="name" id="name"></td>
				<td width=400>發行日期:&nbsp;<input type= "text" name="date" value=<fmt:formatDate value="<%=new java.util.Date() %>" pattern ="yyyy-MM-dd"/>></td>
			</tr>
			<tr>
				<td width=400>音樂類型&nbsp;
					<select name="musicCategory" id="musicCategory">
						<option value="Rock & roll 搖滾樂" selected>Rock & roll 搖滾樂</option>
						<option value="Electronic 電子音樂" >Electronic 電子音樂</option>
						<option value="Hip-Hop 嘻哈音樂" >Hip-Hop 嘻哈音樂</option>
						<option value="R&B 節奏藍調" >R&B 節奏藍調</option>
						<option value="Jazz 爵士樂" >Jazz 爵士樂</option>
						<option value="World music世界音樂" >World music世界音樂</option>
						<option value="Folk 民歌" >Folk 民歌</option>
						<option value="Classical 古典音樂" >Classical 古典音樂</option>
						<option value="New Age music 新世紀音樂" >New Age music 新世紀音樂</option>
						<option value="Kids songs兒歌" >Kids songs兒歌</option>
					</select>
				</td>
				<td width=400>試聽狀態:&nbsp; 
				<input type="radio" name="status" id="status" value="全曲">全曲
				<input type="radio" name="status" id="status" value="90秒">90秒
				<input type="radio" name="status" id="status" value="30秒" checked>30秒
				</td>
			</tr>
			<tr>
				<td width=400>價格:&nbsp; 
				<input type= "radio" name="money" id="money" value= "定價" checked>定價&nbsp;NT&nbsp;<input type= "text" name="gsimoney" value=20 size=5>元
				<input type= "radio" name="money" id="money" value= "僅供試聽，不准下載">僅供試聽，不准下載
				</td>
			</tr>
			<tr>
				<td width=400>紅包打賞</td>
			</tr>
			<tr>
				<td width=400><input type= "radio" name= "money" id="money" value= "小紅包">小紅包5元</td>
			</tr>
			<tr>
				<td width=400><input type= "radio" name= "money" id="money" value= "大紅包">大紅包15元</td>
			</tr>
			<tr>
				<td width=400><input type= "radio" name= "money" id="money" value= "不提供打賞">不提供打賞</td>
			</tr>
			<tr>
				<td width=400>標籤:&nbsp;<input type= "text" name="tag" id="tag"></td>
			</tr>
			<tr>
				<td width=400><input type='button' value='完成' onclick="saveSongData()"></td>
			</tr>
			<p>
			<tr>
				<td width=400><input type='button' value='結束上傳' onclick="stopUpload(${albumId})"></td>
			</tr>
		</table></form>
</body>
<script type="text/javascript">
function saveSongData(){
	if($("#name").val() == ""){
		alert('請輸入歌曲名稱');
	}else if($("#tag").val() == ""){
		alert('請填寫和此專輯相關的標籤，增加被搜尋到機會。');
	}else{
		document.form.action="${pageContext.request.contextPath}/saveSongData.do";	
		document.form.submit();	
	}
}
function stopUpload(albumId){
	document.form.action="${pageContext.request.contextPath}/editAlbumData.do?albumID="+albumId;	
	document.form.submit();	
}
</script>
</html>