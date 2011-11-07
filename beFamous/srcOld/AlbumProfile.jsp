<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table  border="0">
<tr>
<td><img alt="" src="${profile[0].cover}">

</td>
<td>
<h1>${profile[0].name}</h1>
<input type="button" name="like" value="like">
<p>創作人名稱: ${profile[0].creator.accountName}  </p>
<p>形式:      ${profile[0].type}      </p>
<p>音樂類型:   ${profile[0].musicCategory.name}   </p>
<p>發行日期:   ${profile[0].date}   </p>
<p>廠牌:      ${profile[0].brand}</p>
<table border="1">
   <tr>
      <td>50元</td>
      <td>20元+30點</td>
   </tr>
   <tr>
      <td><input type="button" name="cart" value="加到購物車"></td>
      <td><input type="button" name="offense" value="檢舉"></td>
   </tr>
</table>

</td>
<td>
<h1>專輯簡介</h1>
${profile[0].introduction}
</td>

</tr>
</table>

<br><br>
歌曲列表<br>
<a href="javascript: void(0)" onClick="display('a01');">歌曲(10)</a>
<a href="javascript: void(0)" onClick="display('a02');">其他專輯(3)</a>

<div id="a01" >
<iframe src="querySongList.do" frameborder="0"> </iframe>
</div>

<div id="a02" style="display:none" >
<iframe src="queryOtherAlbum.do" frameborder="0"> </iframe>
</div>

<input type="radio" id="aaa" name="aaa" value="test">good
<input type="button" name="aaa" value="測試" onclick="test()">
推薦專輯
<table>
<tr>
<td>
<img alt="" src="images/forPhone.png">
</td>
<td>
渴望
郭富城
</td>
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
	var f = $("#aaa").val();
	alert(f);
}


</script>

</html>


