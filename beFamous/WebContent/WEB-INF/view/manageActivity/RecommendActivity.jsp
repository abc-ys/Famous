<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>

<h1>查看推薦專輯</h1>

<form action="" name="qForm" method="post">
推薦主題:${activity.title} <br></br>
推薦期間:${activity.startDate} - ${activity.endDate}<br></br>


<c:if test="${fn:length(activity.albumSet)>0}">
<div id="a_album">
專輯:
<table id="albumTable" border="1">
<tr><td> 專輯ID </td> <td> 專輯名稱 </td> <td> 創作人 </td></tr>
<c:forEach var="albumSet" items="${activity.albumSet}" >
<tr id="${albumSet.albumID}"><td> ${albumSet.albumID} </td> <td>  ${albumSet.name} </td> <td>  ${albumSet.creator.userName} </td></tr>
</c:forEach>
</table>
</div>
</c:if>



<c:if test="${fn:length(activity.songSet)>0}">
<div id="a_song">
歌曲:
<table id="songTable" border="1">
<tr><td> 歌曲ID </td> <td> 專輯名稱 </td> <td> 歌曲名稱 </td> <td> 創作人 </td></tr>
<c:forEach var="songSet" items="${activity.songSet}" >
<tr id="${songSet.songID}"><td>${songSet.songID}</td><td> ${songSet.album.name} </td> <td>  ${songSet.name} </td> <td>  ${songSet.album.creator.userName} </td></tr>
</c:forEach>
</table>

</div>
</c:if>


活動狀態: <c:if test="${activity.status==0}">到期自動開啟</c:if>
          <c:if test="${activity.status==1}">隱藏</c:if>
        
<br>


</form>


</body>

</html>