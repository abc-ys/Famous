<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
詳細商品資料
<p>
<input type="hidden" name="productId" value="${production[0].pid}">
商品編號:&nbsp; ${production[0].pid}<p>
商品名稱:&nbsp;<input type="text" size="10" name="productName" value="${production[0].name}"><p>
商品類別:&nbsp;
<select name="newProductionClassificationId">
	<c:forEach var="hm" items="${production[1]}">
		<option value="${hm.id}">${hm.name}</option> 
	</c:forEach>
</select><p>
交易方式:&nbsp;
<c:if test="${empty production[0].transactionType}">
	<INPUT type=radio name=transactionType value="1">現金
	<INPUT type=radio name=transactionType value="2">GSiMoney
</c:if>
<c:if test="${production[0].transactionType==1}">
	<INPUT type=radio name=transactionType value="1" checked>現金
	<INPUT type=radio name=transactionType value="2">GSiMoney
</c:if>
<c:if test="${production[0].transactionType==2}">
	<INPUT type=radio name=transactionType value="1">現金
	<INPUT type=radio name=transactionType value="2" checked>GSiMoney
</c:if>
<p>
商品價錢:<br>
售價:&nbsp;<input type="text" size="5" name="realPrice" value="${production[0].sdCardPrice.price}">元&nbsp;&nbsp;&nbsp;
特惠價:&nbsp;售價x<input type="text" size="5" name="specialPrice" value="">&nbsp;&nbsp;&nbsp;
Bonus購買額:&nbsp;<input type="text" size="5" name="discountPrice" value="${production[0].sdCardPrice.discountPrice}">元+&nbsp;<input type="text" size="5" name="discountBonus" value="${production[0].sdCardPrice.discountBonus}">點
<p>
贈送bonus:&nbsp;付款金額x<input type="text" size="5" name="giveBonus" value="${production[0].reward}"><p>
商品庫存:&nbsp;<input type="text" size="5" name="stock" value="${production[0].amount}"><p>
上架:&nbsp;
<c:if test="${empty production[0].status}">
	<INPUT type=radio name=status value="1">是
	<INPUT type=radio name=status value="2">否<p>
</c:if>
<c:if test="${production[0].status==1}">
	<INPUT type=radio name=status value="1" checked>是
	<INPUT type=radio name=status value="2">否<p>
</c:if>
<c:if test="${production[0].status==2}">
	<INPUT type=radio name=status value="1">是
	<INPUT type=radio name=status value="2" checked>否<p>
</c:if>
商品關鍵字:&nbsp;<input type="text" size="20" name="keyword" value="${production[0].keyWord}"><p>
商品簡介:&nbsp;<textarea cols=60 rows=6 name=memo>${production[0].introduction}</textarea><p>
<input type="hidden" name="productionClassificationId" value="${production[0].productionClassification.id}">
<input type="hidden" name="adminId" value="2">
<center><input type="submit" value="儲存修改" onclick="saveData()"/></center>
</form>
</body>
<script>
function saveData(){
	document.fm.action="${pageContext.request.contextPath}/updateProductDetailData.do";
    document.fm.submit();
}
</script>
</html>