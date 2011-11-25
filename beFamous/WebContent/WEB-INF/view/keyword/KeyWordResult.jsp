<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gsimedia" uri="http://www.gsi-media.com/" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/cell.css" type="text/css"/>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/js/cell.js"></script>
</head>
<body>

熱門搜尋關鍵字:
<table border="1">
<tr>
<td>

<c:forEach var="hm" items="${keyWordList[4]}" >
${hm}<br>
</c:forEach>
</td>
 
</tr>
</table>

<h1>搜尋結果</h1>


搜尋的關鍵字:${keyWord}
<br>
搜尋的結果最多顯示500筆
<a href="javascript: void(0)" onClick="display('a01');">歌曲(${fn:length(keyWordList[1])})</a>
<a href="javascript: void(0)" onClick="display('a02');">專輯(${fn:length(keyWordList[0])})</a>
<a href="javascript: void(0)" onClick="display('a03');">創作者(${fn:length(keyWordList[2])})</a>
<a href="javascript: void(0)" onClick="display('a04');">消息(${fn:length(keyWordList[3])})</a>
<br></br>
<div id="all">
  歌曲  
 <gsimedia:songTable listName="arSong"></gsimedia:songTable>

專輯
 <gsimedia:albumTable listName="arAlbum"></gsimedia:albumTable>

<br></br>
創作人
<table>
<c:forEach var="creator" items="${arCreator}" >
<tr>
<td>
<img alt="" src="${creator.picture}">
   </td>
</tr>
<tr>
<td>
${creator.userName}
專輯數:${fn:length(creator.album)}   

</td>
</tr>
</c:forEach>
</table>
</div>

<div id="a01"  style="display:none" >
<iframe id="ifr_a" src="${pageContext.request.contextPath}/queryKeyWordForSong.do?keyWord=${keyWord}" height="500" width="740" frameborder="0"> </iframe>
</div>
<div id="a02" style="display:none" >
<iframe id="ifr_b" src="${pageContext.request.contextPath}/queryKeyWordForAlbum.do?keyWord=${keyWord}" height="500" width="740" frameborder="0"> </iframe>
</div>


<div id="a03" style="display:none" >
<iframe id="ifr_c" src="${pageContext.request.contextPath}/queryKeyWordForCreator.do?keyWord=${keyWord}" height="500" width="740" frameborder="0"> </iframe>
</div>


<div id="a04" style="display:none" >
<iframe id="ifr_c" src="${pageContext.request.contextPath}/queryKeyWordForNews.do?keyWord=${keyWord}" height="500" width="740" frameborder="0"> </iframe>
</div>


</body>



<script type="text/javascript">

function display(divname){

	if(divname=='a01'){
		$("#a01").show();
		$("#a02").hide();
		$("#all").hide();
		$("#a03").hide();
		$("#a04").hide();
		//$("#divCheck").val('a01');
	}
	if(divname=='a02'){
		$("#a02").show();
		$("#a01").hide();
		$("#all").hide();
		$("#a03").hide();
		$("#a04").hide();
		//$("#divCheck").val('a02');
	}
	if(divname=='a03'){
		$("#a03").show();
		$("#a01").hide();
		$("#a02").hide();
		$("#a04").hide();
		$("#all").hide();
		//$("#divCheck").val('a03');
	}
	if(divname=='a04'){
		$("#a04").show();
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").hide();
		$("#all").hide();
		//$("#divCheck").val('a03');
	}
}
</script>


</html>