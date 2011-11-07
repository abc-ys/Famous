<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<br>
<h4>查詢會員資料</h4>
<p>
<form name="fm" method="post">
	帳號:<input type="text" name="orderNo" id="orderNo"><br><br>
	身分:<select name="identity" id="identity">
			<option value="一般會員" selected="selected">一般會員</option>
			<option value="創作人" >創作人</option>
			<option value="樂團" >樂團</option>
		</select><br><br>
	加入日期區間:&nbsp<input name="MOPEND" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.MOPEND');"><img src="images/cal.gif" border="0"></img></a>&nbsp-&nbsp
	<input name="MCLOSED" type="text" class="fillbox" readonly >&nbsp;
	<A HREF="javascript:show_calendar('fm.MCLOSED');"><img src="images/cal.gif" border="0"></img></a><br><br>
	
	地區:<select name="mLocation" id="mLocation">
			<option value="" selected="selected">台灣</option>
			<option value="" >大陸</option>
			<option value="" >香港</option>
			<option value="" >日本</option>
		</select><br><br>
	粉絲數:<input type="text" name="minFns" id="minFns">-<input type="text" name="maxFns" id="maxFns"><br><br>
	好友數:<input type="text" name="minFds" id="minFds">-<input type="text" name="maxFds" id="maxFds"><br><br>
	狀態:<input type="radio" name="status" id="status" value="正常"/>正常<input type="radio" name="status" id="status" value="停權"/>停權<br><br>
	<input type="hidden" name="userIdentity" >
	<input type="hidden" name="location" >
	<input type="hidden" name="statusName" >
	<input type="button" value="查詢" onclick="queryMember()">
	<input type="reset" value="全部清除">
</form>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="140" Height="35" valign="top"><font size="2">帳號</font></td>
	<td valign="top" Width="100"><font size="2">ID</font></td>
	<td valign="top" Width="100"><font size="2">姓名</font></td>
	<td valign="top" Width="100"><font size="2">身分</font></td>
	<td valign="top" Width="100"><font size="2">地區</font></td>
	<td valign="top" Width="100"><font size="2">加入日期</font></td>
	<td valign="top" Width="50"><font size="2">粉絲數</font></td>
	<td valign="top" Width="50"><font size="2">好友數</font></td>
	<td valign="top" Width="50"><font size="2">狀態</font></td>
	<td valign="top" Width="50"><font size="2">備註</font></td></tr>
	<c:forEach var="hm" items="${memberLists}">
	<td Width="140" Height="35" valign="top"><font size="2"><a href="javascript:identity('${admin.adminId}','${hm[0].memberId}','${hm[0].identityName}')">${hm[0].email}</a></font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].memberId}</font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].userName}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm[0].identityName}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm[0].location}</font></td>
	<td valign="top" Width="100"><font size="2">${hm[0].createDate}</font></td>
	<td valign="top" Width="50"><font size="2">${hm[1]}</font></td>
	<td valign="top" Width="50"><font size="2">${hm[2]}</font></td>
	<td valign="top" Width="50"><font size="2">${hm[0].memberStatus.statusName}</font></td>	
	<td valign="top" Width="50"><a href="javascript:queryNote(${hm[0].memberId})"><font size="2">詳細</font></a></td>	
	</c:forEach> 
</table>
</body>
<script type="text/javascript">
function queryMember(){   
	var identity =$('#identity :selected').text();
	document.fm.userIdentity.value=identity;
	var location =$('#mLocation :selected').text();
	document.fm.location.value=location;
	var status =$('input[name=status]:checked').val();
	document.fm.statusName.value=status;
	document.fm.action="manageMember.do";
  	document.fm.submit();
}
function identity(adminId,memberId,identityName){	
	if(identityName == '一般會員'){
		location.href="${pageContext.request.contextPath}/manageGMemberDetail/get/"+adminId+"/"+memberId+".do";
	}else{
		location.href="${pageContext.request.contextPath}/manageCreatorDetail/get/"+adminId+"/"+memberId+".do";
	}
}
function queryNote(memberId){   
	window.open("${pageContext.request.contextPath}/manageMemberNote.do?memberId="+memberId,'son','height=300,width=300,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no');	
}
</script>
</html>