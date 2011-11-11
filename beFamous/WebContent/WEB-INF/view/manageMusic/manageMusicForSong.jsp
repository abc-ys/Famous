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
<br>
<h4>查詢歌曲</h4>
<p>
<form name="fm" method="post">
	專輯名稱:&nbsp<input type="text" name="albumName" size="10"><br><br>
	創作人:&nbsp<input type="text" name="name" size="10"><br><br>
	音樂類別:&nbsp<select name="musicType">
			<option value="" ></option>
			<c:forEach var="hm" items="${MusicCategory}">
			<option value="${hm.name}" >${hm.name}</option>
			</c:forEach>
		</select><br><br>
	<center><input type="button" value="查詢" onclick="querySong()">&nbsp&nbsp<input type="reset" value="全部清除"></center>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">編號</font></td>
	<td valign="top" Width="40"><font size="2">歌曲ID</font></td>
	<td valign="top" Width="100"><font size="2">歌曲名稱</font></td>
	<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="100"><font size="2">創作人</font></td>
	<td valign="top" Width="80"><font size="2">歌詞</font></td>
	<td valign="top" Width="80"><font size="2">類別</font></td>
	<td valign="top" Width="50"><font size="2">建立時間</font></td>
	<td valign="top" Width="20"><font size="2">售價</font></td>
	<td valign="top" Width="50"><font size="2">紅包打賞</font></td>
	<td valign="top" Width="50"><font size="2">標籤</font></td>
	<td valign="top" Width="50"><font size="2">隱藏</font></td>
	<td valign="top" Width="50"><font size="2">隱藏理由</font></td></tr>
	<c:forEach var="hm" items="${song}"  varStatus="status">
	<td Height="35"><font size="2">${status.index+1}</font></td>
	<td><font size="2">${hm.songID}</font></td>
	<td><font size="2"><a href="javascript:void(0)')">${hm.name}</a></font></td>	
	<td><font size="2"><a href="javascript:album('${hm.album.albumID}')">${hm.album.name}</a></font></td>	
	<td><font size="2"><a href="javascript:creator('${hm.album.creator.memberId}')">${hm.album.creator.userName}</a></font></td>
	<td><font size="2"><a href="javascript:lyrics('${hm.songID}')">歌詞</a></font></td>
	<td><font size="2">${hm.musicCategory.name}</font></td>
	<td><font size="2">${hm.date}</font></td>
	<td><font size="2">${hm.songPrice.price}</font></td>
	<td><font size="2">${hm.songPrice.discountPrice}</font></td>
	<td><font size="2">${hm.tag}</font></td>
	<c:forEach var="hm2" items="${hm.offense}">
	<td><font size="2">${hm2.hidden.modifyDate}已隱藏 by${hm2.hidden.modifyUser}
	<br><a href="javascript:hiddenSong('${hm.songID}')">隱藏</a>
	<br><a href="javascript:cancleHiddenSong('${hm.songID}')">取消隱藏</a></font></td>	
	<td><font size="2">${hm2.hidden.hiddenReason}</font></td><tr>
	</c:forEach> 
	</c:forEach> 
</table>
</form>
</body>
<script type="text/javascript">
function querySong(){   
	document.fm.action="${pageContext.request.contextPath}/querySong.do";
  	document.fm.submit();
}
function album(albumId){
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId);
}
function creator(memberId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/123456/"+memberId+".do");
}
function hiddenSong(songId){
	window.open("${pageContext.request.contextPath}/hiddenForSong.do?songID="+songId+"&mType=song",'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function cancleHiddenSong(songId){
	document.fm.action="${pageContext.request.contextPath}/cancleHiddenSong.do?songID="+songId;
  	document.fm.submit();
}
function lyrics(songId){
	window.open("${pageContext.request.contextPath}/showLyrics.do?songID="+songId,'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');
}
</script>
</html>