<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
歌曲:
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">專輯封面/歌曲名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">專輯名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">創作人<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">價格<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">移除<br></font></td>

<c:forEach var="hm" varStatus="status" items="${SongDetail}">
<tr>
<td Width="200" Height="30" border="0"><font size="2"><img alt="" src="/${initParam.ImageWeb}/${hm.productionCategory.album.cover}" width="50"　height="50" >${hm.productionCategory.name}<INPUT type=hidden name=sName value="${hm.productionCategory.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.album.name}<INPUT type=hidden name=aName2 value="${hm.productionCategory.album.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.album.creator.userName}<INPUT type=hidden name=sUserName value="${hm.productionCategory.album.creator.userName}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">
<INPUT type="radio" name="sprice_${status.count}" id="sprice_${status.count}" value="${hm.productionCategory.songPrice.price}" <c:if test="${hm.useBonus=='N'}">checked</c:if> onclick="changePrice('N','${hm.id}');">$ ${hm.productionCategory.songPrice.price}<br></font>
<font size="2">
<INPUT type="radio" name="sprice_${status.count}" id="sprice_${status.count}" value="${hm.productionCategory.songPrice.discountPrice},${hm.productionCategory.songPrice.discountBonus}" <c:if test="${hm.useBonus=='Y'}">checked</c:if> onclick="changePrice('Y','${hm.id}');">$ ${hm.productionCategory.songPrice.discountPrice} + ${hm.productionCategory.songPrice.discountBonus}點<br></font></td>
<td Width="200" Height="30" border="0"><input type="button" name="111" value="刪除" onclick="onDelete('${hm.id}');"></td>
</tr>

</c:forEach>
<tr>

<input type="hidden" name="tBonus" value="">
<input type="hidden" name="choisePrice" value="">
<td Width="200" Height="30" border="0" colspan="5" align="right">
<font size="2">小計；GSiMoney$ 

<INPUT type="hidden" name="tPrice" id="tPrice" Width="5" value="${tPrice}" >${tPrice}元+ ${tBonus}點<br>

</font></td>
</table>
<br>
專輯:
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">專輯封面<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">專輯名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">創作人<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">價格<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">移除<br></font></td>

<c:forEach var="hm" varStatus="status" items="${AlbumDetail}">
<c:forEach var="hm2" items="${hm.productionCategory.songSet}">
<c:set var="albumPrice" value="${albumPrice + hm2.songPrice.price }"></c:set>
<c:set var="albumDiscountPrice" value="${albumDiscountPrice + hm2.songPrice.discountPrice }"></c:set>
<c:set var="albumDiscountBonus" value="${albumDiscountBonus + hm2.songPrice.discountBonus }"></c:set>
</c:forEach>
<tr>
<td Width="200" Height="30" border="0"><font size="2"><img alt="" src="/${initParam.ImageWeb}/${hm.productionCategory.cover}" width="50"　height="50" >${hm.productionCategory.name}<INPUT type=hidden name=sName value="${hm.productionCategory.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.name}<INPUT type=hidden name=aName2 value="${hm.productionCategory.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.creator.userName}<INPUT type=hidden name=sUserName value="${hm.productionCategory.creator.userName}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">
<INPUT type="radio" name="aprice_${status.count}" id="aprice_${status.count}" <c:if test="${hm.useBonus=='N'}">checked</c:if> value="${albumPrice }" onclick="changePrice('N','${hm.id}');">${albumPrice }<br></font>
<font size="2">
<INPUT type="radio" name="aprice_${status.count}" id="aprice_${status.count}" value="${albumDiscountPrice},${albumDiscountBonus}" <c:if test="${hm.useBonus=='Y'}">checked</c:if> onclick="changePrice('Y','${hm.id}');">$ ${albumDiscountPrice} + ${albumDiscountBonus}點<br></font></td>
<td Width="200" Height="30" border="0"><input type="button" name="111" value="刪除" onclick="onDelete('${hm.id}');"></td>
</tr>

</c:forEach>
<tr>

<input type="hidden" name="tBonus" value="">
<input type="hidden" name="choisePrice" value="">
<td Width="200" Height="30" border="0" colspan="5" align="right">
<font size="2">小計；GSiMoney$ 

<INPUT type="hidden" name="tPrice" id="tPrice" Width="5" value="${tAlbumPrice}" >${tAlbumPrice}元+ ${tAlbumBonus}點<br>

</font></td>
</table>
<br>
<font color="red">總金額: GSiMoney$ ${tPrice+tAlbumPrice} 元 + ${tBonus+tAlbumBonus}點</font>
</body>
</html>