<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<option value="表演" <c:if test="${newsCategory=='表演'}">selected = "true"</c:if> >表演</option>
	<option value="公告" <c:if test="${newsCategory=='公告'}">selected = "true"</c:if> >公告</option>
	<option value="新聞" <c:if test="${newsCategory=='新聞'}">selected = "true"</c:if> >新聞</option>
	<option value="好康" <c:if test="${newsCategory=='好康'}">selected = "true"</c:if> >好康</option>
	<option value="其他" <c:if test="${newsCategory=='其他'}">selected = "true"</c:if> >其他</option>
	</select><p>
消息標題:&nbsp;<input type="text" name="newsName" value=${newsName}>&nbsp;<p>
上架時間:&nbsp;
<input name="MOPEND" type="text" class="fillbox" value=${MOPEND} >&nbsp;
<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
<input name="MCLOSED" type="text" class="fillbox" value=${MCLOSED} >&nbsp;
<A HREF="javascript:show_calendar('fm.MCLOSED');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><p>
狀態:&nbsp;
<select name="onStatus">
<option value="" <c:if test="${onStatus==''}">selected = "true"</c:if> ></option> 
<option value="1" <c:if test="${onStatus=='1'}">selected = "true"</c:if> >刊登中</option> 
<option value="2" <c:if test="${onStatus=='2'}">selected = "true"</c:if> >未上架</option>
</select><p>
消息來源:&nbsp;<input type="text" name="newsSource" value=${newsSource} >&nbsp;<p>
<input type="hidden" name="adminId" value=${adminId}>
<center><input type="submit" value="查詢" onclick="query()"/></center>
</form>
<p>
查詢結果
<form name="form2" id='form2' method="post">
<input type="submit" value="刪除" onclick="deleteData(${adminId})"/>
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
	
<c:forEach var="n" items="${newsList}" varStatus="status">
	<td Height="35"><font size="2">${status.index+1}</font></td>
	<td><INPUT type=checkbox name="del" id="del" value="${n.id}"></td>
	<td><font size="2">${n.newsCategory}</font></td>
	<c:if test="${n.onStatus == 1}">
	<td><font size="2"><a href="javascript:void(0)" onclick="showcontent('${n.id}','${adminId}')">${n.newsName}</a></font></td></c:if>
	<c:if test="${n.onStatus == 2}">
	<td><font size="2"><a href="javascript:void(0)" onclick="editcontent('${n.id}','${adminId}')">${n.newsName}</a></font></td></c:if>	
	<td><font size="2">${n.newsSouce}</font></td>	
	<fmt:parseDate var="dateObj" value="${n.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></td>
	<fmt:parseDate var="onDate" value="${n.onDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<td><font size="2"><fmt:formatDate value='${onDate}' pattern='yyyy-MM-dd' /></font></td>
	<c:if test="${n.onStatus == 1}"><td><font size="2">刊登中</font></td></c:if>
	<c:if test="${n.onStatus == 2}"><td><font size="2">未上架</font></td></c:if>
	<td><font size="2">${n.hit}</font></td><tr>
</c:forEach>
<input type="hidden" name="delList">	
</table>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/queryManagerNewsData.do";
     document.fm.submit();
}
function editcontent(newsID,adminId){
    document.form2.action="${pageContext.request.contextPath}/editNewsData/edit.do?newsID="+newsID+"&adminId="+adminId;
    document.form2.submit();
}
function showcontent(newsID,adminId){
    document.form2.action="${pageContext.request.contextPath}/editNewsData/show.do?newsID="+newsID+"&adminId="+adminId;
    document.form2.submit();
}
function deleteData(adminId){
	var delList = new Array();
	$('input:checkbox:checked[name="del"]').each(function(i) { delList[i] = this.value; });
	document.form2.delList.value = delList;
	document.form2.action="${pageContext.request.contextPath}/deleteData.do?adminId="+adminId;
	document.form2.submit(); 
}
</script>
</html>