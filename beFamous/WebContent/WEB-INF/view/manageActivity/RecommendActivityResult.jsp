<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>
<h1>推薦專輯列表</h1>

活動期間:<input type="text" name="year" >年 - <input type="text" name="month" >月<br></br>

<input type="button" name="111" value="查詢" onclick="onSubmit();">

<form action="" method="post" name="qForm">
<input type="hidden" name="activityID" >
</form>

<table border="1">
<tr>
<td>推薦主題</td>
<td>活動期間</td>
<td>專輯數</td>
<td>狀態</td>
<td>推薦內容</td>
<td>報表</td>
</tr>
<c:forEach var="arActivity" items="${arActivity}" >
<tr>
<td>${arActivity.title}</td>
<td>${arActivity.startDate} - ${arActivity.endDate}</td>
<td>${fn:length(arActivity.albumSet)}</td>
<td><c:if test="${arActivity.status==1}">開啟</c:if>
    <c:if test="${arActivity.status==2}">結束</c:if>
    <c:if test="${arActivity.status==3}">隱藏(可編輯)</c:if>
</td>
<td><input type="button" name="222" value="顯示內容" onclick="showContent('${arActivity.activityID}');"><br>
    <c:if test="${arActivity.status==3}"><input type="button" name="333" value="編輯內容" onclick="onEdit('${arActivity.activityID}');"></c:if>
</td>
<td><input type="button" name="444" value="參加會員" onclick="queryMember('${arActivity.activityID}');"><br>
    
    <input type="button" name="555" value="購買專輯總數" onclick="queryAlbum('${arActivity.activityID}');">
</td>
</tr>
</c:forEach>
</table>

</body>

<script type="text/javascript">


function onEdit(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/forwardUpdateRecommendActivity/"+activityID+".do";
	document.qForm.submit();
}


function showContent(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/queryRecommendActivity/"+activityID+".do";
	document.qForm.submit();
}



function queryMember(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}"+"/queryJoinMemberForRec.do";
	document.qForm.submit();
}

function queryAlbum(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}"+"/queryJoinAlbumForRec.do";
	document.qForm.submit();
}
</script>


</html>