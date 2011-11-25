<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>

<table width="1000" border="1" cellspacing="2" align=center rules="none"> 
	<td height="270" width="200">
		<img alt="" src="/${initParam.ImageWeb}/${GeneralMember.picture}" width="200"　height="100" >
	</td>
	<td width="550">
		<form name="qForm" method="post">	
			<h2>${GeneralMember.userName}<input type="submit" value="讚"></h2>
			<h4>會員基本資料</h4>
			${GeneralMember.location}, ${GeneralMember.city}<br>
		    
			好友數:<div id="friendSize">${fn:length(FriendSet)}</div> <br>
			
		 <div id="f1">
		<c:if test="${isFriend==false }">
		 <input type="button" name="111" value="加為好友" onclick="addFriend('${GeneralMember.id}');">
		</c:if> 
		</div>	
		<input type="hidden" name="memberID" >
	</form>
	</td>
	<td width="250">
		<h3>簡介</h3>
		${GeneralMember.introduction}...<a href="">more</a><br>	
 	</td>
	<tr><tr>
	<div id="navi" class="navigation">
   		<td colspan=3>
			 <a href="javascript:void(0)" title="memberNewActivity" onClick="display('memberNewActivity')">近期動態</a>
			 <a href="javascript:void(0)" title="memberAllFriendsCreators" onClick="display('memberAllFriendsCreators')">好友及追蹤中的創作人</a>
		</td>
	</div>
	<tr>
 	<tr>
 	<td colspan=2 rowspan=2 height="1000" width="740"> 		
	 	<div id="memberNewActivity" style="display:block">
	 		<iframe name="memberNewActivity" src="${pageContext.request.contextPath}/memberNewActivity.do?memberID=${GeneralMember.id}" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>	
	 	<div id="memberAllFriendsCreators" style="display:none">
	 		<iframe name="memberAllFriendsCreators" src="${pageContext.request.contextPath}/memberAllFriendsCreators.do?memberID=${GeneralMember.id}" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>		
 	</td>
	
 	</table>
 	
</body>
<script type="text/javascript">

function addFriend(addMemberid){
	$.ajax({
		url: '${pageContext.request.contextPath}/addFriend.do' ,
        data:{"memberID":addMemberid},
		type: 'post',
		error: function(xhr) {},
		success: function(response) {
			$("#friendSize").replaceWith("<div id=\"friendSize\">"+response+"</div>");
			$("#f1").hide();
			alert('加入好友成功!');
		}
	});
	
}

function display(divname){
	if(divname=='memberNewActivity'){
		$("#memberNewActivity").toggle();
		$("#memberAllFriendsCreators").hide();
	}
	if(divname=='memberAllFriendsCreators'){
		$("#memberAllFriendsCreators").toggle();
		$("#memberNewActivity").hide();
	}	
}
</script>
</html>