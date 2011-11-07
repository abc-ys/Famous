<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
${fname}:我們不在是朋友了嗎?<br>
<input type="button" value="不是朋友" onclick="saveDelFriend('${memberID}','${fid}')">
<input type="button" value="還是朋友" onclick="window.close()">
</body>
<script type="text/javascript">
function saveDelFriend(memberId,friendId){	
	location.href="${pageContext.request.contextPath}/saveDelFriend.do?memberID="+memberId+"friendID="+friendId;
}
</script>
</html>