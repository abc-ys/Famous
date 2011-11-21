<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<input type="hidden" name="songID" value="${songID}">
<input type="hidden" name="creatorId" value="${creatorId}">
<table>
<td width="50%">歌曲名稱<br><input type="text" size="20" value="${song.name}" name="songName"><p>
音樂類型<br>
<select name="musicType">
<c:forEach var="hm" items="${mType}">
<option value="${hm.name}" <c:if test="${song.musicCategory.name} == ${hm.name}"> selected </c:if>>${hm.name}</option>
</c:forEach>
</select><p>
發行日期<br><input name="MOPEND" value="${song.createDate}" type="text" class="fillbox" readonly>&nbsp;
<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="images/cal.gif" border="0"></img></a><p>
試聽狀態<br>
<INPUT type=radio name=status value="1" <c:if test="${song.seconds == '1'}"> checked </c:if>>全曲
<INPUT type=radio name=status value="2" <c:if test="${song.seconds == '2'}"> checked </c:if>>90秒
<INPUT type=radio name=status value="3" <c:if test="${song.seconds == '3'}"> checked </c:if>>30秒<p>
價格<br>
<INPUT type=radio name=price value="1" <c:if test="${song.songPrice.price != ''}"> checked </c:if>>訂價&nbsp&nbspNT<input type="text" size="3" name="price2" value="${song.songPrice.price}">元&nbsp&nbsp
<INPUT type=radio name=price value="2" <c:if test="${song.songPrice.price == ''}"> checked </c:if>>僅供試聽，不給下載<br>
歌曲價格任你訂，請填入0-99的數字，最高價格為99<p>
紅包打賞<br>讓消費者以紅包價購買您的作品。感謝您優惠消費者，我們將回饋您GSiBonus點數<br>
<INPUT type=radio name=discount value="1" <c:if test="${song.songPrice.discountPrice == '1'}"> checked </c:if>>小紅包5元&nbsp&nbsp將回饋您GSiBonus 8點<br>
<INPUT type=radio name=discount value="2" <c:if test="${song.songPrice.discountPrice == '2'}"> checked </c:if>>小紅包15元&nbsp&nbsp將回饋您GSiBonus 3點<br>
<INPUT type=radio name=discount value="3" <c:if test="${song.songPrice.discountPrice == '3'}"> checked </c:if>>不提供紅包打賞<p>
標籤<br><input type="text" size="20" value="${song.tag}" name=tag><br>
請輸入與此唱片相關的標籤，並用半形逗點(,)隔開。<br>例如:流行,愛情,獨立創作等</td>

<td width="50%">
此部分雖非必填但建議填寫讓消費者更了解你的創作<p>
歌詞:<p>
<textarea cols=30 rows=10 name=lyrics>${song.lyrics}</textarea><p>
作詞者:&nbsp<input type="text" size="10" value="${song.lyricist}" name=lyricist><p>
作曲者:&nbsp<input type="text" size="10" value="${song.composer}" name=composer><p>
製作人:&nbsp<input type="text" size="10" value="${song.producer}" name=producer><p>
</td>
</table><p>
<center><input type="button" value="儲存" onclick="saveData()"/></center>
</form>
</body>
<script>
function saveData(){
	document.fm.action="${pageContext.request.contextPath}/updateSongData.do";
    document.fm.submit();
}
</script>
</html>