<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
排行榜<br>
<a href="javascript: void(0)" onClick="display('a01');">專輯排行榜</a>
<a href="javascript: void(0)" onClick="display('a02');">歌曲排行榜</a>
<a href="javascript: void(0)" onClick="display('a03');">我最大聲排行榜</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript: void(0)" onClick="week();"><font color="red">週榜</font></a>
<a href="javascript: void(0)" onClick="month();">月榜</a>

<div id="week">
<div id="a01">
   <gsimedia:albumTable listName="arAlbum"></gsimedia:albumTable>
</div>

<div id="a02"  style="display:none" >
<iframe id="ifr_a" src="${pageContext.request.contextPath}/querySongsWeekRanking.do" height="1000" width="740" frameborder="0"> </iframe>
</div>

<div id="a03" style="display:none" >
<iframe id="ifr_b" src="${pageContext.request.contextPath}/queryCreatorWeekRanking.do" height="1000" width="740" frameborder="0"> </iframe>
</div>

</div>




<form name="qForm" action="" method="post">
<input type="hidden" id="divCheck" name="divCheck" value="">

</form>


</body>
<script type="text/javascript">
function display(divname){

	if(divname=='a01'){
		$("#a01").show();
		$("#a02").hide();
		$("#a03").hide();
		$("#divCheck").val('a01');
	}
	if(divname=='a02'){
		$("#a02").show();
		$("#a01").hide();
		$("#a03").hide();
		$("#divCheck").val('a02');
	}
	if(divname=='a03'){
		$("#a03").show();
		$("#a01").hide();
		$("#a02").hide();
		$("#divCheck").val('a03');
	}

}


function week(){
	var value = $("#divCheck").val();
	if(value=='a01'){
		document.qForm.action="queryAlbumsWeekRanking.do";
		document.qForm.submit();
	}
	if(value=='a02'){
		$('#ifr_a').get(0).src = "querySongsWeekRanking.do";
	}
	if(value=='a03'){
		$('#ifr_b').get(0).src = "queryCreatorWeekRanking.do";;
	}
}


function month(){
	var value = $("#divCheck").val();
	if(value=='a01'){
		document.qForm.action="queryAlbumsMonthRanking.do";
		document.qForm.submit();
	}
	if(value=='a02'){
		$('#ifr_a').get(0).src = "querySongsMonthRanking.do";
	}
	if(value=='a03'){
		$('#ifr_b').get(0).src = "queryCreatorMonthRanking.do";;
	}
}


</script>

</html>