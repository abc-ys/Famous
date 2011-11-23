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
	產品類型:電腦使用介面<input type="radio" name="productionType" id="productionType" value="1"/>PC<br>
			手機使用介面<input type="radio" name="productionType" id="productionType" value="2"/>Android
			<input type="radio" name="productionType" id="productionType" value="3"/>Windows Phone
			<input type="radio" name="productionType" id="productionType" value="4"/>Symbian<br><br>
	聯絡信箱:<input type="text" name="email" ><br><br>
	提問類型 :<select name="questionType" id="questionType">
				<option selected="selected"></option>
				<option value="1" >註冊問題</option>
				<option value="2" >付費相關</option>
				<option value="3" >下載問題</option>
				<option value="4" >軟體相關</option>
				<option value="5" >播放問題</option>
				<option value="6" >SD卡相關</option>
				<option value="7" >網站使用問題</option>
				<option value="8" >服務品質</option>
				<option value="9" >促銷活動</option>
				<option value="10" >合作提案</option>
				<option value="11" >其他問題</option>
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
	<td valign="top" Width="100"><font size="2">
	<c:if test="${hm.productionType==1}">PC</c:if>
	<c:if test="${hm.productionType==2}">Android</c:if>
	<c:if test="${hm.productionType==3}">Windows Phone</c:if>
	<c:if test="${hm.productionType==4}">Symbian</c:if></font></td>
	<td valign="top" Width="100"><font size="2">
	<c:if test="${hm.userIdentity==1}">會員</c:if>
	<c:if test="${hm.userIdentity==2}">非會員</c:if></font></td>	
	<td valign="top" Width="100"><font size="2">${hm.userName}</font></td>	
	<td valign="top" Width="100"><font size="2">${hm.email}</font></td>
	<td valign="top" Width="100"><font size="2">${hm.tel}</font></td>
	<td valign="top" Width="100"><font size="2">
	<c:if test="${hm.questionType==1}">註冊問題</c:if>
	<c:if test="${hm.questionType==2}">付費相關</c:if>
	<c:if test="${hm.questionType==3}">下載問題</c:if>
	<c:if test="${hm.questionType==4}">軟體相關</c:if>
	<c:if test="${hm.questionType==5}">播放問題</c:if>
	<c:if test="${hm.questionType==6}">SD卡相關</c:if>
	<c:if test="${hm.questionType==7}">網站使用問題</c:if>
	<c:if test="${hm.questionType==8}">服務品質</c:if>
	<c:if test="${hm.questionType==9}">促銷活動</c:if>
	<c:if test="${hm.questionType==10}">合作提案</c:if>
	<c:if test="${hm.questionType==11}">其他問題</c:if></font></td>
	<td valign="top" Width="100"><font size="2"><c:if test="${hm.handleStatus==1}">未處理</c:if><c:if test="${hm.handleStatus==2}">已處理</c:if></font></td>
	<td valign="top" Width="50"><input type="button"  value="查看" onclick="queryQuestionDetail(${admin},'${hm.id}')"></td>
	<td valign="top" Width="50">
		<c:if test="${hm.noteContent != null}">
			<font size="2">V</font>
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