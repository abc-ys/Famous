<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h3>隱藏區<h3>
<table width="500"> 
	<div id="navi" class="navigation">
   		<td colspan=2>
			 <a href="#" title="hiddenedAlbum" onClick="display('hiddenedAlbum')">專輯</a>
			 <a href="#" title="hiddenedSong" onClick="display('hiddenedSong')">歌曲</a>
		</td>
	</div>
	<tr>
 	<tr>
 	<td height="1000" width="740"> 		
	 	<div id="hiddenedAlbum">
	 		 <br>
	 		 <iframe name="hiddenedAlbum" src="${pageContext.request.contextPath}/hiddenedAlbum.do" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>	
	 	<div id="hiddenedSong" style="display:none">
	 		<iframe name="hiddenedSong" src="${pageContext.request.contextPath}/hiddenedSong.do" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>		
 	</td>
 	</table>
</body>
<script type="text/javascript">
function display(divname){
	if(divname=='hiddenedAlbum'){
		$("#hiddenedAlbum").toggle();
		$("#hiddenedSong").hide();
	}
	if(divname=='hiddenedSong'){
		$("#hiddenedSong").toggle();
		$("#hiddenedAlbum").hide();
	}
}
</script>
</html>