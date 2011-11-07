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
<h3>系統自動隱藏區<h3>
<table width="500"> 
	<div id="navi" class="navigation">
   		<td colspan=2>
			 <a href="#" title="hiddenAlbum" onClick="display('hiddenAlbum')">專輯</a>
			 <a href="#" title="hiddenSong" onClick="display('hiddenSong')">歌曲</a>
		</td>
	</div>
	<tr>
 	<tr>
 	<td height="1000" width="740"> 		
	 	<div id="hiddenAlbum">
	 		 <br>
	 		 <iframe name="hiddenAlbum" src="${pageContext.request.contextPath}/queryHiddenAlbum.do" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>	
	 	<div id="hiddenSong" style="display:none">
	 		<iframe name="hiddenSong" src="${pageContext.request.contextPath}/queryHiddenSong.do" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>		
 	</td>
 	</table>
</body>
<script type="text/javascript">
function display(divname){
	if(divname=='hiddenAlbum'){
		$("#hiddenAlbum").toggle();
		$("#hiddenSong").hide();
	}
	if(divname=='hiddenSong'){
		$("#hiddenSong").toggle();
		$("#hiddenAlbum").hide();
	}
}
</script>
</html>