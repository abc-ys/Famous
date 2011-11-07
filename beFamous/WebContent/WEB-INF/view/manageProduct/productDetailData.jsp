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
<input type="hidden" name="id" value="${productionCategory.productionCategoryRid}">
商品編號:&nbsp ${productionCategory.productionCategoryRid}<p>
商品名稱:&nbsp<input type="text" size="10" name="name" value="${productionCategory.productionCategoryName}"><p>
商品類別:&nbsp<input type="text" size="10" name="category" value="${productionCategory.productionCategoryName}"><p>
交易方式:&nbsp
<INPUT type=radio name=dealMethod value="現金">現金
<INPUT type=radio name=dealMethod value="GSiMoney">GSiMoney
<p>
商品價錢:<br>
售價:&nbsp<input type="text" size="5" name="price" value="${productionCategory.sdCard.sdCardPrice.pPrice}">元&nbsp&nbsp&nbsp
特惠價:&nbsp售價x<input type="text" size="5" name="price2" value="">&nbsp&nbsp&nbsp
Bonus購買額:&nbsp<input type="text" size="5" name="price3" value="${productionCategory.sdCard.sdCardPrice.discountPrice}">元+&nbsp<input type="text" size="5" name="bonus" value="${productionCategory.sdCard.sdCardPrice.discountBonus}">點
<p>
贈送bonus:&nbsp付款金額x<input type="text" size="5" name="price4" value="${productionCategory.sdCard.reward}"><p>
商品庫存:&nbsp<input type="text" size="5" name="amount" value="${productionCategory.sdCard.amount}"><p>
上架:&nbsp
<INPUT type=radio name=onSale value="上架" checked>是
<INPUT type=radio name=onSale value="下架">否<p>
商品關鍵字:&nbsp<input type="text" size="20" name="keyword" value="${productionCategory.sdCard.keyWord}"><p>
商品簡介:&nbsp<textarea cols=60 rows=6 name=msg>${productionCategory.sdCard.introduction}</textarea><p>

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