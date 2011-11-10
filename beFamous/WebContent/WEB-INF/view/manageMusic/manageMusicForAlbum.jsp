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
<h4>查詢專輯</h4>
<p>
<form name="fm" method="post">
	專輯形式:&nbsp
	<INPUT type=radio name=type value=單曲>單曲
	<INPUT type=radio name=type value=EP>EP
	<INPUT type=radio name=type value=專輯>專輯<br><br>
	公開日期區間:&nbsp<input name="MOPEND" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="images/cal.gif" border="0"></img></a>&nbsp-&nbsp
	<input name="MCLOSED" type="text" class="fillbox" readonly >&nbsp;
	<A HREF="javascript:show_calendar('fm.MCLOSED');"><img src="images/cal.gif" border="0"></img></a><br><br>
	創作人:&nbsp<input type="text" name="name" size="10"><br><br>
	音樂類別:&nbsp<select name="musicType">
			<option value="" ></option>
			<c:forEach var="hm" items="${MusicCategory}">
			<option value="${hm.name}" >${hm.name}</option>
			</c:forEach>
		</select><br><br>
	<center><input type="button" value="查詢" onclick="queryAlbum()">&nbsp&nbsp<input type="reset" value="全部清除"></center>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">形式</font></td>
	<td valign="top" Width="60"><font size="2">專輯ID</font></td>
	<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
	<td valign="top" Width="30"><font size="2">歌曲數目</font></td>
	<td valign="top" Width="100"><font size="2">創作人</font></td>
	<td valign="top" Width="80"><font size="2">類別</font></td>
	<td valign="top" Width="50"><font size="2">建立時間</font></td>
	<td valign="top" Width="50"><font size="2">售價</font></td>
	<td valign="top" Width="50"><font size="2">標籤</font></td>
	<td valign="top" Width="50"><font size="2">隱藏</font></td>
	<td valign="top" Width="50"><font size="2">隱藏理由</font></td></tr>
	<c:forEach var="hm" items="${Album}">
	<td Height="35"><font size="2">${hm[0].type}</font></td>
	<td><font size="2">${hm[0].albumID}</font></td>
	<td><font size="2"><a href="javascript:album('${hm[0].albumID}')">${hm[0].name}</a></font></td>	
	<td><font size="2"><a href="javascript:song('${hm[0].name}')">${hm[1]}</a></font></td>	
	<td><font size="2"><a href="javascript:creator('${hm[0].creator.memberId}')">${hm[0].creator.userName}</a></font></td>
	<td><font size="2">${hm[0].musicCategory.name}</font></td>
	<td><font size="2">${hm[0].date}</font></td>
	<td><font size="2">${hm[2]}</font></td>
	<td><font size="2">${hm[0].tag}</font></td>
	<td><font size="2">${hm[0].hidden.modifyDate}已隱藏 by${hm[0].hidden.modifyUser}
	<br><a href="javascript:hiddenAlbum('${hm[0].albumID}')">隱藏</a>
	<br><a href="javascript:cancleHiddenAlbum('${hm[0].albumID}')">取消隱藏</a></font></td>	
	<td><font size="2">${hm[0].hidden.hiddenReason}</font></td><tr>
	</c:forEach> 
</table>
</form>
</body>
<script type="text/javascript">
function queryAlbum(){   
	document.fm.action="${pageContext.request.contextPath}/queryAlbum.do";
  	document.fm.submit();
}
function album(albumId){
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId);
}
function creator(memberId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/123456/"+memberId+".do");
}
function song(albumName){
	window.open("${pageContext.request.contextPath}/querySong.do?albumName="+albumName);
}
function hiddenAlbum(albumId){
	window.open("${pageContext.request.contextPath}/hiddenForAlbum.do?albumID="+albumId+"&mType=album",'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function cancleHiddenAlbum(albumId){
	document.fm.action="${pageContext.request.contextPath}/cancleHiddenAlbum.do?albumID="+albumId;
  	document.fm.submit();
}
</script>
</html>