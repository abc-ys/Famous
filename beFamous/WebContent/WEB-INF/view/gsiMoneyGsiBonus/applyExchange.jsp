<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
	申請兌換
	<p>
	申請者:&nbsp;${creator.userName}<p>
	<p><fmt:parseDate var="lastMonth" value="${lastDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
	截至到&nbsp;<fmt:formatDate value='${lastMonth}' pattern='yyyy-MM-dd' />
	止您可兌換的GSiMoney金額:&nbsp;${lastOrder.balance}元<p>
	本次兌換GSiMoney金額:&nbsp;<input type="text" name="money" id="money">&nbsp;元<br>
	(匯款時將會扣除手續費30元)
	<p>
	<p>
	付款資訊<p>
	真實姓名/公司行號:&nbsp;<input type="text" name="realName" id="realName" size="50" value="${creator.realName}"><br>
	身分證字號/統一編號:&nbsp;<input type="text" name="identityNO" id="identityNO" size="50" value="${creator.identityNO}"><br>
	通訊地址:&nbsp;<input type="text" name="address" size="50" value="${creator.address}"><br>
	行動電話:&nbsp;<input type="text" name="cellPhone" size="15" value="${creator.cellPhone}"><br>
	其他電話:&nbsp;<input type="text" name="tel" size="15" value="${creator.tel}"><p>
	若您為第一次請款，請於7個工作天內補齊身份證明文件<br>
	(可採傳真或掃描圖檔寄至本公司email)<br>
	傳真號碼:02-2657-9797<br>
	email:ubn@mail.com<p>
	銀行資訊<p>
	帳戶名稱:&nbsp;<input type="text" name="accountName" id="accountName" size="50" value="${creator.accountName}"><br>
	銀行名稱:&nbsp;<input type="text" name="bankName" size="15" value="${creator.bankName}"><br>
	分行名稱:&nbsp;<input type="text" name="bankBranch" size="15" value="${creator.bankBranch}"><br>
	銀行帳號:&nbsp;<input type="text" name="accountNO" size="15" value="${creator.accountNO}"><br><p>
	<input type="checkbox" name="check" id="check">將此處修正的資訊同步更新到付款資訊或銀行資訊<br>
	<input type="hidden" name="id" value="${creator.id}">
	<input type="hidden" name="synUpdate" id="synUpdate">
	<input type="hidden" name="year" value="${year}">
	<input type="hidden" name="month" value="${month}">
	<center><input type="button" value="確定" onclick="apply()"/><input type="button" value="取消" onclick="cancle(${creator.id})"/></center>
</form>
</body>
<script>
function apply(){
	if($("#check").attr('checked')==undefined)
	{
		document.fm.synUpdate.value="n";
	}else{
		document.fm.synUpdate.value="y";
	}
	
	if($("#money").val()==""){
		alert("請輸入欲兌換之金額");
	    return false; 		
	}else if($("#realName").val()==""){
		alert("請輸入真實姓名");
	    return false; 			
	}else if($("#identityNO").val()==""){
		alert("請輸入身分證字號/統一編號");
	    return false; 		
		
	}else if($("#accountName").val()==""){
		alert("請輸入帳戶名稱");
	    return false; 		
		
	}else{
		document.fm.action="${pageContext.request.contextPath}/updateExchange.do";
   		document.fm.submit();
	}
    
}
function cancle(userId){
     document.fm.action="${pageContext.request.contextPath}/queryIncomeOutgo.do?userId="+userId;
     document.fm.submit();
}
</script>
</html>