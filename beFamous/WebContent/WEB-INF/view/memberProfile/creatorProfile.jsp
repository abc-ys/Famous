<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gsimedia" uri="http://www.gsi-media.com/" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}"/>/css/cell.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/js/cell.js"></script>
</head>
<body>
<table width="1000" border="1" cellspacing="2" align=center rules="none"> 
	<td height="270" width="200">
		<img alt="" src="/${initParam.ImageWeb}/${creator.picture}" width="200"　height="100" >
	</td>
	<td width="300">
		<form name="form">	
			<h2>${creator.userName}<input type="button" value="讚"></h2>
			<h4>創作人基本資料</h4>
			${creator.location}, ${creator.city}<br>
			身份:${creator.identityName}<br>
			歌曲數:30<br>
			粉絲數:66<br>			
			<h4>喜歡的音樂類型</h4>
			${creator.likeMusicType}<br>
		</form>
	</td>
	<td width="250">
		<h4>推薦你如果你喜歡</h4>
			${creator.likeSinger}<br>
			<br><br>
		<form name="form" method="post">    
	    <c:if test="${isFan==false}">
		   <div id="fanSize"> <input type="button" name="111" value="加入粉絲團"  onclick="addFan('${creator.id}');"></div>
		</c:if>
		<c:if test="${isFriend==false}">
		    <div id="friendSize"><input type="button" name="222" value="加為好友" onclick="addFriend('${creator.id}');"></div>
		</c:if>
		
		</form>
	</td>
	<td width="250">
		<h3>最新專輯</h3><br>	
		 <gsimedia:albumTable listName="newAlbum"></gsimedia:albumTable>
 	</td>
	<tr><tr>
	
	<div id="navi" class="navigation">
   		<td colspan=3>
			 <a href="#" title="creatorAllAlbums" onClick="display('creatorAllAlbums')">所有專輯</a>
			 <a href="#" title="creatorNewActivity" onClick="display('creatorNewActivity')">近期動態</a>
			 <a href="#" title="creatorAllFriendsFans" onClick="display('creatorAllFriendsFans')">好友粉絲團</a>
		</td>
	</div>
	<tr>
 	<tr>
 	<td colspan=3 rowspan=2 height="1000" width="740"> 		
	 	<div id="creatorAllAlbums">
	 		 <br>
	 		 <iframe name="creatorAllAlbums" src="creatorAllAlbums.do?creatorID=${creator.id}" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>	
	 	<div id="creatorNewActivity" style="display:none">
	 		<iframe name="creatorNewActivity" src="creatorNewActivity.do?creatorID=${creator.id}" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>	
	 	<div id="creatorAllFriendsFans" style="display:none">
	 		<iframe name="creatorAllFriendsFans" src="creatorAllFriendsFans.do?creatorID=${creator.id}" height="1000" width="740" frameborder="0"></iframe> 
	 	</div>		
 	</td>
	<td width="250">
		<h3>推薦創作人</h3>
		<a href="">創作人名稱1</a><br>30首歌<br>
		<a href="">創作人名稱2</a><br>30首歌<br>
		<a href="">創作人名稱3</a><br>30首歌<br>
		<a href="">創作人名稱4</a><br>30首歌<br>
		<a href="">創作人名稱5</a><br>30首歌<br>
		
 	</td>
 	<tr>
	<td width="250">
		<h3>簡介</h3>
		${creator.introduction}...<a href="">more</a><br>
 	</td>
 	</table>
</body>
<script type="text/javascript">


function addFan(addMemberid){
	$.ajax({
		url: '${pageContext.request.contextPath}/addFan.do' ,
        data:{"memberID":addMemberid},
		type: 'post',
		error: function(xhr) {},
		success: function(response) {
			$("#fanSize").hide();
			alert('加入粉絲團成功!');
			//location.reload();
			
		}
	});
	
}

function addFriend(addMemberid){
	$.ajax({
		url: '${pageContext.request.contextPath}/addCreatorFriend.do' ,
        data:{"memberID":addMemberid},
		type: 'post',
		error: function(xhr) {},
		success: function(response) {
			$("#friendSize").hide();
			alert('加入好友成功!');
			//location.reload();
		}
	});
	
}

function display(divname){
	if(divname=='creatorAllAlbums'){
		$("#creatorAllAlbums").toggle();
		$("#creatorNewActivity").hide();
		$("#creatorAllFriendsFans").hide();
	}
	if(divname=='creatorNewActivity'){
		$("#creatorNewActivity").toggle();
		$("#creatorAllAlbums").hide();
		$("#creatorAllFriendsFans").hide();
	}
	if(divname=='creatorAllFriendsFans'){
		$("#creatorAllFriendsFans").toggle();
		$("#creatorAllAlbums").hide();
		$("#creatorNewActivity").hide();
	}	
}
</script>
</html>