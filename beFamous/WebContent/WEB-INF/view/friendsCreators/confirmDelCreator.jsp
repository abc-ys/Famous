<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
${cname}你不再支持我了嗎?<br>
<input type="button" value="對，不想" onclick="saveDelCreator('${memberID}','${cid}')">
<input type="button" value="繼續支持" onclick="window.close()">
</body>
<script type="text/javascript">
function saveDelCreator(memberId,creatorId){	
	location.href="${pageContext.request.contextPath}/saveDelCreator.do?memberID="+memberId+"creatorID="+creatorId;
}
</script>
</html>