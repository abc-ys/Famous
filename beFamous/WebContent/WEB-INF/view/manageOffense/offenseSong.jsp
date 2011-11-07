<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="80" Height="35" valign="top"><font size="2">序號</font></td>
	<td valign="top" Width="80"><font size="2">被檢舉次數</font></td>
	<td valign="top" Width="140"><font size="2">曲名</font></td>
	<td valign="top" Width="140"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="140"><font size="2">創作人</font></td>
	<td valign="top" Width="100"><font size="2">原因</font></td>
	<td valign="top" Width="100"><font size="2">隱藏</font></td></tr>
	
	<c:forEach var="hm" items="${offenses}" varStatus="status">
<tr><td Height="35"><font size="2">${status.index+1}</font></td>
	<td><font size="2">${hm[1]}</font></td>
	<td><font size="2"><a href="javascript:void(0)">${hm[0].song.name}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="album('${hm[0].song.album.albumID}')">${hm[0].song.album.name}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="creator('${hm[0].song.album.creator.memberId}')">${hm[0].song.album.creator.userName}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="offenseReason('${hm[0].song.songID}')">檢舉原因</a></font></td>
	<td><input type="submit" value="隱藏專輯" onclick="query()"/></td></tr>
	</c:forEach>
</table>
</form>
</body>
<script>
function album(albumId){
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId);
}
function creator(memberId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/123456/"+memberId+".do");
}
function offenseReason(Id){
	window.open("${pageContext.request.contextPath}/offenseReason.do?id="+Id,"parent","height=300,width=600,location=no,scrollbars=yes,toolbar=no,directories=no,menubar=no,directories=no");
}
function query(){
     document.fm.action="${pageContext.request.contextPath}/hiddenSong.do";
     document.fm.submit();
}
</script>
</html>