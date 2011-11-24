<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h3>隱藏區</h3>
<form name="fm" method="post">
專輯:&nbsp;<INPUT type="radio" name="type" value="1" <c:if test="${queryType == '1'}">checked</c:if>>
歌曲:&nbsp;<INPUT type="radio" name="type" value="2" <c:if test="${queryType == '2'}">checked</c:if>>
<p>
查詢記錄:&nbsp;	
<select name="year"> 
	<option value=""></option> 
	<option value="2010">2010</option> 
	<option value="2011">2011</option> 
	<option value="2012">2012</option> 
	<option value="2013">2013</option> 
</select>&nbsp;年&nbsp;
<select name="month"> 
	<option value=""></option> 
	<option value="01">1</option> 
	<option value="02">2</option> 
	<option value="03">3</option> 
	<option value="04">4</option>
	<option value="05">5</option> 
	<option value="06">6</option> 
	<option value="07">7</option> 
	<option value="08">8</option> 
	<option value="09">9</option> 
	<option value="10">10</option> 
	<option value="11">11</option> 
	<option value="12">12</option> 
</select>&nbsp;月&nbsp;&nbsp;<br><p>
查詢創作者:&nbsp;<input type="text" name="queryCreator" size="5"/><p>
<center><input type="submit" value="查詢" onclick="query()"/></center>
<p>
查詢結果
<c:if test="${queryType ==1 and !empty offenses}"><table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<tr><td Width="40" Height="35" valign="top"><font size="2">序號</font></td>
		<td valign="top" Width="70"><font size="2">被檢舉次數</font></td>
		<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
		<td valign="top" Width="100"><font size="2">創作人</font></td>
		<td valign="top" Width="60"><font size="2">原因</font></td>
		<td valign="top" Width="150"><font size="2">隱藏</font></td>
		<td valign="top" Width="80"></td></tr>
	
	<c:forEach var="hm" items="${offenses}" varStatus="status">
	<tr><td Height="35"><font size="2">${status.index+1}</font></td>
		<td><font size="2">${hm[1]}</font></td>
		<td><font size="2"><a href="javascript:void(0)" onclick="album('${hm[0].pid}')">${hm[0].name}</a></font></td>
		<td><font size="2"><a href="javascript:void(0)" onclick="creator('${hm[0].creator.id}')">${hm[0].creator.userName}</a></font></td>
		<td><font size="2"><a href="javascript:void(0)" onclick="offenseReason('${hm[0].pid}')">檢舉原因</a></font></td>
		<fmt:parseDate var="dateObj" value="${hm[0].hidden.startDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<td><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />&nbsp;已隱藏</font></td>
		<td><input type="submit" value="取消隱藏" onclick="noHidden('${hm[0].hidden.id}')"/></td></tr>
	</c:forEach>
</table></c:if>
<c:if test="${queryType ==2 and !empty offenses}"><table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<tr><td Width="40" Height="35" valign="top"><font size="2">序號</font></td>
		<td valign="top" Width="70"><font size="2">被檢舉次數</font></td>
		<td valign="top" Width="100"><font size="2">曲名</font></td>
		<td valign="top" Width="100"><font size="2">專輯名稱</font></td>
		<td valign="top" Width="100"><font size="2">創作人</font></td>
		<td valign="top" Width="60"><font size="2">原因</font></td>
		<td valign="top" Width="100"><font size="2">隱藏</font></td>
		<td Width="80"></td></tr>
	
	<c:forEach var="hm" items="${offenses}" varStatus="status">
	<tr><td Height="35"><font size="2">${status.index+1}</font></td>
		<td><center><font size="2">${hm[1]}</font></center></td>
		<td><center><font size="2"><a href="javascript:void(0)">${hm[0].name}</a></font></center></td>
		<td><center><font size="2"><a href="javascript:void(0)" onclick="album('${hm[0].album.pid}')">${hm[0].album.name}</a></font></center></td>
		<td><center><font size="2"><a href="javascript:void(0)" onclick="creator('${hm[0].album.creator.id}')">${hm[0].album.creator.userName}</a></font></center></td>
		<td><font size="2"><a href="javascript:void(0)" onclick="offenseReason('${hm[0].pid}')">檢舉原因</a></font></td>
		<fmt:parseDate var="dateObj" value="${hm[0].hidden.startDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<td><center><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />&nbsp;已隱藏</font></center></td>
		<td><input type="submit" value="取消隱藏" onclick="noHidden('${hm[0].hidden.id}')"/></td></tr>
		</c:forEach>
</table></c:if>
<input type="hidden" name="adminId" value="2">
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/queryHiddenedList.do";
     document.fm.submit();
}
function noHidden(hiddenId){
	document.fm.action="${pageContext.request.contextPath}/noHiddened.do?hiddenId="+hiddenId;
    document.fm.submit();
}
function album(albumId){
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId);
}
function creator(memberId){
	window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/123456/"+memberId+".do");
}
function offenseReason(productionCategoryId){
	window.open("${pageContext.request.contextPath}/offenseReason.do?productionCategoryId="+productionCategoryId,"parent","height=300,width=600,location=no,scrollbars=yes,toolbar=no,directories=no,menubar=no,directories=no");
}
</script>
</html>