<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>新增推薦專輯</h1>

<form action="" name="qForm" method="post">
推薦主題:<input type="text" name="subject" ><br></br>
推薦期間:<input type="text" name="startDate" > - <input type="text" name="endDate" ><br></br>

選擇內容: <input type="radio" id="album" name="product" onclick="changeProduct('album');" checked>專輯   <input type="radio" id="song" name="product" onclick="changeProduct('song');">歌曲 

<div id="a_album">
專輯ID:<input type="text" name="albumId">  <input type="button" name="111" value="增加" onclick="addAlbum();">


</div>


<div id="a_song">
歌曲ID:<input type="text" name="songId"> 


</div>


</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	
	  
		   $("#a_album").show();
	    	$("#a_song").hide();
	 
	
});
function changeProduct(product){
    if(product=='album'){
    	$("#a_album").show();
    	$("#a_song").hide();
    }
    if(product=='song'){
    	$("#a_song").show();
    	$("#a_album").hide();
    }
}

function addAlbum(){
	var albumId = document.qForm.albumId.value;
	$.ajax({
		url: '${pageContext.request.contextPath}/queryAlbumById.do' ,
        data:{albumId:albumId},
		datatype: 'json',
		error: function(xhr) {},
		success: function(response) {
			var NumOfJData = response.length;
			alert(NumOfJData);

	    }
	});

	
}



</script>

</html>