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
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/001.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/002.jpg width="100" height="35"></td>
<td Width="200" Height="30" border="0"><img alt="" src=${pageContext.request.contextPath}/images/003.jpg width="100" height="35"></td>
</table>
<br>
購物車
<p>
點選結帳後即可享受您購買的專輯
<p>
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<td Width="200" Height="30" border="0"><font size="2">專輯封面/歌曲名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">專輯名稱<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">創作人<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">價格<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">移除<br></font></td>

<c:forEach var="hm" items="${albumSong}">
<tr>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.name}<INPUT type=hidden name=sName value="${hm.productionCategory.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.album.name}<INPUT type=hidden name=aName2 value="${hm.productionCategory.album.name}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">${hm.productionCategory.album.creator.userName}<INPUT type=hidden name=sUserName value="${hm.productionCategory.album.creator.userName}"><br></font></td>
<td Width="200" Height="30" border="0"><font size="2">
<INPUT type=radio name=sprice id=sprice value="${hm.productionCategory.songPrice.price}" checked onclick="cal()">$ ${hm.productionCategory.songPrice.price}<br></font>
<font size="2">
<INPUT type=radio name=sprice id=sprice value="${hm.productionCategory.songPrice.discountPrice}" onclick="cal()">$ ${hm.productionCategory.songPrice.discountPrice} + ${hm.productionCategory.songPrice.discountBonus}點<br></font></td>
<td Width="200" Height="30" border="0"><font size="2">x<br></font></td>
</tr>
<c:set var="totalPrice" value="${totalPrice + hm.productionCategory.songPrice.price }"></c:set>
</c:forEach>
<tr>
<% 

String tBonus="100";
%>

<INPUT type=hidden name=tBonus value="<%=tBonus %>">
<td Width="200" Height="30" border="0" colspan="5" align="right"><font size="2">總金額；GSiMoney$ <INPUT type="hidden" name="tPrice" id="tPrice" Width="5" value="400" >${totalPrice}元+ <%=tBonus %>點<br></font></td>
</table>
<br>

<center><input type="button" value="繼續購物" onclick="back()"/><input type="button" value="結帳" onclick="add()"/><input type="button" value="前往儲值" onclick="go()"/></center>
</form>
</body>
<script>
function cal(){
	var p1 = $("input[name='aprice']:checked").val();
	var p2 = $("input[name='sprice']:checked").val();;
	
	$('#tPrice').val(eval(p1)+eval(p2));
}
function add(){
     document.fm.action="${pageContext.request.contextPath}/shoppingCartTwo.do";
     document.fm.submit();
}
</script>
</html>