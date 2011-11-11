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
<h1>活動列表</h1>

活動期間:<input type="text" name="year" >年 - <input type="text" name="month" >月<br></br>

<input type="button" name="111" value="查詢" onclick="onSubmit();">

<form action="" method="post" name="qForm">
<input type="hidden" name="activityID" >
</form>

<table border="1">
<tr>
<td>活動名稱</td>
<td>活動期間</td>
<td>篩選條件</td>
<td>贈送內容</td>
<td>狀態</td>
<td>報表</td>
<td>編輯</td>
</tr>
<c:forEach var="arActivity" items="${arActivity}" >
<tr>
<td>${arActivity.title}</td>
<td>${arActivity.startDate} - ${arActivity.endDate}</td>
<td><c:if test="${arActivity.contentCondition==1}">儲值金額</c:if>
    <c:if test="${arActivity.contentCondition==2}">儲值次數</c:if>
    <c:if test="${arActivity.contentCondition==3}">購買項目</c:if>
</td>
<td>
    <c:if test="${arActivity.rewardCondition==1}">GSiBonus ${arActivity.reward}點 </c:if>
    <c:if test="${arActivity.rewardCondition==2}">GSiBonus 訂單金額  X ${arActivity.reward}點 </c:if>
</td>
<td><c:if test="${arActivity.status==1}">開啟</c:if>
    <c:if test="${arActivity.status==2}">結束</c:if>
    <c:if test="${arActivity.status==3}">隱藏(可編輯)</c:if>
</td>

<td><input type="button" name="444" value="參加會員" onclick="queryMember('${arActivity.activityID}');"><br>
    
     <input type="button" name="555" value="儲值總金額" onclick="queryAlbum('${arActivity.activityID}');"><br>
     <input type="button" name="666" value="儲值總次數" onclick="queryAlbum('${arActivity.activityID}');"><br>
     <input type="button" name="777" value="購買專輯總數" onclick="queryAlbum('${arActivity.activityID}');"><br>
     <input type="button" name="888" value="購買單曲總數" onclick="queryAlbum('${arActivity.activityID}');"><br>
</td>
<td><input type="button" name="222" value="顯示內容" onclick="onSubmit();"><br>
    <c:if test="${arActivity.status==3}"><input type="button" name="333" value="編輯內容" onclick="onSubmit();"></c:if>
</td>
</tr>
</c:forEach>
</table>

</body>

<script type="text/javascript">
function queryMember(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}"+"/queryJoinMemberForPro.do";
	document.qForm.submit();
}

function queryAlbum(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}"+"/queryJoinAlbumForRec.do";
	document.qForm.submit();
}
</script>


</html>