<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>

<form action="" name="qForm" method="post">
活動名稱:<input type="text" name="title" value="${activity.title}" ><br></br>
活動期間:
<input type="text" name="startDate" class="fillbox" value="<fmt:parseDate var="dateObj" value="${activity.startDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;
<A HREF="javascript:show_calendar('qForm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp;-&nbsp;
<input type="text" name="endDate" class="fillbox" value="<fmt:parseDate var="dateObj" value="${activity.endDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;
<A HREF="javascript:show_calendar('qForm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>

篩選條件: <br>
<input type="radio"  name="contentCondition" value="1" <c:if test="${activity.contentCondition==1}">checked</c:if> onclick="closeMusic('1');">儲值金額 
<input type="text" id="a_money" <c:if test="${activity.contentCondition==1}">value="${activity.content}"</c:if>  name="prepaidMoney" >元<br>

<input type="radio"  name="contentCondition" value="2" <c:if test="${activity.contentCondition==2}">checked</c:if> onclick="closeMusic('2');">儲值次數 
<input type="text" id="a_count" <c:if test="${activity.contentCondition==2}">value="${activity.content}"</c:if>  name="prepaidCount" >次<br>

<input type="radio" id="album" name="contentCondition" value="3" <c:if test="${activity.contentCondition==3}">checked</c:if> onclick="changeProduct('album');">專輯  
<input type="radio" id="song" name="contentCondition" value="4" <c:if test="${activity.contentCondition==4}">checked</c:if> onclick="changeProduct('song');">歌曲 



<div id="a_album">
專輯ID:<input type="text" name="albumId">  <input type="button" name="111" value="增加" onclick="addAlbum();">

<table id="albumTable1" border="1">
<tbody id="albumTable">
<tr><td> 專輯ID </td> <td> 專輯名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
<c:forEach var="albumSet" items="${activity.albumSet}" >
<tr id="${albumSet.pid}"><td> ${albumSet.pid} </td> <td>  ${albumSet.name} </td> <td>  ${albumSet.creator.userName} </td><td><input type='button' value='刪除' onclick="deleteAlbumRow('${albumSet.pid}');"></td></tr>
<input type="hidden" name="albumID" value="${albumSet.pid}">
</c:forEach>
</tbody>
</table>


</div>


<div id="a_song">
歌曲ID:<input type="text" name="songId"> <input type="button" name="222" value="增加" onclick="addSong();">

<table id="songTable1" border="1">
<tbody id="songTable">
<tr ><td> 歌曲ID </td> <td> 專輯名稱 </td> <td> 歌曲名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
<c:forEach var="songSet" items="${activity.songSet}" >
<tr id="${songSet.pid}"><td>${songSet.pid}</td><td> ${songSet.album.name} </td> <td>  ${songSet.name} </td> <td>  ${songSet.album.creator.userName} </td><td><input type='button' value='刪除' onclick="deleteSongRow('${songSet.pid}');"></td></tr>
<input type="hidden" name="songID" value="${songSet.pid}">
</c:forEach>
</tbody>
</table>

</div>
<br>



符合內容:<br> <input type="radio" id="contentCondition" name="condition" <c:if test="${activity.condition==1}">checked</c:if> value="1">符合全部所選ID<br>
          <input type="radio" id="contentCondition" name="condition" <c:if test="${activity.condition==2}">checked</c:if> value="2">只符合一個所選的ID<br>
          <input type="radio" id="contentCondition" name="condition" <c:if test="${activity.condition==3}">checked</c:if> value="3">須符合三個所選的ID<br>
<p>      
贈送內容: GSiBonus  <br>
    <input type="radio" name="rewardCondition"  <c:if test="${activity.rewardCondition==1}">checked</c:if>  value="1"> 
    <input type="text" id="a_rewardMoney1" <c:if test="${activity.rewardCondition==1}">value="${activity.reward}"</c:if> name="rewardMoney1" >元(點)<br>
    
    <input type="radio" name="rewardCondition"  <c:if test="${activity.rewardCondition==2}">checked</c:if> value="2"> 
訂單金額  X <input type="text" id="a_rewardMoney2" <c:if test="${activity.rewardCondition==2}">value="${activity.reward}"</c:if> name="rewardMoney2" >元(點)<br>

<p>
使用期限:
<select name="rewardDeadline">  
	<option value="1" <c:if test="${activity.rewardDeadline==1}">selected</c:if>>一個月</option> 
	<option value="2" <c:if test="${activity.rewardDeadline==2}">selected</c:if>>三個月</option> 
	<option value="3" <c:if test="${activity.rewardDeadline==3}">selected</c:if>>六個月</option> 
	<option value="4" <c:if test="${activity.rewardDeadline==4}">selected</c:if>>九個月</option> 
	<option value="5" <c:if test="${activity.rewardDeadline==5}">selected</c:if>>一年</option> 
</select>
<p>
活動狀態:  <input type="radio" name="status" <c:if test="${activity.status==1}">checked</c:if> value="1">到期自動開啟
          <input type="radio"  name="status" <c:if test="${activity.status==3}">checked</c:if> value="3">隱藏          
<br>

<input type="hidden" name="adminID" value="${adminID}">
<input type="button" name="222" value="確定修改" onclick="onSubmit('${activity.id}');">
<input type="hidden" name="activityID">
</form>
</body>

<script type="text/javascript">
$(document).ready(function(){
	var product = $("input[name='contentCondition']:checked").val();
	if(product=='3'){
		$("#a_album").show();
		$("#a_song").hide();
		$("#albumTable").show();
		$("#albumTable1").show();
		$("#songTable").hide();	
		$("#songTable1").hide();	
	
	}else if(product=='4'){
		$("#a_album").hide();
		$("#a_song").show();
		$("#albumTable").hide();
		$("#albumTable1").hide();
		$("#songTable").show();	
		$("#songTable1").show();
		
	}else{
		$("#a_album").hide();
		$("#a_song").hide();
		$("#albumTable").hide();
		$("#albumTable1").hide();
		$("#songTable").hide();	
		$("#songTable1").hide();	
	}
});


function onSubmit(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/updatePromotionActivity.do";
	document.qForm.submit();
}

function openMusic(){
	
	$("#a_album").show();
	$("#a_song").hide();
	$("#albumTable").hide();
	$("#albumTable1").hide();
	$("#songTable").hide();	
	$("#songTable1").hide();	
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
             for(var i=0;i<NumOfJData;i++){  
   			 $("#albumTable").append( " <tr id=\""+ response[i].albumID + "\">"  +
   		   			                  " <td> "+response[i].albumID+"</td>"+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/queryAlbumData.do?albumid="+response[i].albumID+"\">"+response[i].name+"</a></td> "+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/creatorProfile.do?creatorID="+response[i].creatorID+"\">"+response[i].creatorName+"</a></td> "+
   			                          " <td><input type='button' value='刪除' onclick=\"deleteAlbumRow('"+response[i].albumID+"');\"></td> "+
   			                          "</tr>" );
   			$("#albumTable").append(" <input type=\"hidden\" name=\"albumID\" value=\""+response[i].albumID+"\">");
		  }
             $("#albumTable").show();
             $("#albumTable1").show();
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
			 
             for(var i=0;i<NumOfJData;i++){       
   			 $("#songTable").append( " <tr id=\""+ response[i].songID + "\">"  +
   		   			                  " <td> "+response[i].songID+"</td>"+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/queryAlbumData.do?albumid="+response[i].albumID+"\">"+response[i].albumName+"</a></td> "+
   			                          " <td> "+response[i].name+"</td> "+
   			                          " <td> <a href=\"${pageContext.request.contextPath}/creatorProfile.do?creatorID="+response[i].creatorID+"\">"+response[i].creatorName+"</a></td> "+
   			                          " <td><input type='button' value='刪除' onclick=\"deleteSongRow('"+response[i].songID+"');\"></td> "+
   			                          "</tr>" );
   			$("#songTable").append(" <input type=\"hidden\" name=\"songID\" value=\""+response[i].songID+"\">");
               }
             $("#songTable").show();
             $("#songTable1").show();
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