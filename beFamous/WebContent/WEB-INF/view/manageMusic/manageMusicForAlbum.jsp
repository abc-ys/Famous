<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
	<INPUT type=radio name="type" value="1">單曲
	<INPUT type=radio name="type" value="2">EP
	<INPUT type=radio name="type" value="3">專輯<br><br>
	公開日期區間:&nbsp<input name="MOPEND" type="text" class="fillbox" >&nbsp;
	<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="images/cal.gif" border="0"></img></a>&nbsp-&nbsp
	<input name="MCLOSED" type="text" class="fillbox" >&nbsp;
	<A HREF="javascript:show_calendar('fm.MCLOSED');"><img src="images/cal.gif" border="0"></img></a><br><br>
	創作人:&nbsp<input type="text" name="name" size="10"><br><br>
	音樂類別:&nbsp<select name="musicType">
			<option value="" ></option>
			<c:forEach var="hm" items="${musicCategory}">
			<option value="${hm.id}" >${hm.name}</option>
			</c:forEach>
		</select><br><br>
	<center><input type="button" value="查詢" onclick="queryAlbum('${adminID}')">&nbsp&nbsp<input type="reset" value="全部清除"></center>
<p>
<c:if test="${Album != null}">
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
	<td Height="35"><font size="2">
	<c:if test="${hm.type == 1}">單曲</c:if>
	<c:if test="${hm.type == 2}">EP</c:if>
	<c:if test="${hm.type == 3}">專輯</c:if></font></td>
	<td><font size="2">${hm.pid}</font></td>
	<td><font size="2"><a href="javascript:album('${hm.pid}','${adminID}')">${hm.name}</a></font></td>	
	<td><font size="2"><a href="javascript:song('${hm.name}')">4</a></font></td>	
	<td><font size="2"><a href="javascript:creator('${hm.creator.id}','${adminID}')">${hm.creator.userName}</a></font></td>
	<td><font size="2">${hm.musicCategory.name}</font></td>
	<td><font size="2">
	<fmt:parseDate var="dateObj" value="${hm.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></td>
	<td><font size="2">30</font></td>
	<td><font size="2">${hm.tag}</font></td>
	
	<c:if test="${hm.hidden.id != null}"><c:if test="${hm.hidden.endDate == null}"><td><font size="2">
	<fmt:parseDate var="dateObj" value="${hm.hidden.modifyDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />已隱藏 by${hm.hidden.modifier.adminName}</font>
	<br><font size="2"><a href="javascript:cancleHiddenAlbum('${hm.pid}','${adminID}')">取消隱藏</a></font></td>
	<td><font size="2">${hm.hidden.hiddenReason}</font></td><tr>
	</c:if></c:if>
	<br><c:if test="${hm.hidden.id == null||hm.hidden.endDate != null}">
	<td><font size="2"><a href="javascript:hiddenAlbum('${hm.pid}','${adminID}')">隱藏</a></font></td>
	<td><font size="2"></font></td></c:if>
	<tr>
	
	</c:forEach>
</table>
</c:if>
</form>
</body>
<script type="text/javascript">
function queryAlbum(adminID){   
	document.fm.action="${pageContext.request.contextPath}/queryAlbum.do?adminID="+adminID;
  	document.fm.submit();
}
function album(albumId,adminID){
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId+"&userId="+adminID);
}
function creator(memberId,adminId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do");
}
function song(albumName){
	alert(albumName);
	window.open("${pageContext.request.contextPath}/querySong.do?albumName="+albumName);
}
function hiddenAlbum(albumId,adminID){
	window.open("${pageContext.request.contextPath}/hiddenForAlbum.do?albumID="+albumId+"&mType=album&adminID="+adminID,'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function cancleHiddenAlbum(albumId,adminID){
	document.fm.action="${pageContext.request.contextPath}/cancleHiddenAlbum.do?albumID="+albumId+"&adminID="+adminID;
  	document.fm.submit();
}
</script>
</html>