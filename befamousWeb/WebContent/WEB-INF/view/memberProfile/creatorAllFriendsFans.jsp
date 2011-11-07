<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table width="730" border="0">
	<h4>好友圈(${fn:length(friendList)})</h4>
	<c:forEach var="hm" items="${friendList}">
 	<td><img alt="" src=${hm.picture}  width="50"　height="20"></img><br><a href="javascript:void(0)" onclick="identity('${hm.identityName}','${hm.memberId}')">${hm.userName}</a></td>
 	</c:forEach> 
</table>
<br>
<table width="730" border="0">
	<h4>粉絲圈(${fn:length(fanList)})</h4>
	<c:forEach var="hm2" items="${fanList}">
	<td><img alt="" src=${hm2.picture}  width="50"　height="20"></img><br><a href="javascript:void(0)" onclick="identity('${hm2.identityName}','${hm2.memberId}')">${hm2.userName}</a></td>
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
</script>
</html>