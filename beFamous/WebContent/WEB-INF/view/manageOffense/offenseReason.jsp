<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
專輯/歌曲檢舉清單
<p>
<form name="fm" method="post">
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="30" Height="35" valign="top"><font size="2">序號</font></center></td>
	<td valign="top" Width="140"><center><font size="2">檢舉會員</font></center></td>
	<td valign="top" Width="110"><center><font size="2">檢舉日期</font></center></td>
	<td valign="top" Width="140"><center><font size="2">原因</font></center></td>
	<td valign="top" Width="60"><center><font size="2">不當檢舉</font></center></td></tr>
	
	<c:forEach var="hm" items="${offenseReason}" varStatus="status"><c:if test="${empty hm.modifyDate}">	
	<tr><td Height="35"><center><font size="2">${status.index+1}</font></center></td>
		<td><center><font size="2"><a href="javascript:identity('2','${hm.member.id}','${hm.member.identityName}')">${hm.member.email}</a></font></center></td>
		<fmt:parseDate var="dateObj" value="${hm.createDate}" type="DATE" pattern="yyyyMMddhhmmss"/> 
		<td><center><font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font></center></td>
		<td><font size="2">${hm.reason}</font></td>
		<td><center><INPUT type="submit" name="offenseWrong" value="不當檢舉" onclick="check('2','${hm.id}');"/></center></td><tr>
	</c:if></c:forEach>
</table>
<INPUT type="hidden" name="productionCategoryId" value="${productionCategoryId}">
<INPUT type="hidden" name="adminId" value="2">
</form>
</body>
<script>
function identity(adminId,memberId,identityName){	
	if(identityName == '一般會員'){
		window.open("${pageContext.request.contextPath}/manageGMemberDetail/get/"+adminId+"/"+memberId+".do");
	}else{
		window.open("${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do");
	}
}
function check(adminId,offenseId){
	document.fm.action="${pageContext.request.contextPath}/offenseWrong.do?offenseId="+offenseId;
	document.fm.submit();
}
</script>
</html>