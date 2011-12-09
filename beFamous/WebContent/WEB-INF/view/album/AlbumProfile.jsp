<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/cell.css" type="text/css"/>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/js/cell.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<table border="0">
<tr>
<td><img alt="" src="/${initParam.ImageWeb}/${album[0].cover}" width="190" height="80">

</td>
<td>
<h1>${album[0].name}</h1>
<input type="button" name="like" value="like">
<p>創作人名稱: ${album[0].creator.userName}  </p>
<p>形式:      
<c:if test="${album[0].type == 1}">單曲</c:if>
<c:if test="${album[0].type == 2}">EP</c:if>
<c:if test="${album[0].type == 3}">專輯</c:if></p>
<p>音樂類型:   ${album[0].musicCategory.name}   </p>
<p>發行日期:   
<fmt:parseDate var="dateObj" value="${album[0].createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></p>
<p>廠牌:      ${album[0].brand}</p>
<table border="1">
   <tr>
      <td>${price}元</td>
      <td>${discountPrice}元+${discountBonus}點</td>
   </tr>
   <tr>
      <td><input type="button" name="cart" value="加到購物車"></td>
      <td><input type="button" name="offense" value="檢舉" onclick="addOffense('${userId}','${album[0].pid}')"></td>
   </tr>
</table>

</td>
<td>
<h1>專輯簡介</h1>
${album[0].introduction}
</td>

</tr>
</table>

<br><br>
歌曲列表<br>
<a href="javascript: void(0)" onClick="display('a01');">歌曲(${fn:length(album[0].songSet)})</a>
<a href="javascript: void(0)" onClick="display('a02');">其他專輯(${fn:length(album[2])})</a>

<div id="a01" >
<iframe src="${pageContext.request.contextPath}/querySongList.do?albumID=${album[0].pid}" height="500" width="740" frameborder="0"> </iframe>
</div>

<div id="a02" style="display:none" >
<iframe src="${pageContext.request.contextPath}/queryOtherAlbum.do?albumID=${album[0].pid}&creatorID=${album[0].creator.id}"  height="500" width="740" frameborder="0"> </iframe>
</div>


推薦專輯
<table>
    <tr>
    <c:forEach var="album" items="${album[1]}">
 		<td><img alt="" src="/${initParam.ImageWeb}/${album.cover}" width="190"　height="80"></img><br><a href="${pageContext.request.contextPath}/queryAlbumData.do?albumid=${album.pid}">album ${album.name}</a><br><a href="">artist ${album.creator.userName}</a><br>date${album.createDate}</td>
 	</c:forEach>
 	</tr>
</table>
</body>

<script type="text/javascript">
function display(divname){

	if(divname=='a01'){
		$("#a01").show();
		$("#a02").hide();
	}
	if(divname=='a02'){
		$("#a02").show();
		$("#a01").hide();
	}
	
}
function test(){
	//var f = $("#aaa").val();
	var f1 = $("#bbb").val();
	var s = $("input[name='aaa']:checked").val();
	alert(s);
	//$("#total").val(eval(f)+eval(f1));
	//alert(f);
}
function addOffense(userId,productionCategoryId){
	window.open("${pageContext.request.contextPath}/offense.do?userId="+userId+"&productionCategoryId="+productionCategoryId,'son','height=300,width=600,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}


</script>

</html>


