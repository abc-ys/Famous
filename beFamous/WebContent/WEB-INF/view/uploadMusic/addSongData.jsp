<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
	<p class="topic">曲目已上傳成功，請填寫歌曲資訊</p>
		<form name="form" method="post">
		<table border=0>
			<input type="hidden" name="songId" value="${songID}">
			<input type="hidden" name="creatorId" value="${creatorId}">
			<input type="hidden" name="albumId" value="${albumId}">
			<tr>
				<td width=400>歌曲名稱:&nbsp;<input type= "text" name="name" id="name"></td>
				<td width=400>發行日期:&nbsp;<input type= "text" name="date" readonly value=<fmt:formatDate value="<%=new java.util.Date() %>" pattern ="yyyy-MM-dd"/>></td>
			</tr>
			<tr>
				<td width=400>音樂類型&nbsp;
					<select name="musicCategory" id="musicCategory">
						<c:forEach var="hm" items="${mType}">
						<option value="${hm.id}">${hm.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width=400>試聽狀態:&nbsp; 
				<input type="radio" name="status" id="status" value="1">全曲
				<input type="radio" name="status" id="status" value="2">90秒
				<input type="radio" name="status" id="status" value="3">30秒
				</td>
			</tr>
			<tr>
				<td width=400>價格:&nbsp; 
				<input type= "radio" name="price" id="price" value= "1">定價&nbsp;NT&nbsp;<input type= "text" name="price2" size=5>元
				<input type= "radio" name="price" id="price" value= "2">僅供試聽，不准下載
				</td>
			</tr>
			<tr>
				<td width=400>紅包打賞</td>
			</tr>
			<tr>
				<td width=400><input type= "radio" name= "discount" id="discount" value= "1">小紅包5元</td>
			</tr>
			<tr>
				<td width=400><input type= "radio" name= "discount" id="discount" value= "2">大紅包15元</td>
			</tr>
			<tr>
				<td width=400><input type= "radio" name= "discount" id="discount" value= "3">不提供打賞</td>
			</tr>
			<tr>
				<td width=400>標籤:&nbsp;<input type= "text" name="tag" id="tag"></td>
			</tr>
			<tr>
				<td width=400><input type='button' value='完成' onclick="saveSongData()"></td>
			</tr>
			<p>
			
		</table></form>
</body>
<script type="text/javascript">
function saveSongData(){
	if($("#name").val() == ""){
		alert('請輸入歌曲名稱');
	}else if($("#tag").val() == ""){
		alert('請填寫和此專輯相關的標籤，增加被搜尋到機會。');
	}else{
		document.form.action="${pageContext.request.contextPath}/saveSongData.do";	
		document.form.submit();	
	}
}

</script>
</html>