<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
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
<div id="price222">
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
<font size="2">小計:GSiMoney$ 

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
<td Width="200" Height="30" border="0"><font size="2"><img alt="" src="/${initParam.ImageWeb}/${hm.productionCategory.cover}" width="50"　height="50" ><INPUT type=hidden name=sName value="${hm.productionCategory.name}"><br></font></td>
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
<font size="2">小計:GSiMoney$ 

<INPUT type="hidden" name="tPrice" id="tPrice" Width="5" value="${tAlbumPrice}" >${tAlbumPrice}元+ ${tAlbumBonus}點<br>

</font></td>
</table>

<br>
<font color="red">總金額: GSiMoney$ ${tPrice+tAlbumPrice} 元 + ${tBonus+tAlbumBonus}點</font>


</div>
<br>

<center>
<input type="button" value="繼續購物" onclick="back()"/>
<input type="button" value="結帳" onclick="add()" <c:if test="${ (fn:length(SongDetail)==0) && (fn:length(AlbumDetail)==0) }">disabled</c:if>  />
<input type="button" value="前往儲值" onclick="go()"/>
</center>
</form>
</body>
<script>

function changePrice(useBonus,id){
	var url = "${pageContext.request.contextPath}/shoppingCartChangePrice.do?useBonus="+useBonus+"&id="+id;
	$("#price222").load(url);
}

function onDelete(id){
	if(confirm("是否刪除?")){
		var url = "${pageContext.request.contextPath}/deleteShoppingCart.do?id="+id;
		$("#price222").load(url);
		return;
	}
}



function cal(useBonus,count){
	//alert(count);
	var price=0;
	var bonus=0;
	for(i=1;i<=count;i++){
		alert(i);
		//var p1 = $("input[name='price_'"+i+"]:checked").val();
		//price = $('input[name="price"]').val([$('#price_' + i).text()]); 
		
		var method = $("input:radio:checked[name=price_"+i+"]");
		var p1 = method[0].value;
		
		
		//var ar = p1.split(',');
		//price =  val(price)+val(ar[0]);
		//bonus =  val(bonus)+val(ar[1]);
		
		alert(price);
	}
	
	
	
	/*var p1 = $("input[name='price_1']:checked").val();
	var p2 = $("input[name='price_2']:checked").val();
	alert(p1);
	alert(p2);
	*/
	//$('#tPrice').val(eval(p1)+eval(p2));
}
function add(){
     document.fm.action="${pageContext.request.contextPath}/shoppingCartTwo.do";
     document.fm.submit();
}

function go(){
	location.href('${pageContext.request.contextPath}/prepay.do');
}

</script>
</html>