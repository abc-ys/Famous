<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<table width="800" height="200" border="0" cellspacing="2" frame="border" > 
	<td width="200" height="200" >
		<h4>通知</h4>
		${account[1]}首歌曲未下載<br><p>
		${account[2]}個項目在購物車<br><p>
		<a href="${pageContext.request.contextPath}/myFriendList/${account[0]}.do">${account[3]}</a>個好友邀請<br><p>
	</td>
	<td width="300" height="200" >
		<form name="form">	
			<h4>GSiMoney/GSiBonus</h4>			
			GSiMoney餘額:<a href="${pageContext.request.contextPath}/incomeOutgo.do?userId=${account[0]}">${account[4]}</a><br><p>
			GSiBonus餘額:<a href="${pageContext.request.contextPath}/rewardRecord.do?userId=${account[0]}">${account[5]}</a><br><p>
				
			<h4>作品銷售查詢</h4>
			本月已售出歌曲:${account[6]}首<br><p>
		</form>
	</td>
	<td width="300" height="200" >
		<h4>推薦創作人(FACEBOOK)</h4>
			
	</td>
	<tr>
	<td colspan=3 width="800"> 	
		<h4>動態(FACEBOOK)</h4>		
	</td>
 	</table>
</body>
</html>