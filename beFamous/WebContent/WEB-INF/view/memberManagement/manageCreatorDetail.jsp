<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form>
	<table border="0" cellpadding="0" cellspacing="0"> 
		<tr>
		<td colspan=2 ><h4>會員詳細資料</h4></td><tr>		
		<td width="200" >帳號:&nbsp ${creatorDetail[0].email}</td>
		<td width="200" >GSiMoney:&nbsp <a href="${pageContext.request.contextPath}/prePayRecord.do">${creatorDetail[0].gsiMoney.balance}</a></td><tr>
		<td width="200" >ID:&nbsp ${creatorDetail[0].memberId}</td>
		<td width="200" >GSiBonus:&nbsp <a href="${pageContext.request.contextPath}/rewardRecord.do">${creatorDetail[0].gsiBonus.balance}</a></td><tr>
		<td width="200" >身分:&nbsp ${creatorDetail[0].identityName}</td>
		<td width="200" >粉絲數:&nbsp ${creatorDetail[1]}</td><tr>
		<td width="200" >加入日期:&nbsp ${creatorDetail[0].createDate}</td>
		<td width="200" >好友數:&nbsp ${creatorDetail[2]}</td><tr>
		<td width="200" >地區:&nbsp ${creatorDetail[0].location}</td>
		<td width="200" >專輯數:&nbsp <a href="${pageContext.request.contextPath}/queryAlbum.do">${creatorDetail[3]}</a></td><tr>	
		<c:if test="${creatorDetail[0].memberStatus.statusName == '正常'}">				
				<td width="200" >狀態:&nbsp	${creatorDetail[0].memberStatus.statusName}</td>			
		</c:if>
		<c:if test="${creatorDetail[0].memberStatus.statusName == '停權'}">				
				<td width="200" >狀態:&nbsp	${creatorDetail[0].memberStatus.statusName}</td>
		</c:if>		
		<td width="200" >歌曲數:&nbsp <a href="${pageContext.request.contextPath}/querySong.do">${creatorDetail[4]}</a></td><tr>		
		<td width="200" ><font color="red">
			<c:if test="${creatorDetail[0].memberStatus.statusName == '停權'}">				
				${creatorDetail[0].memberStatus.statusReason}			
			</c:if></font></td><td width="200" >被檢舉歌曲數:&nbsp <a href="${pageContext.request.contextPath}/queryOffenseSong.do">${creatorDetail[5]}</a></td><tr>	
		<td width="200" ></td><td width="200" >被檢舉專輯數:&nbsp <a href="${pageContext.request.contextPath}/queryOffenseAlbum.do">${creatorDetail[6]}</a></td><tr>	
		<td width="200" ></td><td width="200" >檢舉次數:&nbsp <a href="${pageContext.request.contextPath}/memberOffenseList.do">${creatorDetail[7]}</a></td><tr>	
	</table>
	<table border="0" cellpadding="0" cellspacing="0"> 
		<td ><h4>付款資訊</h4></td><tr>
		<td >真實姓名/公司行號:&nbsp ${creatorDetail[0].userName}</td><tr>
		<td >身分證字號/統一編號:&nbsp ${creatorDetail[0].identityNO}</td><tr>
		<td >通訊地址:&nbsp ${creatorDetail[0].address}</td><tr>
		<td >行動電話:&nbsp ${creatorDetail[0].cellPhone}</td><tr>
		<td >其他電話:&nbsp ${creatorDetail[0].tel}</td><tr><tr>
		<td><h4>銀行資訊</h4></td><tr>
		<td >帳戶名稱:&nbsp ${creatorDetail[0].accountName}</td><tr>
		<td >銀行名稱:&nbsp ${creatorDetail[0].bankName}</td><tr>
		<td >分行名稱:&nbsp ${creatorDetail[0].bankBranch}</td><tr>
		<td >銀行帳號:&nbsp ${creatorDetail[0].accountNO}</td><tr>
	</table><br>
	<table border="0" cellpadding="0" cellspacing="0"> 
		<td width="200" ><input type="button" value="修改會員資料" onclick="modifyMemberDetail('${admin}','${creatorDetail[0].memberId}')"></td>
	</table>
</form>	
</body>
<script type="text/javascript">
function modifyMemberDetail(adminId, memberId){
	location.href="${pageContext.request.contextPath}/manageCreatorDetail/modify/"+adminId+"/"+memberId+".do";
}
</script>
</html>