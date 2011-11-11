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
<h4>查詢使用者問題</h4>
<p>
<form name="fm" method="post">	
	問題區間:&nbsp;<input name="startDate" type="text" class="fillbox" readonly>&nbsp;
	<A HREF="javascript:show_calendar('fm.startDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a>&nbsp;-&nbsp;
	<input name="endDate" type="text" class="fillbox" readonly >&nbsp;
	<A HREF="javascript:show_calendar('fm.endDate');"><img src="${pageContext.request.contextPath}/images/cal.gif" border="0"></img></a><br><br>
	產品類型:電腦使用介面<input type="radio" name="productionType" id="productionType" value="PC"/>PC<br>
			手機使用介面<input type="radio" name="productionType" id="productionType" value="Android"/>Android
			<input type="radio" name="productionType" id="productionType" value="Windows Phone"/>Windows Phone
			<input type="radio" name="productionType" id="productionType" value="Symbian"/>Symbian<br><br>
	聯絡信箱:<input type="text" name="email" ><br><br>
	提問類型 :<select name="questionType" id="questionType">
				<option value="註冊問題" selected="selected">註冊問題</option>
				<option value="付費相關" >付費相關</option>
				<option value="下載問題" >下載問題</option>
				<option value="軟體相關" >軟體相關</option>
				<option value="播放問題" >播放問題</option>
				<option value="SD卡相關" >SD卡相關</option>
				<option value="網站使用問題" >網站使用問題</option>
				<option value="服務品質" >服務品質</option>
				<option value="促銷活動" >促銷活動</option>
				<option value="合作提案" >合作提案</option>
				<option value="其他問題" >其他問題</option>
			</select><br><br>	
	<input type="hidden" name="adminId" value=${admin}>
	<input type="button" value="查詢" onclick="queryQuestionList()">
	<input type="reset" value="全部清除">
</form>
<p>
查詢結果
<table border="1" BorderColor="#000000" cellpadding="0" cellspacing="0">
<tr><td Width="100" Height="35" valign="top"><font size="2">提問時間</font></td>
	<td valign="top" Width="100"><font size="2">產品類型</font></td>
	<td valign="top" Width="100"><font size="2">身分</font></td>
	<td valign="top" Width="100"><font size="2">姓名</font></td>
	<td valign="top" Width="100"><font size="2">聯絡信箱</font></td>
	<td valign="top" Width="100"><font size="2">電話</font></td>
	<td valign="top" Width="100"><font size="2">問題類型</font></td>
	<td valign="top" Width="100"><font size="2">處理狀態</font></td>
	<td valign="top" Width="50"><font size="2">詳細問題</font></td>
	<td valign="top" Width="50"><font size="2">備註</font></td></tr>
	<c:forEach var="hm" items="${questionList}">
	<tr>
	<td Width="100" Height="35" valign="top"><font size="2">${hm.questionDate}</font></td>
	<td valign="top" Width="100"><font size="2">${hm.productionType}</font></td>
	<td valign="top" Width="100"><font size="2">${hm.userIdentity}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm.userName}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm.email}</font></td>
	<td valign="top" Width="100"><font size="2">${hm.tel}</font></td>
	<td valign="top" Width="100"><font size="2">${hm.questionType}</font></td>
	<td valign="top" Width="100"><font size="2">${hm.handleStatus}</font></td>
	<td valign="top" Width="50"><input type="button"  value="查看" onclick="queryQuestionDetail(${admin},'${hm.questionId}')"></td>
	<td valign="top" Width="50">
		<c:if test="${hm.noteContent != null}">
			<font size="2">YES</font>
		</c:if>		
	</td>
	</tr>
	</c:forEach> 
</table>
</body>
<script type="text/javascript">

function queryQuestionList(){	
	document.fm.action="queryQuestionList.do";
  	document.fm.submit();
}
function queryQuestionDetail(admin, qId){  
	location.href="${pageContext.request.contextPath}/queryQuestionDetail.do?adminId="+admin+"&qId="+qId;
}
</script>
</html>