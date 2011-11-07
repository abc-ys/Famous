<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<p>
訂單備註1&nbsp&nbsp備註訂購者可見<br>
<textarea cols="60" rows="6" name="memo1"></textarea> 
<p>
訂單備註2&nbsp&nbsp備註只有管理者可見<br>
<textarea cols="60" rows="6" name="memo2"></textarea> 

<center><input type="submit" value="儲存" onclick="saveData()"/></center>
</form>
</body>
<script>
function saveData(){
    document.fm.action="${pageContext.request.contextPath}/editClose.do";
    document.fm.submit();
}
</script>
</html>