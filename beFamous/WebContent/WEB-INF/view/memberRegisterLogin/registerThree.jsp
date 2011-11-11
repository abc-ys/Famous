<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<td Width="200" Height="30"><img alt=""
			src=${pageContext.request.contextPath}/images/001.jpg width="100"
			height="35"></td>
		<td Width="200" Height="30"><img alt=""
			src=${pageContext.request.contextPath}/images/002.jpg width="100"
			height="35"></td>
		<td Width="200" Height="30"><img alt=""
			src=${pageContext.request.contextPath}/images/003.jpg width="100"
			height="35"></td>
	</table>
	<h3>註冊完成，請到您所填寫的信箱收取認證信</h3>
	<form name="fm" method="post">
		<table border="0" cellpadding="0" cellspacing="0">			
			<tr>
			<td><img alt=""
				src=${pageContext.request.contextPath}/${member.picture} width="100"
				height="55"></td>
			<td>${member.userName}<br>${member.email}
			</td>
			<tr>
			<td><c:out value="發現好音樂不用四處跑"></c:out><br> <input
				type="button" value="購買GSiSD卡" onclick="buySD()" /></td>
			<tr>
			<td><c:out value="販售音樂，從這裡開始"></c:out><br> <input
				type="button" value="上傳音樂" onclick="uploadMusic()" /></td>
				
		</table>
	</form>
</body>
<script>
	function buySD() {
		document.fm.action = "${pageContext.request.contextPath}/sdCard.do";
		document.fm.submit();
	}
	function uploadMusic() {
		document.fm.action = "${pageContext.request.contextPath}/addAlbum.do";
		document.fm.submit();
	}
</script>
</html>