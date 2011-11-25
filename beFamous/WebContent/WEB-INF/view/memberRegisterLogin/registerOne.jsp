<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/001.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/002.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/003.jpg width="100" height="35"></td>
</table>

<form name="fm" method="post">
<h3>加入會員</h3>
<p>
	聯絡信箱:&nbsp;<input type="text" name="email" id="email" ><br><br>
	姓名:&nbsp;<input type="text" name="userName" id="userName"><br><br>
	密碼:&nbsp;<input type="password" name="password" id="password"><br><br>
	確認密碼:&nbsp;<input type="password" name="comfirmPassword" id="comfirmPassword"><br><br>
	性別:&nbsp;<input type="radio" name="sex" id="sex" value="1"/>男
			   <input type="radio" name="sex" id="sex" value="2"/>女<br><br>
	生日:&nbsp;
		<select name="year" id="year">
			<option selected="selected"></option>
			<option value="2011">2011</option>
			<option value="2010">2010</option>
			<option value="2009">2009</option>
			<option value="2008">2008</option>
			<option value="2007">2007</option>
			<option value="2006">2006</option>
			<option value="2005">2005</option>
			<option value="2004">2004</option>
			<option value="2003">2003</option>
			<option value="2002">2002</option>
		</select>年
		<select name="month" id="month">
			<option selected="selected"></option>
			<option value="01">1</option>
			<option value="02">2</option>
			<option value="03">3</option>
			<option value="04">4</option>
			<option value="05">5</option>
			<option value="06">6</option>
			<option value="07">7</option>
			<option value="08">8</option>
			<option value="09">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>月
		<select name="day" id="day">
			<option selected="selected"></option>
			<option value="01">1</option>
			<option value="02">2</option>
			<option value="03">3</option>
			<option value="04">4</option>
			<option value="05">5</option>
			<option value="06">6</option>
			<option value="07">7</option>
			<option value="08">8</option>
			<option value="09">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>日<br><br>	
	居住地:&nbsp;
		<select name="location" id="location">
			<option selected="selected"></option>
			<option value="台灣">台灣</option>
			<option value="大陸">大陸</option>
			<option value="日本">日本</option>
			<option value="香港">香港</option>
		</select><br><br>
	字詞驗證:&nbsp;<img alt="" src=${pageContext.request.contextPath}/images/number.jpg width="100" height="35"><br>
	<input type="text" name="number" ><br><br>
	<input type="hidden" name="birthday" id="birthday"><br><br>
	<input type="checkbox" name="agree" id="agree" value="agree">我已詳閱使用條款並同意遵守<br>	
	<center><input type="button" value="建立帳戶" onclick="add()"/></center>
</form>
</body>
<script>
function add(){
	var agree = $("input:checkbox:checked[name=agree[]]").length;
	if($("#email").val() == ""){
		alert("請輸入email");
		$("#email").focus();
		return;
	}else if($("#userName").val() == ""){
		alert("請輸入姓名");
		$("#userName").focus();
		return;
	}else if($("#password").val() == ""){
		alert("請輸入密碼");
		$("#password").focus();
		return;
	}else if($("#comfirmPassword").val() == ""){
		alert("請輸入確認密碼");
		$("#comfirmPassword").focus();
		return;
	}else if(!$("input:radio[name=sex]").is(":checked")){
		alert("請選擇性別");
		$("#sex").focus();
		return;
	}else if($("#password").val() != $("#comfirmPassword").val()){
		alert("密碼與確認密碼不符，請重新輸入");
		$("#comfirmPassword").focus();
		return;
	}else if($("#number").val() == ""){
		alert("請輸入驗證碼");
		$("#number").focus();
		return;
	}else if(!agree){
		alert("請勾選詳閱同意書");
		return;
	}else{	
		var birthday = $("#year").val()+$("#month").val()+$("#day").val();
		document.fm.birthday.value = birthday;
	    document.fm.action="${pageContext.request.contextPath}/saveRegisterOne.do";
	    document.fm.submit();
    }
}
</script>
</html>