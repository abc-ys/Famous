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
<h4>查詢廣告內容</h4>
<p>
<form name="fm" method="post">	
	廣告類別:
	<select name="bannerType" id="bannerType">
		<option value="主banner" selected="selected">主banner</option>
		<option value="創作人主打banner" >創作人主打banner</option>
		<option value="超樂活動banner" >超樂活動banner</option>
	</select><br><br>
	活動標題:<input type="text" name="activityName" id="activityName"><br><br>
	上架日期:&nbsp<input name="onStartDate" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.onStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
	<input name="onEndDate" type="text" class="fillbox" readonly >&nbsp;
	<A HREF="javascript:show_calendar('fm.onEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	下架日期:&nbsp<input name="offStartDate" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.offStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp-&nbsp
	<input name="offEndDate" type="text" class="fillbox" readonly >&nbsp;
	<A HREF="javascript:show_calendar('fm.offEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>

	<input type="hidden" name="adminId" value=${admin.adminId}>
	<input type="button" value="查詢" onclick="queryAd()">
	<input type="reset" value="全部清除">
</form>
<p>
<div>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="25" ><font size="2">廣告類別</font></td>
	<td Width="100"><font size="2">活動標題</font></td>
	<td Width="100"><font size="2">活動期間</font></td>
	<td Width="100"><font size="2">上架日期</font></td>
	<td Width="100"><font size="2">下架日期</font></td>
	<td Width="100"><font size="2">狀態</font></td>
	<td Width="50"><font size="2">點閱數</font></td></tr>
	<c:forEach var="hm" items="${adList}">
	<td Width="140" Height="25" valign="top"><font size="2">${hm[0].adType.adTypeName}</font></td>
	<td valign="top" Width="100"><font size="2"><a href="javascript:queryAdDetail('${admin.adminId }','${hm[0].adId}')">${hm[0].activityName}</a></font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].activityStartDate}~${hm[0].activityEndDate}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm[0].onDate}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm[0].offDate}</font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].onStatus}</font></td>
	<td valign="top" Width="50"><font size="2">${hm[1]}</font></td><tr>
	</c:forEach>
</table>	
</div>
</body>
<script type="text/javascript">
function queryAd(){   
	document.fm.action="queryAdBanner.do";
  	document.fm.submit();
}
function queryAdDetail(adminId, adId){   
	location.href="${pageContext.request.contextPath}/queryAdDetail/get/"+adminId+"/"+adId+".do";
}
</script>
</html>