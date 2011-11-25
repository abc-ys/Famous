<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0"> 
	<form>
		<tr>
		<td colspan=2 ><h4>會員詳細資料</h4></td><tr>		
		<td width="200" >帳號:&nbsp ${memberDetail[0].email}</td>
		<td width="200" >GSiMoney:&nbsp <a href= "${pageContext.request.contextPath}/queryPrePayRecord.do">${memberDetail[6]}</a></td><tr>
		<td width="200" >ID:&nbsp ${memberDetail[0].id}</td>
		<td width="200" >GSiBonus:&nbsp <a href="${pageContext.request.contextPath}/queryRewardRecord.do">${memberDetail[7]}</a></td><tr>
		<td width="200" >身分:&nbsp 
		<c:if test="${memberDetail[0].identityName==1}">一般會員</c:if>
		<c:if test="${memberDetail[0].identityName==2}">個人創作者</c:if>
		<c:if test="${memberDetail[0].identityName==3}">樂團</c:if></td><tr>
		<td width="200" >加入日期:&nbsp 
		<fmt:parseDate var="dateObj" value="${memberDetail[0].createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
		<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></td>
		<td width="200" >好友數:&nbsp ${memberDetail[2]}</td><tr>
		<td width="200" >地區:&nbsp ${memberDetail[0].location}</td><tr>			
		<c:if test="${memberDetail[0].memberStatus.statusName == '正常'}">				
				<td width="200" >狀態:&nbsp	${memberDetail[0].memberStatus.statusName}</td>			
		</c:if>
		<c:if test="${memberDetail[0].memberStatus.statusName == '停權'}">				
				<td width="200" >狀態:&nbsp	${memberDetail[0].memberStatus.statusName}</td>
		</c:if>		<tr>		
		<td width="200" ><font color="red">
			<c:if test="${memberDetail[0].memberStatus.statusName == '停權'}">				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${memberDetail[0].memberStatus.statusReason}			
			</c:if></font></td>
		<td width="200" >檢舉次數:&nbsp <a href="${pageContext.request.contextPath}/memberOffenseList.do">${memberDetail[3]}</a></td><tr>	
		<td width="200" ><input type="button" value="修改會員資料" onclick="modifyMemberDetail('${admin}','${memberDetail[0].id}')"></td>
	</form>
</table>
</body>
<script type="text/javascript">

function modifyMemberDetail(adminId, memberId){
	location.href="${pageContext.request.contextPath}/manageGMemberDetail/modify/"+adminId+"/"+memberId+".do";
}

</script>
</html>