<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">

<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step001.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step002.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/step003.jpg width="100" height="35"></td>
</table>
<br>
購物車 
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>選擇付款方式</b></font></font></td></table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="400"  Height="70">
	<font size="2">
		<INPUT type="radio" name="pay" id="pay" value="VISA">信用卡刷卡(可接受VISA,Master,JCB,聯合信用卡)<br>
		<INPUT type="radio" name="pay" id="pay" value="ATM">ATM轉帳(海外客戶限使用ATM轉帳，轉帳幣別為台幣)
	</font>
</td>
</table>

<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>收件人資訊</b></font></font></td></table>
<p>
<table border="2" BorderColor="white" cellpadding="0" cellspacing="0">
<tr>
	<c:if test="${empty member.receiver}">
		<td Width="65" Height="40"><font size="2">&nbsp;收件人:</font></td><td>&nbsp;<input type="text" name="receiver"></td>
	</c:if>
	<c:if test="${!empty member.receiver}">
		<td Width="65" Height="40"><font size="2">&nbsp;收件人:</font></td><td>&nbsp;<input type="text" name="receiver" value="${member.receiver}"></td>
	</c:if>
</tr>
<tr>
<td></td>
<td><font color="#000000" size="2" >&nbsp;收件人姓名與寄送地址請填寫中文以利辨識</font></td>
</tr>
<tr>
	<c:if test="${empty member.tel}">
		<td Width="65" Height="40"><font size="2">&nbsp;連絡電話:</font></td><td>&nbsp;<input type="text" name="tel"></td>
	</c:if>
	<c:if test="${!empty member.tel}">
		<td Width="65" Height="40"><font size="2">&nbsp;連絡電話:</font></td><td>&nbsp;<input type="text" name="tel" value="${member.tel}"></td>
	</c:if>
</tr>
<tr>
	<c:if test="${empty member.email}">
		<td Width="65" Height="40"><font size="2">&nbsp;email:</font></td><td>&nbsp;<input type="text" name="email"></td>
	</c:if>
	<c:if test="${!empty member.email}">
		<td Width="65" Height="40"><font size="2">&nbsp;email:</font></td><td>&nbsp;<input type="text" name="email" value="${member.email}"></td>
	</c:if>
</tr>
<tr>
<td></td>
<td><font size="2">&nbsp為避免收不到交易相關通知，請確認您所填寫的email，請盡量不要填寫入口網站免費提供的web mail address</font></td>
</tr>
<tr>
<td Width="65" Height="40"><font size="2">&nbsp;郵寄地址:</font></td>
<td>&nbsp;
	<select name="city"> 
	<option value="">請選擇縣市</option> 
	<option value="基隆市">基隆市</option> 
	<option value="台北市">台北市</option> 
	<option value="新北市">新北市</option> 
	<option value="桃園縣">桃園縣</option> 
	</select>&nbsp&nbsp
	<select name="region"> 
	<option value="">請選擇鄉鎮市區</option> 
	<option value="三重區">三重區</option> 
	<option value="大同區">大同區</option> 
	<option value="中正區">中正區</option> 
	<option value="中山區">中山區</option> 
	</select>&nbsp;&nbsp;&nbsp;
	
	<font size="2">&nbsp;郵遞區號: <input type="text" name="number" size="5"></font></td>
</tr>
<tr>
<td></td>
	<c:if test="${empty member.address}">
		<td>&nbsp<input type="text" name="address" size="80"></td>
	</c:if>
	<c:if test="${!empty member.address}">
		<td>&nbsp<input type="text" name="address" size="80" value="${member.address}"></td>
	</c:if>
