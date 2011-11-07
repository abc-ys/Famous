<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h4>喜歡創作人列表</h4>
	<table name=creators>
		<c:forEach var="hm" items="${creators}">
	 	<td><img alt="" src=${hm.picture}  width="50"　height="20"></img></td>
	 	<td width="100"><a href="javascript:void(0)" onclick="identity('${hm.identityName}','${hm.memberId}')">${hm.userName}</a></td>
	 	<td><input type="button" value="刪除" onclick="confirmDelCreator('${memberID}','${hm.memberId}','${hm.userName}')"></td><tr>
	 	</c:forEach> 
	</table>
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
function confirmDelCreator(memberId,creatorId,creatorName){
	window.open("${pageContext.request.contextPath}/confirmDelCreator.do?memberID="+memberId+"&creatorID="+creatorId+"&creatorName="+creatorName,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');
}
</script>
</html>