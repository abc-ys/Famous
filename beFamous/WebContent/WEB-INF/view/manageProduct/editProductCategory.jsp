<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
類別名稱編輯
<p>
<font size="2">&nbsp;名稱:</font>&nbsp;
<input type="text" name="productionClassificationName" size="15" value="${productionClassification.name}">
<input type="hidden" name="productionClassificationId" size="15" value="${productionClassification.id}" >
<input type="hidden" name="adminId" size="15" value="${adminId}" >
<input type="submit" value="修改" onclick="add()"/>
</form>
</body>
<script>
function add(){
     document.fm.action="${pageContext.request.contextPath}/editProductCategoryClose.do";
     document.fm.submit();
}
</script>
</html>