</tr>
<tr>
<td></td>
<td><font size="2">&nbsp;*配送時間為白天，請填寫白天可以簽收的中文地址，以利宅配人員遞送與聯繫<br>&nbsp*因商品配送採用宅配到府，需專人簽收，請勿填寫郵政信箱</font></td>
</tr>
<tr>
<td><font size="2">&nbsp;配送時間: </font></td>
<td><font size="2">
<INPUT type=radio name="shipTime" value="1">不限時
<INPUT type=radio name="shipTime" value="2">上午8-12時
<INPUT type=radio name="shipTime" value="3">下午12-17時
<INPUT type=radio name="shipTime" value="4">晚上17時以後
</font></td>
</tr>
</table>
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0" Width="200" Height="35"><td><font size="2" color="#880000"><b>發票資訊</b></font></font></td></table>
<p>
<table border="0" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="135" Height="60" rowspan="2" valign="top">
<font size="2"><INPUT type=radio name="bill" id="bill" value="eleCheck" onclick="onBill('1');">二聯式電子發票</font></td>
<td><font size="2">&nbsp;買受人: <input type="text" id="msg1" name="elecBillPerson"> 可不填寫</font></td></tr>
<tr><td><font size="2">&nbsp;電子發票不隨或寄出，我們幫您兌換，核准文號:財北國稅松山營業字第0960211565號</font></td>
</tr>
<tr>
<td Width="135" Height="35" valign="top">
<font size="2"><INPUT type=radio name=bill id="bill" value="paperCheck" onclick="onBill('2');">二聯式紙本發票</font></td>
<td><font size="2">&nbsp;買受人: <input type="text" id="msg2" name="peperBillPerson"> 可不填寫</font></td>
</tr>
<tr>
<td Width="135" Height="95" rowspan="3" valign="top">
<font size="2"><INPUT type=radio name=bill id="bill" value="commonCheck" onclick="onBill('3');">三聯式發票</font></td>
<td><font size="2">&nbsp;統一編號: <input type="text" id="msg3" name="threeBillNo"></font></td></tr>
<tr><td><font size="2">&nbsp;發票抬頭: <input type="text" id="msg4" name="threeBillTitle"> 限22個文字，若超過請電洽客服人員處理</font></td></tr>
<tr><td><font size="2">&nbsp;依統一發票使用辦法規定；個人戶(二聯式)發票一經開立，不得任意更改或改開公司戶(三聯式)發票。</font></td></tr>
</table>
<br>
<p>
<center><input type="button" value="下一步" onclick="add()"/></center>

<INPUT type=hidden name=billData value="">
<INPUT type=hidden name="amount" value="${amount}">
<INPUT type=hidden name="priceType" value="${priceType}">
</form>
</body>
<script>

function onBill(id){
	if(id=='1'){
		$("#msg1").attr("disabled",false);
		$("#msg2").attr("disabled",true); 
		$("#msg3").attr("disabled",true); 
		$("#msg4").attr("disabled",true); 
	}else if(id=='2'){
		$("#msg1").attr("disabled",true);
		$("#msg2").attr("disabled",false); 
		$("#msg3").attr("disabled",true); 
		$("#msg4").attr("disabled",true); 
	}else if(id=='3'){
		$("#msg1").attr("disabled",true);
		$("#msg2").attr("disabled",true); 
		$("#msg3").attr("disabled",false); 
		$("#msg4").attr("disabled",false); 
	}
}

function add(){	
	//var billData;
	/*if($('input[name=bill]').get(0).checked == true){
		billData=document.fm.bill[0].value+'  買受人: '+document.fm.msg1.value;
	}
	if($('input[name=bill]').get(1).checked == true){
		billData=document.fm.bill[1].value+'  買受人: '+document.fm.msg2.value;
	}
	if($('input[name=bill]').get(2).checked == true){
		billData=document.fm.bill[2].value+'  統一編號: '+document.fm.msg3.value+'  發票抬頭: '+document.fm.msg4.value;
	}*/
	 var bill = $("input[name='bill']:checked").val();
	alert(bill);
	document.fm.billData.value=bill;	
    document.fm.action="${pageContext.request.contextPath}/sdCardThree.do";
    document.fm.submit();
}
</script>
</html>