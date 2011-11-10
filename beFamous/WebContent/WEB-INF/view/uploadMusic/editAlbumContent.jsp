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
<h3>${album.name}</h3>
<p>
<table>
<td Width="140" Height="35" align="center" rowspan="4"><img alt="" src="${album.cover}"></td>
<td>音樂類型:&nbsp${album.musicCategory.name}</td><tr>
<td>${album.date}</td><tr>
<c:if test="${album.status == '公開'}">
<td>狀態:&nbsp公開&nbsp<a href="javascript:changeState('隱藏')">隱藏</a></td><tr>
</c:if>
<c:if test="${album.status == '隱藏'}">
<td>狀態:&nbsp<a href="javascript:changeState('公開')">公開</a>&nbsp隱藏</td><tr>
</c:if>
<td><input type="button" value="編輯專輯資訊" onclick="editAlbumInfo('${album.albumID}')"/>&nbsp
<input type="button" value="新增歌曲" onclick="addSong()"/></td>
</table><p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="30" Height="35" valign="top"><font size="2">序號</font></td>
<td valign="top" Width="30"><font size="2"></font></td>
<td valign="top" Width="150"><font size="2">曲名</font></td>
<td valign="top" Width="60"><font size="2">試聽</font></td>
<td valign="top" Width="100"><font size="2">編輯歌曲資訊</font></td><tr>
	
<c:forEach var="hm" items="${album.songSet}"  varStatus="status">
<td Height="35"><font size="2">${status.index+1}</font></td>
<td><font size="2"><INPUT type=checkbox name=interst value="${hm.songID}"></font></td>
<td><font size="2">${hm.name}</font></td>
<td><font size="2"><a href="javascript:void(0)"><img alt="" src="${pageContext.request.contextPath}/images/title_01.gif"><br></a></font></td>	
<td><font size="2"><a href="javascript:editSongData('${hm.songID}')">編輯</a></font></td><tr>
</c:forEach>
<td Width="140" Height="35" colspan="5"><input type="submit" value="刪除" onclick="deleteData('${hm.songID}')"/></td>
</table>
提示:上下拖曳歌曲，即可調整曲序，調整完後請按儲存。<p>
<input type="submit" value="儲存" onclick="saveData('${album.albumID}')"/>
</form>
</body>
<script>
function saveData(albumID){
	document.fm.action="${pageContext.request.contextPath}/saveAlbumData.do?albumID="+albumID;
    document.fm.submit();
}
function deleteData(){
	if(confirm('你確定要刪除這些歌?')){
		document.fm.action="${pageContext.request.contextPath}/deleteSongData.do";
		document.fm.submit();
		}
}
function changeState(state){
	var url = "${pageContext.request.contextPath}/changeState.do?state="+state;    
	//以下兩行是將中文字做編碼
	url = encodeURI(url); 
	url = encodeURI(url);
	document.fm.action=url;
    document.fm.submit();
}
function addSong(){
}
function editSongData(songID){
	window.open("${pageContext.request.contextPath}/editSongData.do?songID="+songID);
}
function editAlbumInfo(albumID){
	document.fm.action="${pageContext.request.contextPath}/editAlbumData.do?albumID="+albumID;
	document.fm.submit();
}
</script>
</html>