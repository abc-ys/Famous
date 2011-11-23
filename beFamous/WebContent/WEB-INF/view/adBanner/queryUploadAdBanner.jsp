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
<h4>查看上傳廣告內容</h4>
<p>
<form name="fm" method="post">	
	活動時間:&nbsp<input name="activityStartDate" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.activityStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
	<input name="activityEndDate" type="text" class="fillbox" readonly >&nbsp;
	<A HREF="javascript:show_calendar('fm.activityEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>

	審核狀態:<select name="identity" id="identity">
			<option selected="selected"></option>
			<option value="1" >尚未審核</option>
			<option value="2" >審核成功</option>
			<option value="3" >審核失敗</option>
		</select><br><br>
	專輯數:<input type="text" name="albumNumber" id="albumNumber"><br><br>
	<input type="button" value="查詢" onclick="queryAd()">
</form>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">會員帳號</font></td>
	<td valign="top" Width="50"><font size="2">專輯數</font></td>
	<td valign="top" Width="150"><font size="2">廣告類別</font></td>
	<td valign="top" Width="150"><font size="2">活動標題</font></td>
	<td valign="top" Width="150"><font size="2">活動期間</font></td>
	<td valign="top" Width="80"><font size="2">審核狀態</font></td></tr>
	<c:forEach var="hm" items="${adList}">
	<td Height="35"><font size="2"><a href="javascript:identity('${adminId}','${hm.memberCreator.id}','${hm.memberCreator.identityName}')">${hm.memberCreator.email}</a></font></td>
	<td><font size="2">30</font></td>
	<td><font size="2">
	<c:if test="${hm.adType.id==1}">主banner</c:if>
	<c:if test="${hm.adType.id==2}">創作人主打banner</c:if>
	<c:if test="${hm.adType.id==3}">超樂活動banner</c:if></font></td>	
	<td><a href="javascript:queryAdDetail('${hm.id}','${adminId}')"><font size="2">${hm.activityName}</font></a></td>	
	<td><font size="2">
	<fmt:parseDate var="dateObj" value="${hm.activityStartDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />&nbsp~&nbsp  
	<fmt:parseDate var="dateObj" value="${hm.activityEndDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></td>
	<td><font size="2">
	<c:if test="${hm.checkStatus==1}">尚未審核</c:if>
	<c:if test="${hm.checkStatus==2}">審核成功</c:if>
	<c:if test="${hm.checkStatus==3}">審核失敗</c:if></font></td><tr>
	</c:forEach> 
</table>
</body>
<script type="text/javascript">
function queryAd(){
	document.fm.action="${pageContext.request.contextPath}/queryUploadAdBanner.do";
  	document.fm.submit();
}
function identity(adminId,memberId,identityName){	
	if(identityName == '一般會員'){
		location.href="${pageContext.request.contextPath}/manageGMemberDetail/get/"+adminId+"/"+memberId+".do";
	}else{
		location.href="${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do";
	}
}
function queryAdDetail(adId,adminId){
	location.href="${pageContext.request.contextPath}/queryUploadAdDetail/"+adminId+"/"+adId+".do";
}
</script>

</html>