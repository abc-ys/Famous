<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
類別:<input type="checkbox" name="musicCategory" value="aa">搖滾樂
     <input type="checkbox" name="musicCategory" value="bb">電子樂<br>
上架時間:<input type="text" name="startDate">-<input type="text" name="endDate"><br>

<input type="button" name="111" value="查詢" onclick="onSubmit()">



<c:if test="${arAlbum!=null}">
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
<c:forEach var="arAlbum" varStatus="status" items="${arAlbum}">
<tr>
<td>${status.count}</td>
<td>${arAlbum.albumID}</td>
<td><a href="${pageContext.request.contextPath}/queryAlbumData.do?albumid=${arAlbum.albumID}" >${arAlbum.name}</a></td>
<td><a href="${pageContext.request.contextPath}/creatorProfile.do?creatorID=${arAlbum.creator.memberId }">${arAlbum.creator.accountName }</a></td>
<td>${arAlbum.musicCategory.name }</td>
<td>${arAlbum.date}</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>
<input type="text" id="cpPoint_${status.count}" name="cpPoint_${status.count}" value="${arAlbum.cp.modifyValue}">
<input type="hidden" id="cpId_${status.count}" name="cpId_${status.count}" value="${arAlbum.cp.cpID}">
</td>
<td>0</td>
<td><input type="button" name="111" value="更新" onclick="updateCP('${status.count}');"></td>
</tr>
</c:forEach>
</table>
</c:if>
</form>
</body>

<script type="text/javascript">
function onSubmit(){
	document.qForm.action="${pageContext.request.contextPath}/queryAlbumMonthRankingForAdmin.do";
	document.qForm.submit();
}

function updateCP(count){
    var cpPoint = $("#cpPoint_"+count).val();
    var cpId    = $("#cpId_"+count).val();
    if(cpPoint==''){
      alert('請填入CP值');
      return;
    }
	$.ajax({
	    url: '${pageContext.request.contextPath}/updateAlbumMonthCP.do',
	    type: 'POST',
	    data: {cpPoint:cpPoint,cpId:cpId},
	    error: function(xhr) {},
	    success: function(response) {
	    	alert('更新成功');
		}
	});
}
</script>

</html>