<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h4>會員資料編輯</h4>
<form name="form" method="post">	
	<img alt="" src=${member.picture} width="200"　height="100" ><br><a href="javascript:void(0)" onclick="uploadMemberPicture(${member.memberId})">上傳圖片</a>&nbsp&nbsp<a href="${pageContext.request.contextPath}/deleteMemberPicture.do?memberID=${member.memberId}">刪除</a>
	<input type="hidden" name="picture" value=${member.picture}>
	<input type="hidden" name="email" value=${member.email}>
	<input type="hidden" name="password" value=${member.password}>
	<br><br>
	身份:<input type="text" name="identityName" value=${member.identityName}><br><br>
	Email:${member.email}<input type="button" value="變更email" onclick="modifyEmail('${member.memberId}','${member.password}')"><br><br>
	使用者名稱: <input type="text" name="userName" value=${member.userName}><br><br>
	密碼: <input type="password" value=${member.password}> <input type="button" value="變更密碼" onclick="modifyPassword('${member.memberId}','${member.password}')"><br><br>
	性別:<input type="text" name="sex" value=${member.sex}><br><br>
	生日: <input type="text" name="birthday" value=${member.birthday}><br><br>
	居住地: <input type="text" name="location" value=${member.location}><br><br>
	城市: <input type="text" name="city" value=${member.city}><br><br>
	官方網站: <input type="text" name="webSite" value=${member.webSite}><br><br>
	簡介: <textarea rows="6" cols="40" name="introduction">${member.introduction}</textarea><br><br>
	是否訂閱新訊息:<input type="text" name="subscribeStatus"  value=${member.subscribeStatus}><br>			
	<h4>以下建議創作人填寫</h4>
	喜歡的音樂類型:<br><br>
	<input type="text" name="likeMusicType" value=${member.creator.likeMusicType}><br><br>
	
	<h4>喜歡的音樂人:</h4><br>
	請提供你所欣賞的1-3名，如果你喜歡的音樂人和消費者所喜歡的相同，則會提高他對你作品的興趣喔!<br><br>	
	你喜歡的音樂人:	<input type="text" name="likeSinger" value=${member.creator.likeSinger}><br><br>
	
	<input type="button" value="確定修改" onclick="saveMemberData()">
</form>
</body>
<script type="text/javascript">
function modifyEmail(memberId,password){
	window.open("${pageContext.request.contextPath}/modifyEmail.do?password="+password,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}

function modifyPassword(memberId,password){
	window.open("${pageContext.request.contextPath}/modifyPassword.do?password="+password,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function uploadMemberPicture(memberId){
	window.open("${pageContext.request.contextPath}/uploadMemberPicture.do?memberId="+memberId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
function saveMemberData(){
	document.form.action="${pageContext.request.contextPath}/saveMemberData.do";
	document.form.submit();
}
</script>
</html>