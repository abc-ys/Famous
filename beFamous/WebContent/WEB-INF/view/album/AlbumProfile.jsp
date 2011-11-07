<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<td><img alt="" src="${album.cover}">

</td>
<td>
<h1>${profile[0].name}</h1>
<input type="button" name="like" value="like">
<p>創作人名稱: ${album.creator.accountName}  </p>
<p>形式:      ${album.type}      </p>
<p>音樂類型:   ${album.musicCategory.name}   </p>
<p>發行日期:   ${album.date}   </p>
<p>廠牌:      ${album.brand}</p>
<table border="1">
   <tr>
      <td>${price}元</td>
      <td>${discountPrice}元+${discountBonus}點</td>
   </tr>
   <tr>
      <td><input type="button" name="cart" value="加到購物車"></td>
      <td><input type="button" name="offense" value="檢舉"></td>
   </tr>
</table>

</td>
<td>
<h1>專輯簡介</h1>
${album.introduction}
</td>

</tr>
</table>

<br><br>
歌曲列表<br>
<a href="javascript: void(0)" onClick="display('a01');">歌曲(${fn:length(songList)})</a>
<a href="javascript: void(0)" onClick="display('a02');">其他專輯(${fn:length(arOtherAlbum)})</a>

<div id="a01" >
<iframe src="${pageContext.request.contextPath}/querySongList.do" height="500" width="740" frameborder="0"> </iframe>
</div>

<div id="a02" style="display:none" >
<iframe src="${pageContext.request.contextPath}/queryOtherAlbum.do"  height="500" width="740" frameborder="0"> </iframe>
</div>


推薦專輯
<table>
    <tr>
    <c:forEach var="album" items="${recommandAlbum}">
 		<td><img alt="" src="http://localhost:8080/ImageWeb/${album.cover}"  width="190"　height="80"></img><br><a href="${pageContext.request.contextPath}/queryAlbumData.do?albumid=${album.albumID}">album ${album.name}</a><br><a href="">artist ${album.creator.userName}</a><br>date${album.date}</td>
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


</script>

</html>


