<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h4>帳戶資料編輯</h4>
<form name="form" method="post">
	<input type="hidden" name="userId" value="${member[0].id}">
	帳戶名稱:&nbsp;<input type="text" name="accountName" value="${member[0].accountName}"><br><br>
	銀行名稱:&nbsp;
		<select name="bankName" id="bankName"/>
			<option value="000" <c:if test="${member[0].bankName=='000'}">selected = "true"</c:if> >中央銀行</option> 
			<option value="004" <c:if test="${member[0].bankName=='004'}">selected = "true"</c:if> >台灣銀行</option>
		</select><br><br>
	分行名稱:&nbsp; <input type="text" name="bankBranch" value="${member[0].bankBranch}"><br><br>
	帳戶號碼:&nbsp; <input type="text" name="accountNO" value="${member[0].accountNO}"><br><br>
	身份證字號/統一編號:&nbsp;<input type="text" name="identityNO" value="${member[0].identityNO}"><br><br>
	通訊地址: &nbsp;<input type="text" name="address" value="${member[0].address}"><br><br>
	手機:&nbsp; <input type="text" name="cellPhone" value="${member[0].cellPhone}"><br><br>
	連絡電話:&nbsp; <input type="text" name="tel" value="${member[0].tel}"><br><br>
	<input type="button" value="確定修改" onclick="saveAccountData()">
</form>

</body>
<script type="text/javascript">
function saveAccountData(){
	 if($("#accountName").val() == "")  
	 {
	    $("#accountName").focus();
	    alert("帳戶名稱沒填寫");
	    return false; 
	 }else if($("#bankBranch").val() == "")
	 {
	    $("#bankBranch").focus();
	    alert("分行名稱沒填寫");
	    return false;       
	 }else if($("#accountNo").val() == "")
	 {
		    $("#accountNo").focus();
		    alert("帳戶號碼沒填寫");
		    return false;       
	}
	else if($("#identityNO").val() == "")
	{
		    $("#identityNO").focus();
		    alert("身份證字號/統一編號沒填寫");
		    return false;       
	}
	else if($("#address").val() == "")
	{
		    $("#address").focus();
		    alert("通訊地址沒填寫");
		    return false;       
	}
	else if($("#cellPhone").val() == "")
	{
		    $("#cellPhone").focus();
		    alert("手機沒填寫");
		    return false;       
	}
	else if($("#tel").val() == "")
	{
		    $("#tel").focus();
		    alert("連絡電話沒填寫");
		    return false;       
	}	        
	else{
		document.form.action="${pageContext.request.contextPath}/saveAccountData.do";
		document.form.submit();
	}
}
</script>
</html>