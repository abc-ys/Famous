<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<tr><td Width="30" Height="35" valign="top"><font size="2">序號</font></td>
	<td valign="top" Width="140"><font size="2">檢舉會員</font></td>
	<td valign="top" Width="110"><font size="2">檢舉日期</font></td>
	<td valign="top" Width="140"><font size="2">原因</font></td>
	<td valign="top" Width="60"><font size="2">不當檢舉</font></td></tr>
	
	<c:forEach var="hm" items="${offenses.offense}" varStatus="status">
<tr><td Height="35"><font size="2">${status.index+1}</font></td>
	<td><font size="2"><a href="javascript:creator('${hm.member.memberId}')">${hm.member.email}</a></font></td>
	<td><font size="2">${hm.createDate}</font></td>
	<td><font size="2">${hm.reason}</font></td>
	<td><INPUT type="submit" name="offenseWrong" value="確定為不當檢舉" onclick="aaa('${hm.member.memberId}','${hm.offenseRid}');"/></td><tr>
	</c:forEach>
</table>


</form>
</body>
<script>
function creator(memberId){
	location.href="${pageContext.request.contextPath}/manageCreatorDetail.do?memberId="+memberId;
}
function aaa(memberId,offenseId){
	document.fm.action="${pageContext.request.contextPath}/offenseWrong.do?memberId="+memberId+"&offenseId="+offenseId;
	document.fm.submit();
}
</script>
</html>