<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<input type="hidden" name="mType" value="${mType}">
請填入隱藏理由:<p>
<textarea cols=40 rows=10 name=reason></textarea><p>
<input type="hidden" name="albumID" value="${albumID}">
<input type="hidden" name="adminID" value="${adminID}">
<input type="hidden" name="songID" value="${songID}">
<input type="hidden" name="HiddenID" value="${Hidden.id}">
<center><input type="button" value="送出"  onclick="addReason()"></center>
</form>
</body>
<script type="text/javascript">
function addReason(){   
	document.fm.action="${pageContext.request.contextPath}/addReason.do";
  	document.fm.submit();
}
</script>
</html>