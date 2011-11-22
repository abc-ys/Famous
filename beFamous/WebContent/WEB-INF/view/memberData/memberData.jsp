<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h4>會員資料編輯</h4>
<form name="form" method="post">
	<input type="hidden" name="userID" value="${member[0].id}">	
	<img alt="" src="/${initParam.ImageWeb}/${member[0].picture}" width="200" height="100"><br><a href="javascript:void(0)" onclick="uploadMemberPicture(${member[0].id})">上傳圖片</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/deleteMemberPicture.do?userID=${member[0].id}">刪除</a>
	<input type="hidden" name="picture" value=${member[0].picture}>
	<input type="hidden" name="email" value=${member[0].email}>
	<input type="hidden" name="password" value=${member[0].password}>
	<br><br>
	身份:&nbsp;
	<c:if test="${member[0].identityName==1}">
		<INPUT type=radio name=identityName value="1" checked>一般聽眾
		<INPUT type=radio name=identityName value="2">創作人
		<INPUT type=radio name=identityName value="3">樂團
	</c:if>
	<c:if test="${member[0].identityName==2}">
		<INPUT type=radio name=identityName value="2" checked>創作人
		<INPUT type=radio name=identityName value="3">樂團
	</c:if>
	<c:if test="${member[0].identityName==3}">
		<INPUT type=radio name=identityName value="2">創作人
		<INPUT type=radio name=identityName value="3" checked>樂團
	</c:if>
	<br><br>
	Email:&nbsp;${member[0].email}<input type="button" value="變更email" onclick="modifyEmail('${member[0].id}','${member[0].password}')"><br><br>
	使用者名稱:&nbsp; <input type="text" name="userName" value=${member[0].userName}><br><br>
	密碼:&nbsp; <input type="button" value="變更密碼" onclick="modifyPassword('${member[0].id}','${member[0].password}')"><br><br>
	性別:&nbsp;
	<c:if test="${empty member[0].sex}">
		<INPUT type=radio name=sex value="1">男
		<INPUT type=radio name=sex value="2">女
	</c:if>
	<c:if test="${member[0].sex==1}">
		<INPUT type=radio name=sex value="1" checked>男
		<INPUT type=radio name=sex value="2">女
	</c:if>
	<c:if test="${member[0].sex==2}">
		<INPUT type=radio name=sex value="1" >男
		<INPUT type=radio name=sex value="2" checked>女
	</c:if>
	<br><br>
	生日:&nbsp; <input type="text" name="birthday" value=${member[0].birthday}><br><br>
	居住地:&nbsp; <input type="text" name="location" value=${member[0].location}><br><br>
	城市: &nbsp;<input type="text" name="city" value=${member[0].city}><br><br>
	官方網站:&nbsp; <input type="text" name="webSite" value=${member[0].webSite}><br><br>
	簡介:&nbsp; <textarea rows="6" cols="40" name="introduction">${member[0].introduction}</textarea><br><br>
	是否訂閱新訊息:&nbsp;
	<c:if test="${empty member[0].subscribeStatus}">
		<INPUT type=radio name=subscribeStatus value="1">是
		<INPUT type=radio name=subscribeStatus value="2">否
	</c:if>
	<c:if test="${member[0].subscribeStatus==1}">
		<INPUT type=radio name=subscribeStatus value="1" checked>是
		<INPUT type=radio name=subscribeStatus value="2">否
	</c:if>
	<c:if test="${member[0].subscribeStatus==2}">
		<INPUT type=radio name=subscribeStatus value="1" >是
		<INPUT type=radio name=subscribeStatus value="2" checked>否
	</c:if>
	<br>			
	<h4>以下建議創作人填寫</h4>
	喜歡的音樂類型:&nbsp;<br><br>
	<input type="checkbox" name="likeMusicType" value="Rock & roll 搖滾樂">Rock & roll 搖滾樂
	<input type="checkbox" name="likeMusicType" value="Electronic 電子音樂">Electronic 電子音樂
	<input type="checkbox" name="likeMusicType" value="Pop 流行音樂">Pop 流行音樂
	<input type="checkbox" name="likeMusicType" value="Hip-Hop 嘻哈音樂">Hip-Hop 嘻哈音樂<br><br>
	<input type="checkbox" name="likeMusicType" value="R&B 節奏藍調">R&B 節奏藍調
	<input type="checkbox" name="likeMusicType" value="Jazz 爵士樂">Jazz 爵士樂
	<input type="checkbox" name="likeMusicType" value="World music世界音樂">World music世界音樂
	<input type="checkbox" name="likeMusicType" value="Folk 民歌">Folk 民歌<br><br>
	<input type="checkbox" name="likeMusicType" value="Classical 古典音樂">Classical 古典音樂
	<input type="checkbox" name="likeMusicType" value="New Age music 新世紀音樂">New Age music 新世紀音樂
	<input type="checkbox" name="likeMusicType" value="Kids songs兒歌">Kids songs兒歌<br><br>
	<input type="hidden" name="likeMusicTypes" >
	<h4>喜歡的音樂人:&nbsp;</h4><br>
	請提供你所欣賞的1-3名，如果你喜歡的音樂人和消費者所喜歡的相同，則會提高他對你作品的興趣喔!<br><br>	
	你喜歡的音樂人1:	&nbsp;<input type="text" name="likeSinger1"><br><br>
	你喜歡的音樂人2:	&nbsp;<input type="text" name="likeSinger2"><br><br>
	你喜歡的音樂人3:	&nbsp;<input type="text" name="likeSinger3"><br><br>
	<input type="hidden" name="likeSingers" >
	<input type="button" value="確定修改" onclick="saveMemberData()">
</form>
</body>
<script type="text/javascript">
function modifyEmail(userID){
	window.open("${pageContext.request.contextPath}/modifyEmail.do?userID="+userID,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}

function modifyPassword(userID){
	window.open("${pageContext.request.contextPath}/modifyPassword.do?userID="+userID,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function uploadMemberPicture(userID){
	window.open("${pageContext.request.contextPath}/uploadMemberPicture.do?userID="+userID,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function saveMemberData(){
	
	var musicType = new Array();
	$('input:checkbox:checked[name="likeMusicType"]').each(function(i) { musicType[i] = this.value; });	
	var likeMusicTypes = musicType.toString();

	var likeSingers = $('input[name="likeSinger1"]').val()+','+$('input[name="likeSinger2"]').val()+','+$('input[name="likeSinger3"]').val();
	
	document.form.likeMusicTypes.value = likeMusicTypes;
	document.form.likeSingers.value = likeSingers;
	document.form.action="${pageContext.request.contextPath}/saveMemberData.do";
	document.form.submit();
}
</script>
</html>