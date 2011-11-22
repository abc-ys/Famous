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
<td>${album.createDate}</td><tr>
<c:if test="${album.status == '1'}">
<td>狀態:&nbsp公開&nbsp<a href="javascript:changeState('2','${album.id}','${creatorId}')">隱藏</a></td><tr>
</c:if>
<c:if test="${album.status == '2'}">
<td>狀態:&nbsp<a href="javascript:changeState('1','${album.id}','${creatorId}')">公開</a>&nbsp隱藏</td><tr>
</c:if>
<td><input type="button" value="編輯專輯資訊" onclick="editAlbumInfo('${album.id}','${creatorId}')"/>&nbsp
<input type="button" value="新增歌曲" onclick="addSong('${album.id}','${creatorId}')"/></td>
</table><p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="30" Height="35" valign="top"><font size="2">序號</font></td>
<td valign="top" Width="30"><font size="2"></font></td>
<td valign="top" Width="150"><font size="2">曲名</font></td>
<td valign="top" Width="60"><font size="2">試聽</font></td>
<td valign="top" Width="100"><font size="2">編輯歌曲資訊</font></td><tr>
	
<c:forEach var="hm" items="${album.songSet}"  varStatus="status">
<c:if test="${hm.dropDate == ''}">
<td Height="35"><font size="2">${status.index+1}</font></td>
<td><font size="2"><INPUT type=checkbox name=interst value="${hm.pid}"></font></td>
<td><font size="2">${hm.name}</font></td>
<td><font size="2"><a href="javascript:void(0)"><img alt="" src="${pageContext.request.contextPath}/images/title_01.gif"><br></a></font></td>	
<td><font size="2"><a href="javascript:editSongData('${hm.id}','${creatorId}')">編輯</a></font></td><tr>
</c:if>
</c:forEach>
<td Width="140" Height="35" colspan="5"><input type="button" value="刪除" onclick="deleteData('${creatorId}','${album.id}')"/></td>
</table>
提示:上下拖曳歌曲，即可調整曲序，調整完後請按儲存。<p>
<input type="button" value="儲存" onclick="saveData('${album.id}','${creatorId}')"/>
</form>
</body>
<script>
function saveData(albumID,creatorId){
	document.fm.action="${pageContext.request.contextPath}/saveAlbumData.do?albumID="+albumID+"&creatorId="+creatorId;
    document.fm.submit();
}
function deleteData(creatorId,albumID){
	if(confirm('你確定要刪除這些歌?')){
		document.fm.action="${pageContext.request.contextPath}/deleteSongData.do?creatorId="+creatorId+"&albumID="+albumID;
		document.fm.submit();
		}
}
function changeState(state,albumID,creatorId){
	//var url =     
	//以下兩行是將中文字做編碼
	//url = encodeURI(url); 
	//url = encodeURI(url);
	document.fm.action="${pageContext.request.contextPath}/changeState.do?state="+state+"&albumID="+albumID+"&creatorId="+creatorId;
    document.fm.submit();
}
function addSong(albumID,creatorId){
	document.fm.action="${pageContext.request.contextPath}/addSong.do?albumId="+albumID+"&creatorId="+creatorId;
	document.fm.submit();
}
function editSongData(songID,creatorId){
	window.open("${pageContext.request.contextPath}/editSongData.do?songID="+songID+"&creatorId="+creatorId);
}
function editAlbumInfo(albumID,creatorId){
	document.fm.action="${pageContext.request.contextPath}/editAlbumData.do?albumID="+albumID+"&creatorId="+creatorId;
	document.fm.submit();
}
</script>
</html>