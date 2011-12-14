<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<table border="0" cellpadding="0" cellspacing="0" align="center">
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/001.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/002.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/003.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
點選結帳後即可享受您購買的專輯
<p>
歌曲:
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr>
<td Width="200" Height="30" border="0"><font size="2">專輯封面/歌曲名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">專輯名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">創作人<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">價格<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus<br></font></td>
</tr>
<c:forEach var="hm" items="${SongDetail}">
<tr>
<td Width="200" Height="30" border="0"><font size="2">
<img alt="" src="/${initParam.ImageWeb}/${hm.productionCategory.album.cover}" width="50"　height="50" >${hm.productionCategory.name}<br></font>
</td>
<td Width="200" Height="30" border="0"><font size="2">
${hm.productionCategory.album.name}<br></font>
</td>
<td Width="200" Height="30" border="0"><font size="2">
${hm.productionCategory.album.creator.userName}<br></font>
</td>
<td Width="200" Height="30" border="0"><font size="2">
<c:if test="${hm.useBonus=='N'}">${hm.productionCategory.songPrice.price}</c:if>
<c:if test="${hm.useBonus=='Y'}">${hm.productionCategory.songPrice.discountPrice} + ${hm.productionCategory.songPrice.discountBonus}點</c:if>
<br></font>
</td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus ${hm.productionCategory.songPrice.reward}點<br></font></td>
</tr>

</c:forEach>
<input type="hidden" name="tBonus" value="${tBonus}">
<input type="hidden" name="tPrice" value="${tPrice}">
<input type="hidden" name="shoppingCartId" value="${shoppingCartId}">
<td Width="200" Height="30" border="0" colspan="5" align="right"><font size="2">小計:GSiMoney$ ${tPrice}+${tBonus}點 <br></font></td>
</table>

專輯:
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">專輯封面<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">專輯名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">創作人<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">價格<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus<br></font></td>

<c:forEach var="hm" varStatus="status" items="${AlbumDetail}">
<c:forEach var="hm2" items="${hm.productionCategory.songSet}">
<c:set var="albumPrice" value="${albumPrice + hm2.songPrice.price }"></c:set>
<c:set var="albumDiscountPrice" value="${albumDiscountPrice + hm2.songPrice.discountPrice }"></c:set>
<c:set var="albumDiscountBonus" value="${albumDiscountBonus + hm2.songPrice.discountBonus }"></c:set>
<c:set var="albumReward" value="${albumReward + hm2.songPrice.reward }"></c:set>
</c:forEach>
<tr>
<td Width="200" Height="30" border="0"><font size="2"><img alt="" src="/${initParam.ImageWeb}/${hm.productionCategory.cover}" width="50"　height="50" ><INPUT type=hidden name=sName value="${hm.productionCategory.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.name}<INPUT type=hidden name=aName2 value="${hm.productionCategory.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.creator.userName}<INPUT type=hidden name=sUserName value="${hm.productionCategory.creator.userName}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">
<c:if test="${hm.useBonus=='N'}">${albumPrice }</c:if> <br>
<c:if test="${hm.useBonus=='Y'}">${albumDiscountPrice} + ${albumDiscountBonus}點</c:if>  <br></font></td>
<td Width="200" Height="30" border="0"><font size="2">贈送GSiBonus ${albumReward}點<br></font></td>
</tr>

</c:forEach>
<tr>

<input type="hidden" name="tAlbumBonus" value="${tAlbumBonus}">
<input type="hidden" name="tAlbumPrice" value="${tAlbumPrice}">
<td Width="200" Height="30" border="0" colspan="5" align="right">
<font size="2">小計:GSiMoney$ 

${tAlbumPrice}元+ ${tAlbumBonus}點<br>

</font></td>
</table>
<br>
<input type="hidden" name="totalBonus" value="${tBonus+tAlbumBonus}">
<input type="hidden" name="totalPrice" value="${tPrice+tAlbumPrice}">
<font color="red">購物車共有 ${fn:length(SongDetail)+fn:length(AlbumDetail)}件商品，總金額 GSiMoney$ ${tPrice+tAlbumPrice}元+ ${tBonus+tAlbumBonus}點</font>

<center><input type="button" value="確認無誤結帳" onclick="add()"/></center>
</form>
</body>
<script>
function add(){
     document.fm.action="${pageContext.request.contextPath}/shoppingCartThree.do";
     document.fm.submit();
}
</script>
</html>