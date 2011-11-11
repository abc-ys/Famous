<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" id='fm' method="post">
<br>
查詢最新消息
<p>
消息類別:&nbsp;
<select name="newsCategory">
	<option value=""></option>
	<option value="表演">表演</option>
	<option value="公告">公告</option>
	<option value="新聞">新聞</option>
	<option value="好康">好康</option>
	<option value="其他">其他</option>
	</select><p>
消息標題:&nbsp;<input type="text" name="newsName">&nbsp<p>
上架時間:&nbsp;
<input name="MOPEND" type="text" class="fillbox" readonly>&nbsp;
<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
<input name="MCLOSED" type="text" class="fillbox" readonly >&nbsp;
<A HREF="javascript:show_calendar('fm.MCLOSED');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
狀態:&nbsp;
<select name="status"> 
<option value=""></option> 
<option value="刊登中">刊登中</option> 
<option value="未上架">未上架</option> 
<option value="已上架">已上架</option> 
</select><p>
消息來源:&nbsp;<input type="text" name="newsSource">&nbsp<p>
<center><input type="submit" value="查詢" onclick="query()"/></center>
<p>
查詢結果<input type="submit" value="刪除" onclick="deleteData()"/>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="50" Height="35" valign="top"><font size="2">序號</font></td>
	<td valign="top" Width="50"><font size="2"></font></td>
	<td valign="top" Width="100"><font size="2">消息類別</font></td>
	<td valign="top" Width="100"><font size="2">新聞標題</font></td>
	<td valign="top" Width="100"><font size="2">提供者</font></td>
	<td valign="top" Width="100"><font size="2">建立日期</font></td>
	<td valign="top" Width="100"><font size="2">上架日期</font></td>
	<td valign="top" Width="60"><font size="2">狀態</font></td>
	<td valign="top" Width="50"><font size="2">曝光數</font></td></tr>
	
	<c:forEach var="n" items="${news}" varStatus="status">
	<td Height="35"><font size="2">${status.index+1}</font></td>
	<td><INPUT type=checkbox name=interst value="${n[0].newsId}"></td>
	<td><font size="2">${n[0].newsCategory}</font></td>	
	<c:choose>
	<c:when test="${n[0].onStatus == '未上架'}">
	<td><font size="2"><a href="javascript:void(0)" onclick="editcontent('${n[0].newsId}')">${n[0].newsName}</a></font></td>	
	</c:when>
	<c:otherwise>
	<td><font size="2"><a href="javascript:void(0)" onclick="showcontent('${n[0].newsId}')">${n[0].newsName}</a></font></td>	
	</c:otherwise>
	</c:choose>
	<td><font size="2">${n[0].newsSouce}</font></td>	
	<td><font size="2">${n[0].createDate}</font></td>
	<td><font size="2">${n[0].onDate}</font></td>
	<td><font size="2">${n[0].onStatus}</font></td>
	<td><font size="2">${n[1]}</font></td><tr>
	</c:forEach>
</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/queryManagerNews.do";
     document.fm.submit();
}
function editcontent(newsID){
    document.fm.action="${pageContext.request.contextPath}/editNewsData/edit.do?newsID="+newsID;
    document.fm.submit();
}
function showcontent(newsID){
    document.fm.action="${pageContext.request.contextPath}/editNewsData/show.do?newsID="+newsID;
    document.fm.submit();
}
function deleteData(){
    document.fm.action="${pageContext.request.contextPath}/deleteData.do";
    document.fm.submit();
}
</script>
</html>