<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>

<h1>查看推薦專輯</h1>

<form action="" name="qForm" method="post">
推薦主題:<input type="text" name="title" value="${activity.title}" ><br></br>
推薦期間:<input type="text" name="startDate" value="${activity.startDate}"> - <input type="text" name="endDate" value="${activity.endDate}" ><br></br>

選擇內容: <input type="radio" id="album" name="product" value="album" onclick="changeProduct('album');" <c:if test="${fn:length(activity.albumSet)>0}">checked </c:if>>專輯   <input type="radio" id="song" name="product"  <c:if test="${fn:length(activity.songSet)>0}">checked </c:if>  value="song" onclick="changeProduct('song');">歌曲 

<div id="a_album">
專輯ID:<input type="text" name="albumId">  <input type="button" name="111" value="增加" onclick="addAlbum();">

<table id="albumTable" border="1">
<tr><td> 專輯ID </td> <td> 專輯名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
<c:forEach var="albumSet" items="${activity.albumSet}" >
<tr id="${albumSet.albumID}"><td> ${albumSet.albumID} </td> <td>  ${albumSet.name} </td> <td>  ${albumSet.creator.userName} </td><td><input type='button' value='刪除' onclick="deleteAlbumRow('${albumSet.albumID}');"></td></tr>
<input type="hidden" name="albumID" value="${albumSet.albumID}">
</c:forEach>
</table>


</div>


<div id="a_song">
歌曲ID:<input type="text" name="songId"> <input type="button" name="222" value="增加" onclick="addSong();">

<table id="songTable" border="1">
<tr><td> 歌曲ID </td> <td> 專輯名稱 </td> <td> 歌曲名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
<c:forEach var="songSet" items="${activity.songSet}" >
<tr id="${songSet.songID}"><td>${songSet.songID}</td><td> ${songSet.album.name} </td> <td>  ${songSet.name} </td> <td>  ${songSet.album.creator.userName} </td><td><input type='button' value='刪除' onclick="deleteSongRow('${songSet.songID}');"></td></tr>
<input type="hidden" name="songID" value="${songSet.songID}">
</c:forEach>
</table>

</div>

活動狀態:  <input type="radio" name="status" value="0">到期自動開啟
          <input type="radio"  name="status" value="1">隱藏          
<br>
<input type="button" name="222" value="確定修改" onclick="onSubmit('${activity.activityID}');">
<input type="hidden" name="activityID">
</form>


</body>
<script type="text/javascript">


function onSubmit(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/updateAlbumRecommendActivity.do";
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
		url: '${pageContext.request.contextPath}/queryAlbumById.do' ,
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
		url: '${pageContext.request.contextPath}/querySongById.do' ,
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