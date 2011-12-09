<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gsimedia" uri="http://www.gsi-media.com/" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/cell.css" type="text/css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/js/cell.js"></script>
</head>
<body>

分類名稱:

<br><br>
<form action="" name="qform" method="POST">


<a href="javascript: void(0)" onClick="goSubmit('a01','${categoryID}');">最新發佈</a>
<a href="javascript: void(0)" onClick="goSubmit('a02','${categoryID}');">最受歡迎</a>




<table>
   <gsimedia:albumTable listName="arAlbum"></gsimedia:albumTable>
</table>
</form>
</body>

<script type="text/javascript">
function goSubmit(condition,categoryID){
	if(condition=='a01'){
      document.qform.action='${pageContext.request.contextPath}/queryNewAlbumsForMusicCategory/'+categoryID+'.do';
      document.qform.submit();
	}
	if(condition=='a02'){
	      document.qform.action='${pageContext.request.contextPath}/queryHotAlbumsForMusicCategory/'+categoryID+'.do';
	      document.qform.submit();
	}
	
}


</script>


</html>