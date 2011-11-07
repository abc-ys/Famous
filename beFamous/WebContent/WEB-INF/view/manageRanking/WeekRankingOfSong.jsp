<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<p>修改歌曲週榜</p>

<form action="" name="qForm" method="post">
歌曲名稱:<input type="text" name="songName"><br>
專輯名稱:<input type="text" name="albumName"><br>
創作人:<input type="text" name="creatorName"><br>
類別:<input type="checkbox" name="musicCategory" value="aa">搖滾樂
     <input type="checkbox" name="musicCategory" value="bb">電子樂<br>
上架時間:<input type="text" name="startDate">-<input type="text" name="endDate"><br>

<input type="button" name="111" value="查詢" onclick="onSubmit()">



<c:if test="${arSong!=null}">
<table border="1">
<tr>
<td>排行</td>
<td>歌曲ID</td>
<td>歌曲名稱</td>
<td>專輯名稱</td>
<td>創作人</td>
<td>類別</td>
<td>發佈日期</td>
<td>週試聽數</td>
<td>週購買數</td>
<td>週總分</td>
<td>週CP值</td>
<td>合計</td>
<td>修改</td>
</tr>
<c:forEach var="arSong" varStatus="status" items="${arSong}">
<tr>
<td>${status.count}</td>
<td>${arSong.songID}</td>
<td>${arSong.name}</td>
<td><a href="${pageContext.request.contextPath}/queryAlbumData.do?albumid=${arSong.album.albumID}" >${arSong.album.name}</a></td>
<td><a href="${pageContext.request.contextPath}/creatorProfile.do?creatorID=${arSong.album.creator.memberId }">${arSong.album.creator.accountName }</a></td>
<td>${arSong.musicCategory.name }</td>
<td>${arSong.date}</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>
<input type="text" id="cpPoint_${status.count}" name="cpPoint_${status.count}" value="${arSong.cp.modifyValue}">
<input type="hidden" id="cpId_${status.count}" name="cpId_${status.count}" value="${arSong.cp.cpID}">
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
	document.qForm.action="${pageContext.request.contextPath}/querySongWeekRankingForAdmin.do";
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
	    url: '${pageContext.request.contextPath}/updateSongWeekCP.do',
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