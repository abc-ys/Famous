<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
</head>
<body>

<h1>查看推薦專輯</h1>

<form action="" name="qForm" method="post">
推薦主題:${activity.title} <br></br>
推薦期間:
<fmt:parseDate var="dateObj" value="${activity.startDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font>&nbsp;-&nbsp;
<fmt:parseDate var="dateObj" value="${activity.endDate}" type="DATE" pattern="yyyyMMddHHmmss"/> 
<font size="2"><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' /></font><br></br>

<c:if test="${fn:length(activity.albumSet)>0}">
<div id="a_album">
專輯:
<table id="albumTable" border="1">
<tr><td> 專輯ID </td> <td> 專輯名稱 </td> <td> 創作人 </td></tr>
<c:forEach var="albumSet" items="${activity.albumSet}" >
<tr id="${albumSet.pid}"><td> ${albumSet.pid} </td> <td>  ${albumSet.name} </td> <td>  ${albumSet.creator.userName} </td></tr>
</c:forEach>
</table>
</div>
</c:if>

活動狀態: <c:if test="${activity.status==1}">到期自動開啟</c:if>
          <c:if test="${activity.status==3}">隱藏</c:if>
        
<br>
</form>
</body>
</html>