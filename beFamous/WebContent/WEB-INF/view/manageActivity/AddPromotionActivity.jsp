<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
<h1>新增活動</h1>

<form action="" name="qForm" method="post">
活動名稱:<input type="text" name="title" ><br></br>
活動期間:<input type="text" name="startDate" > - <input type="text" name="endDate" ><br>

篩選條件: <br>
<input type="radio"  name="contentCondition" value="1" onclick="closeMusic('1');">儲值金額 <input type="text" id="a_money"  name="prepaidMoney" >元<br>
<input type="radio"  name="contentCondition" value="2" onclick="closeMusic('2');">儲值次數 <input type="text" id="a_count"  name="prepaidCount" >次<br>


<input type="radio" id="album" name="contentCondition" value="3" onclick="changeProduct('album');">專輯   <input type="radio" id="song" name="contentCondition" value="4" onclick="changeProduct('song');">歌曲 

<div id="a_album">
專輯ID:<input type="text" name="albumId">  <input type="button" name="111" value="增加" onclick="addAlbum();">

<table id="albumTable" border="1">
<tr><td> 專輯ID </td> <td> 專輯名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
</table>


</div>


<div id="a_song">
歌曲ID:<input type="text" name="songId"> <input type="button" name="222" value="增加" onclick="addSong();">

<table id="songTable" border="1">
<tr><td> 歌曲ID </td> <td> 專輯名稱 </td> <td> 歌曲名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
</table>

</div>
<br>

符合內容: <input type="radio"  name="condition"  value="1">符合全部所選ID<br>
          <input type="radio"  name="condition" value="2">只符合一個所選的ID<br>
          <input type="radio"  name="condition" value="3">須符合三個所選的ID<br>
          
贈送內容: GSiBonus  <br>
    <input type="radio" name="rewardCondition"  value="1"> <input type="text" id="a_rewardMoney1"  name="rewardMoney1" >元(點)<br>
    <input type="radio" name="rewardCondition"  value="2"> 訂單金額  X <input type="text" id="a_rewardMoney2"  name="rewardMoney2" >元(點)<br>


活動狀態:  <input type="radio" name="status" value="0">到期自動開啟
          <input type="radio"  name="status" value="1">隱藏          
<br>


<input type="button" name="222" value="確定儲存" onclick="onSubmit();">

</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#a_album").hide();
	$("#a_song").hide();
	$("#albumTable").hide();
	$("#songTable").hide();	
	
});

function openMusic(){
	
	$("#a_album").show();
	$("#a_song").hide();
	$("#albumTable").hide();
	$("#songTable").hide();	
}
function closeMusic(condition){
	
	if(condition=='1'){
		$("#a_album").hide();
    	$("#a_song").hide();
    	$("#a_count").attr("disabled",true);
    	$("#a_money").attr("disabled",false);
    }
    if(condition=='2'){
    	$("#a_song").hide();
    	$("#a_album").hide();
    	$("#a_count").attr("disabled",false);
    	$("#a_money").attr("disabled",true);
    }
}


function onSubmit(){
	
	var product = $("input[name='contentCondition']:checked").val();
	/*if(product=='3'){
	      document.qForm.action="saveAlbumPromotionActivity.do";
	}else if(product=='4'){
		  document.qForm.action="saveSongPromotionActivity.do";
	}*/
	document.qForm.action="savePromotionActivity.do";
	
	document.qForm.submit();
}

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
		url: 'queryAlbumById.do' ,
        data:{"albumId":albumId},
		datatype: 'json',
		error: function(xhr) {},
		success: function(response) {
			  var NumOfJData = response.length;
              if(NumOfJData == 0){
            	  alert('此專輯ID不存在!');
	              return; 
              }
			
			  var trSize = $("tr[id="+albumId+"]").size();
			  if(trSize == 1){ 
				  alert('已有重複專輯ID!');
	              return; 
			  }
			 $("#albumTable").show();
             for(i=0;i<NumOfJData;i++){  
   			 $("#albumTable").append( " <tr id=\""+ response[i].albumID + "\">"  +
   		   			                  " <td> "+response[i].albumID+"</td>"+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/queryAlbumData.do?albumid="+response[i].albumID+"\">"+response[i].name+"</a></td> "+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/creatorProfile.do?creatorID="+response[i].creator.memberId+"\">"+response[i].creator.userName+"</a></td> "+
   			                          " <td><input type='button' value='刪除' onclick=\"deleteAlbumRow('"+response[i].albumID+"');\"></td> "+
   			                          "</tr>" );
   			$("#albumTable").append(" <input type=\"hidden\" name=\"albumID\" value=\""+response[i].albumID+"\">");
               
		  }
	    }
	});
	
}


function addSong(){
	var songId = document.qForm.songId.value;
	$.ajax({
		url: 'querySongById.do' ,
        data:{"songId":songId},
		datatype: 'json',
		error: function(xhr) {},
		success: function(response) {
			  var NumOfJData = response.length;
			  if(NumOfJData == 0){
           	      alert('此歌曲ID不存在!');
	              return; 
              }
			  var trSize = $("tr[id="+songId+"]").size();
			  if(trSize == 1){ 
				  alert('已有重複歌曲ID!');
	              return; 
			  }
			 $("#songTable").show();
             for(i=0;i<NumOfJData;i++){       
   			 $("#songTable").append( " <tr id=\""+ response[i].songID + "\">"  +
   		   			                  " <td> "+response[i].songID+"</td>"+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/queryAlbumData.do?albumid="+response[i].album.albumID+"\">"+response[i].album.name+"</a></td> "+
   			                          " <td> "+response[i].name+"</td> "+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/creatorProfile.do?creatorID="+response[i].album.creator.memberId+"\">"+response[i].album.creator.userName+"</a></td> "+
   			                          " <td><input type='button' value='刪除' onclick=\"deleteSongRow('"+response[i].songID+"');\"></td> "+
   			                          "</tr>" );
   			$("#songTable").append(" <input type=\"hidden\" name=\"songID\" value=\""+response[i].songID+"\">");
               }
	    }
	});
	
}




function  deleteAlbumRow(ID){
	 $("tr[id="+ID+"]").remove();
	}

function  deleteSongRow(ID){
	 $("tr[id="+ID+"]").remove();
	}
</script>

</html>