<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<h4>詳細廣告內容</h4>
<form name="modifyAd" method="post">
	<table border="0" cellpadding="0" cellspacing="0"> 	
		<td>banner類別:		
			<select name="bannerType" id="bannerType">		
			<c:if test="${adDetail[0].adType.adTypeName == '主banner'}">				
					<option value="主banner" selected="selected">主banner</option>
					<option value="創作人主打banner" >創作人主打banner</option>	
					<option value="超樂活動banner" >超樂活動banner</option>								
			</c:if>
			<c:if test="${adDetail[0].adType.adTypeName == '創作人主打banner'}">				
					<option value="創作人主打banner" selected="selected">創作人主打banner</option>
					<option value="主banner" >主banner</option>				
					<option value="超樂活動banner" >超樂活動banner</option>	
			</c:if>
			<c:if test="${adDetail[0].adType.adTypeName == '超樂活動banner'}">				
					<option value="超樂活動banner" selected="selected">超樂活動banner</option>
					<option value="主banner" >主banner</option>		
					<option value="創作人主打banner" >創作人主打banner</option>			
			</c:if>
			</select>
		</td><tr><tr>
		<td>活動名稱:&nbsp <input type="text" name="activityName" value='<c:out value="${adDetail[0].activityName}"></c:out>'></td><tr><tr>
		<td>檔案:&nbsp <img alt="" src="/${initParam.ImageWeb}/${adDetail[0].picture}"></td><tr><tr>
		<td><input type="file" name="file" size="20"></td><tr><tr>
		<td>活動期間:&nbsp <input name="activityStartDate" type="text" value="${adDetail[0].activityStartDate}" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('modifyAd.activityStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
		  &nbsp~&nbsp <input name="activityEndDate" type="text" value="${adDetail[0].activityEndDate}" class="fillbox" readonly >&nbsp;<A HREF="javascript:show_calendar('modifyAd.activityEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
		</td><tr><tr>
		<td>連結網址:&nbsp <input type="text" name="website" value='<c:out value="${adDetail[0].website}"></c:out>'></td><tr><tr>
		<td>上架日期:&nbsp <input type="text" name="onDate"  value="${adDetail[0].onDate}" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('modifyAd.onDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td><tr><tr>
		<td>下架日期:&nbsp <input type="text" name="offDate"  value="${adDetail[0].offDate}" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('modifyAd.offDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></td><tr><tr>
		<td>建立日期:&nbsp ${adDetail[0].createDate}</td><tr><tr>
		<input type="hidden" name="adminId" id="adminId" value=${admin}><tr><tr>		
		<input type="hidden" name="adId" id="adId" value=${adDetail[0].adId}><tr><tr>	
		<td><input type="button" value="儲存" onclick="saveAdDetail('${admin}','${adDetail[0].adId}')"></td><tr><tr>
	</table>
</form>	
</body>
<script type="text/javascript">
function saveAdDetail(){
	document.modifyAd.action="${pageContext.request.contextPath}/saveAdDetail.do";
	document.modifyAd.submit();
}
</script>
</html>