<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
	<p>
	查詢商品資料
	<br>
	<font size="2">&nbsp商品類別:</font>&nbsp;
	<select name="productionClassificationId">
			<c:forEach var="hm" items="${productionClassification[0]}">
				<option value="${hm.id}">${hm.name}</option> 
			</c:forEach>
		</select>
	&nbsp;&nbsp;
	<input type="submit" value="查詢" onclick="query()"/>
	<p>
	批次調整商品資料
	<br>
	<font size="2">&nbsp;調整比率:</font>&nbsp;
	<select name="condition"> 
	<option value="1">定價</option>
	<option value="2">贈送Bonus</option>  
	</select>&nbsp;&nbsp;&nbsp;售價x&nbsp
	<input type="text" size="5" name="rate">&nbsp;&nbsp;&nbsp;
	<input type="submit" value="調整" onclick="change()"/>
</form>
<p>

<form name="fm2" method="post">
查詢結果&nbsp;&nbsp;&nbsp;
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="70" Height="35"><center><font size="2">商品編號</font></center></td>
	<td Width="100"><center><font size="2">商品名稱</font></center></td>
	<td Width="80"><center><font size="2">商品類別</font></center></td>
	<td Width="80"><center><font size="2">廠商</font></center></td>
	<td Width="40"><center><font size="2">上架</font></center></td>
	<td Width="40"><center><font size="2">數量</font></center></td>
	<td Width="50"><center><font size="2">售價</font></center></td>
	<td Width="50"><center><font size="2">特惠價</font></center></td>
	<td Width="100"><center><font size="2">Bonus購買額</font></center></td>
	<td Width="80"><center><font size="2">贈送Bonus</font></center></td>
	<td Width="50"><center><font size="2">編輯</font></center></td></tr>
	
	<c:forEach var="hm2" items="${productionClassification[1]}">
	<tr><td><center><font size="2">${hm2.id}</font></center></td>
		<td><center><font size="2">${hm2.name}</font></center></td>
		<td><center><font size="2">${hm2.productionClassification.name}</font></center></td>
		<td><center><font size="2">${hm2.companyName}</font></center></td>
		<td><center><INPUT type="checkbox" name="status"></center></td>
		<td><center><font size="2">${hm2.amount}</font></center></td>
		<td><center><input type=text name="realPrice" size="3" value=${hm2.sdCardPrice.price}></center></td>
		<td><center><input type=text name="specialPrice" size="3" value=${hm2.sdCardPrice.specialPrice}></center></td>
		<td><center><font size="2">${hm2.sdCardPrice.discountPrice}元+&nbsp;${hm2.sdCardPrice.discountBonus}點</font></center></td>
		<td><center><font size="2">${hm2.reward}</font></center></td>
		<td><center><font size="2"><a href="javascript:editData(${hm2.id})">編輯</a></font></center></td>
	</tr>
	</c:forEach>
</table>
<input type="submit" value="儲存修改" onclick="saveModify()"/>
</form>
</body>
<script>
function query(){
     document.fm.action="${pageContext.request.contextPath}/queryProductData.do";
     document.fm.submit();
}
function change(){
    document.fm.action="${pageContext.request.contextPath}/allChange.do";
    document.fm.submit();
}
function editData(productId){
    document.fm2.action="${pageContext.request.contextPath}/productDetailData.do?productId="+productId;
    document.fm2.submit();
}
function saveModify(){ 
    document.fm2.action="${pageContext.request.contextPath}/saveModify.do";
    document.fm2.submit();
}
</script>
</html>