<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<p>修改專輯月榜</p>

<form action="" name="qForm" method="post">
專輯名稱:<input type="text" name="albumName"><br>
創作人:<input type="text" name="creatorName"><br>
類別:
<select name="musicCategory">  
<c:forEach var="musicCategory" items="${mc}">
	<option value="${musicCategory.id}">${musicCategory.name}</option> 
</c:forEach>
</select>
<br>
上架時間:
<input type="text" name="startDate" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('qForm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
&nbsp;-&nbsp;
<input type="text" name="endDate" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('qForm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
<br>

<input type="button" name="111" value="查詢" onclick="onSubmit()"><p>



<c:if test="${arAlbum!=null}">
查詢結果<br>
<table border="1">
<tr>
<td>排行</td>
<td>專輯ID</td>
<td>專輯名稱</td>
<td>創作人</td>
<td>類別</td>
<td>發佈日期</td>
<td>月試聽數</td>
<td>月購買數</td>
<td>fb分享數</td>
<td>月總分</td>
<td>月CP值</td>
<td>合計</td>
<td>修改</td>
</tr>
<c:forEach var="list" varStatus="status" items="${arAlbum}">
<tr>
<td>${status.count}</td>
<td>${list[0].pid}</td>
<input type="hidden" name="albumID" id="albumID" value="${list[0].pid}"/>
<td><a href="queryAlbumData.do?albumid=${list[0].pid}" >${list[0].name}</a></td>
<td><a href="creatorProfile.do?creatorID=${list[0].creator.id }">${list[0].creator.userName }</a></td>
<td>${list[0].musicCategory.name}</td>
<td>
<fmt:parseDate var="dateObj" value="${list[0].createDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />
</td>
<td>${list[1]}</td>
<td>${list[2]}</td>
<td>0</td>
<td>${list[1]+list[2]}</td>
<td>
<input type="hidden" name="originalTotal" id="originalTotal_${status.count}" value="${list[1]+list[2]}"/>
<td><input type="text" id="cpPoint_${status.count}" name="cpPoint_${status.count}" value="0" size="5" onblur="cal('${status.count}')"></td>
<td><div id="tPrice_${status.count}" ></div></td>
<td><input type="button" name="111" value="更新" onclick="updateCP('${status.count}');"></td>
</tr>
</c:forEach>
<input type="hidden" name="adminID" id="adminID" value="${adminID}"/>
</table>
</c:if>
</form>
</body>

<script type="text/javascript">
function onSubmit(){
	document.qForm.action="queryAlbumMonthRankingForAdmin.do";
	document.qForm.submit();
}

function cal(index){
	var p1 = $('#originalTotal_'+index).val();
	var p2 = $('#cpPoint_'+index).val();
	
	$('#tPrice_'+index).html(eval(p1)+eval(p2));
}

function updateCP(count,adminID,albumID){
    var cpPoint = $("#cpPoint_"+count).val();
    var adminID = $("#adminID").val();
    var albumID = $("#albumID").val();
    if(cpPoint==''){
      alert('請填入CP值');
      return;
    }
	$.ajax({
	    url: 'updateAlbumMonthCP.do',
	    type: 'POST',
	    data: {"cpPoint":cpPoint,"adminID":adminID,"albumID":albumID},
	    error: function(xhr) {},
	    success: function(response) {
	    	alert('更新成功');
		}
	});
}
</script>

</html>