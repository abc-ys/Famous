<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h4>訊息刊登管理</h4>
這裡有您所刊登的訊息，以方便您管理。<br><br>
<form name="form" method="post">
狀態:&nbsp;
	<c:if test="${onStatus ==1}">
		<select name="onStatus" id="onStatus">
			<option value="1" selected="selected">開放</option>
			<option value="2">草稿</option>
		</select>
	</c:if>
	<c:if test="${onStatus ==2}">
		<select name="onStatus" id="onStatus">
			<option value="1">開放</option>
			<option value="2" selected="selected">草稿</option>
		</select>
	</c:if>
<br><br>
<input type="button" value="查詢" onclick="queryNews()">
<input type="hidden" name="userId" value=${userId}>
</form>
<p>
<h4>查詢結果</h4>
<form name="form2" method="post">	
	<input type="button" value="新增訊息" onclick="addNews()"><input type="button" value="刪除訊息" onclick="delNews()">
	<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
		<tr><td Width="50" Height="35" valign="top"><center><font size="2">序號</font></center></td>
		<td valign="top" Width="50"><center><font size="2"></font></center></td>
		<td valign="top" Width="100"><center><font size="2">日期</font></center></td>
		<td valign="top" Width="100"><center><font size="2">訊息標題</font></center></td>
		<td valign="top" Width="100"><center><font size="2">消息來源</font></center></td>
		<td valign="top" Width="100"><center><font size="2">狀態</font></center></td>
		<td valign="top" Width="100"><center><font size="2">曝光數</font></center></td>
		<td valign="top" Width="100"><center><font size="2">編輯</font></center></td></tr>
		<c:forEach var="hm" items="${newsList}" varStatus="status">
			<tr><td valign="top" Width="50"><center><font size="2">${status.index+1}</font></center></td>
			<td valign="top" Width="50"><center><font size="2"><input type="checkbox" name="del" id="del" value=${hm.id}></font></center></td>	
			<fmt:parseDate var="dateObj" value="${hm.createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
			<td valign="top" Width="100"><center><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></center></td>
			<td valign="top" Width="100"><center><font size="2"><a href="javascript:void(0)" onclick="showNews(${hm.id})">${hm.newsName}</a></font></center></td>	
			<td valign="top" Width="100"><center><font size="2">${hm.newsSouce}</font></center></td>
			<c:if test="${hm.onStatus == 1}"><td valign="top" Width="100"><center><font size="2">開放</font></center></td></c:if>
			<c:if test="${hm.onStatus == 2}"><td valign="top" Width="100"><center><font size="2">草稿</font></center></td></c:if>
			<td valign="top" Width="100"><center><font size="2">${hm.hit}</font></center></td>
			<td valign="top" Width="100"><center><font size="2"><center><input type="button" value="編輯" onclick="modifyNews('${userId}','${hm.id}')"></font></center></td></tr>
		</c:forEach>
		<input type="hidden" name="userId" value=${userId}>
		<input type="hidden" name="onStatus" value=${onStatus}>
		<input type="hidden" name="delList">	 
	</table>
</form>

</body>
<script type="text/javascript">
function queryNews(){
	document.form.action="${pageContext.request.contextPath}/queryNewsData.do";
	document.form.submit();  
}

function addNews(){
	document.form.action="${pageContext.request.contextPath}/addNews.do";
	document.form.submit();  
}
function delNews(){	
	var delList = new Array();
	$('input:checkbox:checked[name="del"]').each(function(i) { delList[i] = this.value; });
	document.form2.delList.value = delList;
	document.form2.action="${pageContext.request.contextPath}/delNews.do";
	document.form2.submit();	 
}
function modifyNews(userId, newsId){
	window.open("${pageContext.request.contextPath}/modifyNews.do?newsId="+newsId,'son','location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');		
}
function showNews(newsId){
	window.open("${pageContext.request.contextPath}/showNews.do?newsId="+newsId);		
}
</script>
</html>