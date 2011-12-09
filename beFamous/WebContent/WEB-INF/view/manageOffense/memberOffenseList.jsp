<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
會員檢舉清單
<p>
<form name="fm" method="post">
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">序號</font></td>
	<td valign="top" Width="140"><font size="2">曲名</font></td>
	<td valign="top" Width="140"><font size="2">專輯</font></td>
	<td valign="top" Width="110"><font size="2">檢舉日期</font></td>
	<td valign="top" Width="140"><font size="2">原因</font></td>
	<td valign="top" Width="60"><font size="2">不當檢舉</font></td></tr>
	
	<c:forEach var="hm" items="${member}" varStatus="status">
<tr><td Height="35"><font size="2">${status.index+1}</font></td>
	<td><font size="2"><a href="javascript:void(0)">${hm.song.name}</a></font></td>
	<td><font size="2"><a href="javascript:void(0)" onclick="album('${hm.song.album.id}')">${hm.song.album.name}</a></font></td>
	<td><font size="2">${hm.createDate}</font></td>
	<td><font size="2">${hm.reason}</font></td>
	<c:if test="${hm.offenseStatus == 1}">
		<td><INPUT type="submit" name="offenseWrong" value="不當檢舉" onclick="aaa('${hm.id}','${hm.member.id}');"/></td><tr>
	</c:if>
	</c:forEach>
</table>
<input type="hidden" name="adminId" value="${adminId}">

</form>
</body>
<script>
function album(albumId){
	location.href="${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId;
}
function aaa(offenseId, userId){
	alert(offenseId);
	alert(userId);
	document.fm.action="${pageContext.request.contextPath}/memberOffenseWrong.do?offenseId="+offenseId+"&userId="+userId;
	document.fm.submit();
}
</script>
</html>