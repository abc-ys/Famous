<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
</head>
<body>
<h1>活動列表</h1>
<form action="" method="post" name="qForm">
活動期間:
<select name="year"> 
	<option ></option> 
	<option value="2010" <c:if test="${year=='2010'}">selected</c:if>>2010</option> 
	<option value="2011" <c:if test="${year=='2011'}">selected</c:if>>2011</option> 
	<option value="2012" <c:if test="${year=='2012'}">selected</c:if>>2012</option> 
	<option value="2013" <c:if test="${year=='2013'}">selected</c:if>>2013</option> 
</select>&nbsp;年&nbsp;
<select name="Month"> 
	<option ></option> 
	<option value="1" <c:if test="${month=='1'}">selected</c:if>>1</option> 
	<option value="2" <c:if test="${month=='2'}">selected</c:if>>2</option> 
	<option value="3" <c:if test="${month=='3'}">selected</c:if>>3</option> 
	<option value="4" <c:if test="${month=='4'}">selected</c:if>>4</option>
	<option value="5" <c:if test="${month=='5'}">selected</c:if>>5</option> 
	<option value="6" <c:if test="${month=='6'}">selected</c:if>>6</option> 
	<option value="7" <c:if test="${month=='7'}">selected</c:if>>7</option> 
	<option value="8" <c:if test="${month=='8'}">selected</c:if>>8</option> 
	<option value="9" <c:if test="${month=='9'}">selected</c:if>>9</option> 
	<option value="10" <c:if test="${month=='10'}">selected</c:if>>10</option> 
	<option value="11" <c:if test="${month=='11'}">selected</c:if>>11</option> 
	<option value="12" <c:if test="${month=='12'}">selected</c:if>>12</option> 
</select>月<br></br>

<input type="button" name="111" value="查詢" onclick="onSubmit();">


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
<fmt:parseDate var="dateObj" value="${arActivity.startDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<td><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font>&nbsp;-&nbsp;
<fmt:parseDate var="dateObj" value="${arActivity.endDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></td>

<td><c:if test="${arActivity.contentCondition==1}">儲值金額</c:if>
    <c:if test="${arActivity.contentCondition==2}">儲值次數</c:if>
    <c:if test="${arActivity.contentCondition==3}">購買項目</c:if>
    <c:if test="${arActivity.contentCondition==4}">購買項目</c:if>
</td>
<td>
    <c:if test="${arActivity.rewardCondition==1}">GSiBonus ${arActivity.reward}點 </c:if>
    <c:if test="${arActivity.rewardCondition==2}">GSiBonus 訂單金額  X ${arActivity.reward}點 </c:if>
</td>
<td><c:if test="${arActivity.status==1}">開啟</c:if>
    <c:if test="${arActivity.status==2}">結束</c:if>
    <c:if test="${arActivity.status==3}">隱藏(可編輯)</c:if>
</td>

<td><input type="button" name="444" value="參加會員" onclick="queryMember('${arActivity.id}');"><br>
     <input type="button" name="555" value="儲值總金額" onclick="queryGSiMoney('${arActivity.id}');"><br>
     <input type="button" name="666" value="儲值總次數" onclick="queryTimes('${arActivity.id}');"><br>
     <input type="button" name="777" value="購買專輯總數" onclick="queryAlbum('${arActivity.id}');"><br>
     <input type="button" name="888" value="購買單曲總數" onclick="querySong('${arActivity.id}');"><br>
</td>
<td>
   <input type="button" name="333" value="編輯內容" onclick="onEdit('${arActivity.id}','${adminID}');">
</td>
</tr>
</c:forEach>
</table>

</body>

<script type="text/javascript">

function onSubmit(){
	
	document.qForm.action="${pageContext.request.contextPath}/queryPromotionActivity.do";
	document.qForm.submit();
}

function onEdit(activityID,adminID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/forwardUpdatePromotionActivity/"+activityID+".do?adminID="+adminID;
	document.qForm.submit();
}

function queryMember(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/queryJoinMemberForPro.do";
	document.qForm.submit();
}

function queryGSiMoney(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/queryJoinGSiMoneyForPro.do";
	document.qForm.submit();
}

function queryTimes(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/queryJoinTimesForPro.do";
	document.qForm.submit();
}

function queryAlbum(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/queryJoinAlbumForPro.do";
	document.qForm.submit();
}
function querySong(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/queryJoinSongsForPro.do";
	document.qForm.submit();
}
</script>


</html>