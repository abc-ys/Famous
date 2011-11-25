<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h4>好友列表</h4>
	<table name=unfriends>
		<h4>待確認清單</h4>
		<c:forEach var="hm" items="${unfriends}">
	 	<td><img alt="" src="/${initParam.ImageWeb}/${hm.friend.picture}"  width="50"　height="20"></img></td>
	 	<td width="100"><a href="javascript:void(0)" onclick="identity('${hm.friend.identityName}','${hm.friend.id}')">${hm.friend.userName}</a></td>
	 	<td><input type="button" value="接受邀請" onclick="acceptInvite('${userID}','${hm.friend.id}')"> &nbsp;&nbsp; <input type="button" value="忽略" onclick="refuseInvite('${userID}','${hm.friend.id}')"></td>
		</c:forEach> 
	</table>
	<hr />
	<div id="myFriendIframe" style="display:block">
	 		<iframe name="myFriendIframe" src="${pageContext.request.contextPath}/myFriendIframe.do?userID=${userID}" height="1000" width="740" frameborder="0"></iframe> 
	</div>	
	
</body>
<script type="text/javascript">
function identity(identityName,memberId)
{
	if(identityName == '一般會員'){
		location.href="${pageContext.request.contextPath}/memberProfile.do?memberID="+memberId;		
	}else{
		location.href="${pageContext.request.contextPath}/creatorProfile.do?memberID="+memberId;
	}
}
function acceptInvite(memberId,friendId){
	location.href="${pageContext.request.contextPath}/acceptFriend.do?userID="+memberId+"&friendID="+friendId;
}
function refuseInvite(memberId,friendId){
	location.href="${pageContext.request.contextPath}/refuseFriend.do?userID="+memberId+"&friendID="+friendId;
}
</script>
</html>