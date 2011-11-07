<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h3>被檢舉區<h3>
<table width="500"> 
	<div id="navi" class="navigation">
   		<td colspan=2>
			 <a href="#" title="offenseAlbum" onClick="display('offenseAlbum')">專輯</a>
			 <a href="#" title="offenseSong" onClick="display('offenseSong')">歌曲</a>
		</td>
	</div>
	<tr>
 	<tr>
 	<td height="1000" width="740"> 		
	 	<div id="offenseAlbum">
	 		 <br>
	 		 <iframe name="offenseAlbum" src="${pageContext.request.contextPath}/offenseAlbum.do" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>	
	 	<div id="offenseSong" style="display:none">
	 		<iframe name="offenseSong" src="${pageContext.request.contextPath}/offenseSong.do" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>		
 	</td>
 	</table>
</body>
<script type="text/javascript">
function display(divname){
	if(divname=='offenseAlbum'){
		$("#offenseAlbum").toggle();
		$("#offenseSong").hide();
	}
	if(divname=='offenseSong'){
		$("#offenseSong").toggle();
		$("#offenseAlbum").hide();
	}
}
</script>
</html>