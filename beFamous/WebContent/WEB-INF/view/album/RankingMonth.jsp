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
排行榜
<a href="javascript: void(0)" onClick="display('a01');">專輯排行榜</a>
<a href="javascript: void(0)" onClick="display('a02');">歌曲排行榜</a>
<a href="javascript: void(0)" onClick="display('a03');">我最大聲排行榜</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript: void(0)" onClick="week();">週榜</a>
<a href="javascript: void(0)" onClick="month();"><font color="red">月榜</font></a>




<div id="b01" style="display:none">
b01
   <gsimedia:albumTable listName="arAlbum"></gsimedia:albumTable>
</div>

<div id="b02"  style="display:none" >
b02
<iframe src="${pageContext.request.contextPath}/querySongsMonthRanking.do" height="1000" width="740" frameborder="0"> </iframe>
</div>

<div id="b03" style="display:none" >
bo3
<iframe src="${pageContext.request.contextPath}/queryCreatorMonthRanking.do" height="1000" width="740" frameborder="0"> </iframe>
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

function month(){
	var value = $("#divCheck").val();
	if(value=='a01'){
		document.qForm.action="${pageContext.request.contextPath}/queryAlbumsMonthRanking.do";
		document.qForm.submit();
	}
	if(value=='a02'){
		$("#b02").show();
	}
	if(value=='a03'){
		$("#b03").show();
	}
}


</script>

</html>