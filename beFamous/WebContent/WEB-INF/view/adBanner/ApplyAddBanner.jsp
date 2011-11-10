<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
 申請廣告刊登
 
 <form name="pdfForm" action="${pageContext.request.contextPath}/saveBanner.do" enctype="multipart/form-data" method="post">
 <p>會員帳號:<input type="text" name="memberID" value="${memberID}">
 </p>
 <p>banner類別:
 <select name="dep">
 <c:forEach items="${arAdType}" var="adType">
<option value="${adType.adTypeName}">${adType.adTypeName}</option>
</c:forEach>
</select>
</p>
 <p>活動名稱:<input type="text" name="activityName" ></p>
 <p>檔案:<input type="file" name="uploadFile" size="20" ></p>
 <p>活動期間<input type="text" name="activityStartDate" > - <input type="text" name="activityEndDate" ></p>
 <p>連結網址:<input type="text" name="website" ></p>
 <p>建立日期:<input type="text" name="createDate" ></p>
 <p>活動說明:<input type="text" name="activityContent" ></p>
 
 
 <input type="button" name="111" value="送出" onclick="onSubmit();">
 </form>
 
</body>
<script type="text/javascript">

function modifyMemberId(memberId){
	window.open("${pageContext.request.contextPath}/modifyMemberId.do?memberId="+memberId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}

function onSubmit(){
	document.pdfForm.submit();
}

</script>
</html>