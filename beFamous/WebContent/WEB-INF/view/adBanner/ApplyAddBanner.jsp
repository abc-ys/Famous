<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
 申請廣告刊登
 
 <form name="adForm" action="${pageContext.request.contextPath}/saveBanner.do" enctype="multipart/form-data" method="post">
 <p>會員帳號:<input type="text" name="memberID" disabled value="${memberID}">
   <!--  <input type="button" value="變更帳號" onclick="modifyMemberId('${memberID}')">-->
 </p>
 <p>banner類別:
 <select name="dep">
 <c:forEach items="${arAdType}" var="adType">
<option value="${adType.id}">${adType.adTypeName}</option>
</c:forEach>
</select>
</p>
 <p>活動名稱:<input type="text" name="activityName" ></p>
 <p>檔案:<input type="file" name="uploadFile" size="20" ></p>
 <p>活動期間<input type="text" name="activityStartDate" > &nbsp;<A HREF="javascript:show_calendar('adForm.activityStartDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a> - 
 <input type="text" name="activityEndDate" > &nbsp;<A HREF="javascript:show_calendar('adForm.activityEndDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a></p>
 <p>連結網址:<input type="text" name="website" ></p>
 <p>建立日期:<input type="text" name="createDate" class="fillbox" readonly value=<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/> disabled></p>
 <p>活動說明:<input type="text" name="activityContent" ></p>
 
 
 <input type="button" name="111" value="送出" onclick="onSubmit();">
 </form>
 
</body>
<script type="text/javascript">

function modifyMemberId(memberId){
	window.open("${pageContext.request.contextPath}/modifyMemberId.do?memberId="+memberId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}

function onSubmit(){
	document.adForm.submit();
}

</script>
</html>