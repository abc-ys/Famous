<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h4>帳戶資料編輯</h4>
<form name="form" method="post">	
	<input type="hidden" name="memnberID" value="${member}"><br><br>	
	帳戶名稱:<input type="text" name="accountName" value="${account.accountName}"><br><br>
	銀行名稱:<select name="banks" id="banks">
				<option value="000">中央銀行</option>
				<option value="004">台灣銀行</option>
			</select><br><br>
	分行名稱: <input type="text" name="bankBranch" value="${account.bankBranch}"><br><br>
	帳戶號碼: <input type="text" name="accountNo" value="${account.accountNO}"><br><br>
	身份證字號/統一編號:<input type="text" name="identityNO" value="${account.identityNO}"><br><br>
	通訊地址: <input type="text" name="address" value="${account.address}"><br><br>
	手機: <input type="text" name="cellPhone" value="${account.cellPhone}"><br><br>
	連絡電話: <input type="text" name="tel" value="${account.tel}"><br><br>
	<input type="hidden" name="bankName" value="${account.bankName}"><br><br>
	<input type="button" value="確定修改" onclick="saveAccountData()">
</form>

</body>
<script type="text/javascript">
function saveAccountData(){
	var bankName =$('#banks :selected').text();
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
		var bankName =$('#banks :selected').text();
		alert(bankName);
		document.form.bankName.value = bankName;
		document.form.action="${pageContext.request.contextPath}/saveAccountData.do";
		document.form.submit();
	}
}
</script>
</html>