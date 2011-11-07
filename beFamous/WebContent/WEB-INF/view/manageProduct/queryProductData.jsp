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
<font size="2">&nbsp商品類別:</font>&nbsp
<select name="productCategory"> 
<option value=""></option> 
<option value="4G GSiSD卡">4G GSiSD卡</option> 
<option value="儲值 300元">儲值 300元</option> 
</select>&nbsp&nbsp
<input type="submit" value="查詢" onclick="query()"/>
<p>
批次調整商品資料
<br>
<font size="2">&nbsp調整比率:</font>&nbsp
<select name="percentage"> 
<option value=""></option> 
<option value="調整特惠價">調整特惠價</option> 
</select>&nbsp&nbsp&nbsp售價x&nbsp
<input type="text" size="5" name="changePrice">&nbsp&nbsp&nbsp
<input type="submit" value="調整" onclick="change()"/>
<p>
查詢結果&nbsp&nbsp&nbsp<input type="submit" value="儲存修改" onclick="saveModify()"/>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="80" Height="35"><font size="2">商品編號</font></td>
	<td Width="100"><font size="2">商品名稱</font></td>
	<td Width="100"><font size="2">商品類別</font></td>
	<td Width="100"><font size="2">廠商</font></td>
	<td Width="40"><font size="2">上架</font></td>
	<td Width="40"><font size="2">數量</font></td>
	<td Width="50"><font size="2">售價</font></td>
	<td Width="50"><font size="2">特惠價</font></td>
	<td Width="80"><font size="2">Bonus購買額</font></td>
	<td Width="100"><font size="2">贈送Bonus</font></td>
	<td Width="40"><font size="2">編輯</font></td></tr>
	
	<c:forEach var="hm" items="${productionCategory}">
<tr><td><font size="2">${hm.productionCategoryRid}</font></td>
	<td><font size="2">${hm.productionCategoryName}</font></td>
	<td><font size="2">${hm.productionCategoryName}</font></td>
	<td><font size="2">${hm.sdCard.companyName}</font></td>
	<td><INPUT type="checkbox" name="onsale"></td>
	<td><font size="2">${hm.sdCard.amount}</font></td>
	<td><input type=text name="price" size="3"></td>
	<td><input type=text name="price2" size="3"></td>
	<td><input type=text name="price3" size="6"></td>
	<td><font size="2">${hm.sdCard.reward}</font></td>
	<td><font size="2"><a href="javascript:editData()">編輯</a></font></td>
	</tr>
	</c:forEach>
</table>
<input type="submit" value="儲存修改" onclick="change()"/>
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
function editData(){
    document.fm.action="${pageContext.request.contextPath}/productDetailData.do";
    document.fm.submit();
}
function saveModify(){
    document.fm.action="${pageContext.request.contextPath}/saveModify.do";
    document.fm.submit();
}
</script>
</html>