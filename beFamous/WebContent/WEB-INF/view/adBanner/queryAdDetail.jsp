<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!-- 
<input type= "text" name="classcode" value="/${initParam.ImageWeb}/${adDetail[0].picture}"/> 
${pageContext.request.contextPath}-->

<h4>詳細廣告內容</h4>
	<table border="0" cellpadding="0" cellspacing="0"> 	
	<form>
		<td >banner類別:&nbsp ${adDetail[0].adType.adTypeName}</td><tr><tr>
		<td >活動名稱:&nbsp ${adDetail[0].activityName}</td><tr><tr>
		<td >檔案:&nbsp <img alt="" src="/${initParam.ImageWeb}/${adDetail[0].picture}"></td><tr><tr>
		<td >活動期間:&nbsp ${adDetail[0].activityStartDate} &nbsp~&nbsp ${adDetail[0].activityEndDate}</td><tr><tr>
		<td >連結網址:&nbsp <a href="http://${adDetail[0].website}">${adDetail[0].website}</a></td><tr><tr>
		<td >上架日期:&nbsp ${adDetail[0].onDate}</td><tr><tr>
		<td >下架日期:&nbsp ${adDetail[0].offDate}</td><tr><tr>
		<td >建立日期:&nbsp ${adDetail[0].createDate}</td><tr><tr>
		<td >刊登狀態:&nbsp ${adDetail[0].onStatus}</td><tr><tr>
		<td >點閱數:&nbsp ${adDetail[1]}</td><tr><tr>	
		<td ><input type="button" value="編輯" onclick="modifyAdDetail('${admin}','${adDetail[0].adId}')"></td><tr><tr>
	</form>
</table>	
</body>
<script type="text/javascript">
function modifyAdDetail(adminId, adId){
	location.href="${pageContext.request.contextPath}/queryAdDetail/modify/"+adminId+"/"+adId+".do";
}
</script>
</html>