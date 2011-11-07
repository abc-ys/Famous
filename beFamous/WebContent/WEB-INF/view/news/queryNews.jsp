<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
狀態:<select name="status" id="status">
<option value="開放" selected="selected">開放</option>
<option value="草稿">草稿</option>
</select><br><br>
<input type="button" value="查詢" onclick="queryNews()">
<input type="hidden" name="status">
</form>
<p>
<h4>查詢結果</h4>
<form name="form2" method="post">
<input type="button" value="新增訊息" onclick="addNews()"><input type="button" value="刪除訊息" onclick="delNews()">
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
	<tr><td Width="50" Height="35" valign="top"><font size="2">序號</font></td>
	<td valign="top" Width="50"><font size="2"></font></td>
	<td valign="top" Width="100"><font size="2">日期</font></td>
	<td valign="top" Width="100"><font size="2">訊息標題</font></td>
	<td valign="top" Width="100"><font size="2">消息來源</font></td>
	<td valign="top" Width="100"><font size="2">狀態</font></td>
	<td valign="top" Width="100"><font size="2">曝光數</font></td>
	<td valign="top" Width="100"><font size="2">編輯</font></td></tr>
	<c:forEach var="hm" items="${newsList}" varStatus="status">
		<tr><td valign="top" Width="50"><font size="2">${status.index+1}</font></td>
		<td valign="top" Width="50"><font size="2"><input type="checkbox" name="check" id="check" value=${hm[0].newsId}></font></td>	
		<td valign="top" Width="100"><font size="2">${hm[0].createDate}</font></td>		
		<td valign="top" Width="100"><font size="2">${hm[0].newsName}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm[0].newsSouce}</font></td>	
		<td valign="top" Width="100"><font size="2">${hm[0].onStatus}</font></td>
		<td valign="top" Width="100"><font size="2">${hm[1]}</font></td>
		<td valign="top" Width="100"><font size="2"><input type="button" value="編輯" onclick="modifyNews(${hm[0].newsId})"></font></td></tr>
	</c:forEach>
	<input type="hidden" name="delList">
	<input type="hidden" name="ststus" value=${status}>
	</form> 
</table>


</body>
<script type="text/javascript">
function queryNews(){
	var status =$('#status :selected').text();
	document.form.status.value = status;
	document.form.action="${pageContext.request.contextPath}/queryNews.do";
	document.form.submit();  
}

function addNews(){
	document.form.action="${pageContext.request.contextPath}/addNews.do";
	document.form.submit();  
}
function delNews(){	
	if($("#check").attr('checked')==undefined){
		alert("請選擇欲刪除訊息");
		return false;
	}else{
		var delList = new Array();
		$('input:checkbox:checked[name="check"]').each(function(i) { delList[i] = this.value; });
		document.form2.delList.value = delList;
		document.form2.action="${pageContext.request.contextPath}/delNews.do";
		document.form2.submit(); 
	}
	 
}
function modifyNews(newsID){
	window.open("${pageContext.request.contextPath}/modifyNews.do?newsID="+newsID,'son','location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');		
}
</script>
</html>