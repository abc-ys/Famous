<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<h1>新增推薦專輯</h1>

<form action="" name="qForm" method="post">
推薦主題:&nbsp;<input type="text" name="title" ><br></br>
推薦期間:&nbsp;
<input type="text" name="startDate" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('qForm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
&nbsp;-&nbsp;<input type="text" name="endDate" class="fillbox" readonly>&nbsp;<A HREF="javascript:show_calendar('qForm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>

加入專輯:<br>
專輯ID:&nbsp;<input type="text" name="albumId">  <input type="button" name="111" value="增加" onclick="addAlbum();">

<table id="albumTable1" border="1">
<tbody id="albumTable">
<tr><td> 專輯ID </td> <td> 專輯名稱 </td> <td> 創作人 </td><td>刪除</td></tr>
</tbody>
</table>

活動狀態:&nbsp;<input type="radio" name="status" value="1">到期自動開啟
          <input type="radio"  name="status" value="3">隱藏          
<br>
<input type="hidden" name="adminID" value="${adminID}">
<input type="button" name="222" value="確定儲存" onclick="onSubmit();">

</form>
</body>
<script type="text/javascript">

function onSubmit(){
	      
	document.qForm.action="saveAlbumRecommendActivity.do";
	document.qForm.submit();
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

function  deleteAlbumRow(ID){
	 $("tr[id="+ID+"]").remove();
	}
</script>

</html>