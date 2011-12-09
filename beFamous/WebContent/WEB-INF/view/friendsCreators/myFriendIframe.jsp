<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<table name=friends>
		<c:forEach var="hm2" items="${friends}">
	 	<td><img alt="" src="/${initParam.ImageWeb}/${hm2.friend.picture}"  width="50"　height="20"></img></td>
	 	<td width="100"><a href="javascript:void(0)" onclick="identity('${hm2.friend.identityName}','${hm2.friend.id}')">${hm2.friend.userName}</a></td>
	 	<td><input type="button" value="刪除" onclick="confirmDelFriend('${userID}','${hm2.friend.id}')"></td><tr>
	 	</c:forEach> 
	</table>
</body>
<script type="text/javascript">
function identity(identityName,memberId)
{
	if(identityName == '一般會員'){
		top.location.href ="${pageContext.request.contextPath}/memberProfile.do?memberID="+memberId;		
	}else{
		top.location.href ="${pageContext.request.contextPath}/creatorProfile.do?memberID="+memberId;
	}
}
function confirmDelFriend(memberId,friendId){
	window.open("${pageContext.request.contextPath}/confirmDelFriend.do?userID="+memberId+"&friendID="+friendId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');
}
</script>
</html>