<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0"> 
	<form name="form" method="post">
		<tr>
		<td colspan=2 ><h4>會員詳細資料</h4></td><tr>		
		<td width="200" >帳號:&nbsp <c:out value="${creatorDetail[0].email}"></c:out></td>
		<td width="200" >GSiMoney:&nbsp <a href="${pageContext.request.contextPath}/prePayRecord.do"><c:out value="${creatorDetail[0].gsiMoney.balance}"></c:out></a></td><tr>
		<td width="200" >ID:&nbsp <c:out value="${creatorDetail[0].memberId}"></c:out></td>
		<td width="200" >GSiBonus:&nbsp <a href="${pageContext.request.contextPath}/rewardRecord.do"><c:out value="${creatorDetail[0].gsiBonus.balance}"></c:out></a></td><tr>
		<td width="200" >身分:&nbsp <c:out value="${creatorDetail[0].identityName}"></c:out></td>
		<td width="200" >粉絲數:&nbsp <c:out value="${creatorDetail[1]}"></c:out></td><tr>
		<td width="200" >加入日期:&nbsp <c:out value="${creatorDetail[0].createDate}"></c:out></td>
		<td width="200" >好友數:&nbsp <c:out value="${creatorDetail[2]}"></c:out></td><tr>
		<td width="200" >地區:&nbsp <c:out value="${creatorDetail[0].location}"></c:out></td>
		<td width="200" >專輯數:&nbsp <a href=""><c:out value="${creatorDetail[3]}"></c:out></a></td><tr>	
		<td width="200" >狀態:&nbsp
		<select name="status" id="status" onchange="addReason('${admin}','${creatorDetail[0].memberId}')"> 
			<c:if test="${creatorDetail[0].memberStatus.statusName == '正常'}">				
					<option value="正常" selected="selected">正常</option>
					<option value="停權" >停權</option>								
			</c:if>
			<c:if test="${creatorDetail[0].memberStatus.statusName == '停權'}">				
					<option value="停權" selected="selected">停權</option>
					<option value="正常" >正常</option>				
			</c:if>
		</select></td>
		<td width="200" >歌曲數:&nbsp <a href=""><c:out value="${creatorDetail[4]}"></c:out></a></td><tr>		
		<td width="200" ></td><td width="200" >被檢舉歌曲數:&nbsp <a href=""><c:out value="${creatorDetail[5]}"></c:out></a></td><tr>	
		<td width="200" ></td><td width="200" >被檢舉專輯數:&nbsp <a href=""><c:out value="${creatorDetail[6]}"></c:out></a></td><tr>	
		<td width="200" ></td><td width="200" >檢舉次數:&nbsp <a href=""><c:out value="${creatorDetail[7]}"></c:out></a></td><tr><tr>		
		
		<td ><h4>付款資訊</h4></td><tr>
		<td width="400">真實姓名/公司行號:&nbsp <input type="text" name="userName" id="userName" value='<c:out value="${creatorDetail[0].userName}"></c:out>'></td><tr>
		<td width="400">身分證字號/統一編號:&nbsp <input type="text" name="identityNO" id="identityNO" value='<c:out value="${creatorDetail[0].identityNO}"></c:out>'></td><tr>
		<td width="400">通訊地址:&nbsp <input type="text" name="address" id="address" value='<c:out value="${creatorDetail[0].address}"></c:out>'></td><tr>
		<td width="400">行動電話:&nbsp <input type="text" name="cellPhone" id="cellPhone" value='<c:out value="${creatorDetail[0].cellPhone}"></c:out>'></td><tr>
		<td width="400">其他電話:&nbsp <input type="text" name="tel" id="tel" value='<c:out value="${creatorDetail[0].tel}"></c:out>'></td><tr>
		<td><h4>銀行資訊</h4></td><tr>
		<td width="400">帳戶名稱:&nbsp <input type="text" name="accountName"  id="accountName" value='<c:out value="${creatorDetail[0].accountName}"></c:out>'></td><tr>
		<td width="200" >銀行名稱:&nbsp
		<select name="bankName" id="bankName"> 
			<c:if test="${creatorDetail[0].bankName == '中央銀行'}">				
					<option value="中央銀行" selected="selected">中央銀行</option>
					<option value="台灣銀行" >台灣銀行</option>								
			</c:if>
			<c:if test="${creatorDetail[0].bankName == '台灣銀行'}">				
					<option value="台灣銀行" selected="selected">台灣銀行</option>
					<option value="中央銀行" >中央銀行</option>				
			</c:if>
		</select></td><tr>		
		<td width="400">分行名稱:&nbsp <input type="text" name="bankBranch"  id="bankBranch" value='<c:out value="${creatorDetail[0].bankBranch}"></c:out>'></td><tr>
		<td width="400">銀行帳號:&nbsp <input type="text" name="accountNO"  id="accountNO" value='<c:out value="${creatorDetail[0].accountNO}"></c:out>'></td><tr>
		
		<input type="hidden" name="statusName" id="statusName" value= ${creatorDetail[0].memberStatus.statusName}><tr><tr>
		<input type="hidden" name="adminId" id="adminId" value=${admin}><tr><tr>		
		<input type="hidden" name="memberId" id="memberId" value=${creatorDetail[0].memberId}><tr><tr>		
		<td width="200" ><input type="button" value="確定" onclick="saveModification()">	
		<input type="button" value="取消" onclick="cancle('${admin}','${creatorDetail[0].memberId}')"></td><tr>
	</form>
</table>
</body>
<script type="text/javascript">

function addReason(adminId, memberId){
	document.form.statusName.value = document.getElementById("status").value;
	if(document.getElementById("status").value=="停權")
	{			
		window.open("${pageContext.request.contextPath}/addOffenseReason/"+adminId+"/"+memberId+".do",'son','height=300,width=400,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');
	}  
}

function saveModification(){
	document.form.action="${pageContext.request.contextPath}/saveCreator.do";
	document.form.submit();
}

function cancle(adminId, memberId){
	location.href="${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do";
}
</script>
</html>