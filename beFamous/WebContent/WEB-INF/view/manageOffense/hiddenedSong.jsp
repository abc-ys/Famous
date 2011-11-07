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
查詢紀錄:&nbsp
<select name="year"> 
<option value=""></option> 
<option value="2010">2010</option> 
<option value="2011">2011</option> 
<option value="2012">2012</option> 
<option value="2013">2013</option> 
</select>&nbsp年&nbsp
<select name="month"> 
<option value=""></option> 
<option value="1">1</option> 
<option value="2">2</option> 
<option value="3">3</option> 
<option value="4">4</option> 
</select>&nbsp月&nbsp&nbsp<br>
查詢創作者:&nbsp<input type="text" name="queryCreator" size="5"/><p>
<center><input type="submit" value="查詢" onclick="query()"/></center>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="40" Height="35" valign="top"><font size="2">序號</font></td>
	<td valign="top" Width="70"><font size="2">被檢舉次數</font></td>
	<td valign="top" Width="100"><font size="2">曲名</font></td>
	<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="100"><font size="2">創作人</font></td>
	<td valign="top" Width="60"><font size="2">原因</font></td>
	<td valign="top" Width="100"><font size="2">隱藏</font></td>
	<td Width="80"></td>
	<td Width="80"></td></tr>
	
	<c:forEach var="hm" items="${offenses}" varStatus="status">
<tr><td Height="35"><font size="2">${status.index+1}</font></td>
	<td><font size="2">${hm[1]}</font></td>
	<td><font size="2"><a href="javascript:void(0)">${hm[0].offense.song.name}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="album('${hm[0].offense.song.album.albumID}')">${hm[0].offense.song.album.name}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="creator('${hm[0].offense.song.album.creator.memberId}')">${hm[0].offense.song.album.creator.userName}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="offenseReason('${hm[0].offense.song.songID}')">檢舉原因</a></font></td>
	<td><font size="2">${hm[0].modifyDate}&nbsp已隱藏&nbsp by${hm[0].modifyUser}</font></td>
	<td><input type="submit" value="取消隱藏" onclick="noHiddenSong('${hm[0].offense.song.songID}')"/></td>
	<td><input type="submit" value="確定隱藏" onclick="yesHiddenSong('${hm[0].offense.song.songID}')"/></td></tr>
	</c:forEach>
</table>
</form>
</body>
<script>
function query(){
    document.fm.action="${pageContext.request.contextPath}/hiddenedSong.do";
    document.fm.submit();
}
function noHiddenSong(Id){
   document.fm.action="${pageContext.request.contextPath}/noHiddenedSong.do?ID="+Id;
   document.fm.submit();
}
function yesHiddenSong(Id){
   document.fm.action="${pageContext.request.contextPath}/yesHiddenedSong.do?ID="+Id;
   document.fm.submit();
}
function album(albumId){
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId);
}
function creator(memberId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/123456/"+memberId+".do");
}
function offenseReason(Id){
	window.open("${pageContext.request.contextPath}/offenseReason.do?id="+Id,"parent","height=300,width=600,location=no,scrollbars=yes,toolbar=no,directories=no,menubar=no,directories=no");
}
</script>
</html>