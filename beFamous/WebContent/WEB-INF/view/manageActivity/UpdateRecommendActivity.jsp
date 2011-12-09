<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
</head>
<body>

<h1>查看推薦專輯</h1>

<form action="" name="qForm" method="post">
推薦主題:<input type="text" name="title" value="${activity.title}" ><br></br>
推薦期間:
<input type="text" name="startDate" class="fillbox" value="<fmt:parseDate var="dateObj" value="${activity.startDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;
<A HREF="javascript:show_calendar('qForm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp;-&nbsp;
<input type="text" name="endDate" class="fillbox" value="<fmt:parseDate var="dateObj" value="${activity.endDate}" type="DATE" pattern="yyyyMMddHHmmss"/><fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" readonly>&nbsp;
<A HREF="javascript:show_calendar('qForm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>
<br></br>

專輯內容:
<br>
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
活動狀態:  <input type="radio" name="status" <c:if test="${activity.status==1}">checked</c:if> value="1">到期自動開啟
          <input type="radio"  name="status" <c:if test="${activity.status==3}">checked</c:if> value="3">隱藏          
<br>
<input type="hidden" name="adminID" value="${adminID}">
<input type="button" name="222" value="確定修改" onclick="onSubmit('${activity.id}');">
<input type="hidden" name="activityID">
</form>


</body>
<script type="text/javascript">


function onSubmit(activityID){
	document.qForm.activityID.value=activityID;
	document.qForm.action="${pageContext.request.contextPath}/updateAlbumRecommendActivity.do";
	document.qForm.submit();
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
			 
             for(i=0;i<NumOfJData;i++){  
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