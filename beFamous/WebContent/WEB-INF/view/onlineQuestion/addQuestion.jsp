<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<form name="form" method="post">
	<h4>線上客服</h4>
	請先點選正確產品類別，並填妥下列資料，客服中心將竭誠為您處理，謝謝!<br><br>	
	產品類型:電腦使用介面<input type="radio" name="productionType" id="productionType" value="1"/>PC<br>
			手機使用介面<input type="radio" name="productionType" id="productionType" value="2"/>Android
						<input type="radio" name="productionType" id="productionType" value="3"/>Windows Phone
						<input type="radio" name="productionType" id="productionType" value="4"/>Symbian<br><br>
	身分:<input type="radio" name="userIdentity" id="userIdentity" value="1"/>會員
		<input type="radio" name="userIdentity" id="userIdentity" value="2"/>非會員<br><br>
	姓名:<input type="text" name="userName" ><br><br>
	聯絡信箱:<input type="text" name="email" ><br><br>
	連絡電話:<input type="text" name="tel" ><br><br>
	提問類型 :<select name="questionType" id="questionType">
				<option selected="selected"></option>
				<option value="1" >註冊問題</option>
				<option value="2" >付費相關</option>
				<option value="3" >下載問題</option>
				<option value="4" >軟體相關</option>
				<option value="5" >播放問題</option>
				<option value="6" >SD卡相關</option>
				<option value="7" >網站使用問題</option>
				<option value="8" >服務品質</option>
				<option value="9" >促銷活動</option>
				<option value="10" >合作提案</option>
				<option value="11" >其他問題</option>
			</select><br><br>
	問題描述:<textarea rows="6" cols="40" name="questionContent"></textarea><br><br>
	<input type="button" value="送出" onclick="saveQuestion()">
	<input type="button" value="取消" onclick="cancleQuestion()">
</form>
</body>
<script type="text/javascript">
function cancleQuestion(){
	document.form.action="${pageContext.request.contextPath}/addQuestion.do";
	document.form.submit();
}
function saveQuestion(){
	//var productionType =$('input[name=productionType]:checked').val();
	//document.form.productionType.value = productionType;
	//alert("productionType"+productionType);
	//var userIdentity =$('input[name=userIdentity]:checked').val();
	//document.form.userIdentity.value = userIdentity;
	//alert("userIdentity"+userIdentity);
	//var questionType =$('#questionType :selected').text();
	//document.form.questionType.value = questionType;
	//alert("questionType"+questionType);
	document.form.action="${pageContext.request.contextPath}/saveQuestion.do";
	document.form.submit();	
}
</script>
</html>