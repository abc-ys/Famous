<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<p>修改創作人週榜</p>

<form action="" name="qForm" method="post">
創作人:<input type="text" name="creatorName"><br>

<input type="button" name="111" value="查詢" onclick="onSubmit()"><p>


<c:if test="${arCreator!=null}">
查詢結果<br>
<table border="1">
<tr>
<td>排行</td>
<td>會員ID</td>
<td>創作人</td>
<td>週點閱數</td>
<td>被說讚數</td>
<td>粉絲數</td>
<td>週總分</td>
<td>週CP值</td>
<td>合計</td>
<td>修改</td>
</tr>
<c:forEach var="list" varStatus="status" items="${arCreator}">
<tr>
<td>${status.count}</td>
<td>${list[0].id}</td>
<input type="hidden" name="creatorID" id="creatorID" value="${list[0].id}"/>
<td><a href="creatorProfile.do?creatorID=${list[0].id}">${list[0].userName}</a></td>
<td>${list[2]}</td>
<td>0</td>
<td>${list[1]}</td>
<td>${list[1]+list[2]}</td>
<input type="hidden" name="originalTotal" id="originalTotal_${status.count}" value="${list[1]+list[2]}"/>
<td><input type="text" id="cpPoint_${status.count}" name="cpPoint_${status.count}" value="0" size="5" onblur="cal('${status.count}')"></td>
<td><div id="tPrice_${status.count}" ></div></td>
<td><input type="button" name="111" value="更新" onclick="updateCP('${status.count}','${adminID}','${list[0].id}');"></td>
</tr>
</c:forEach>
<input type="hidden" name="adminID" id="adminID" value="${adminID}"/>
</table>
</c:if>
</form>
</body>

<script type="text/javascript">
function onSubmit(){
	document.qForm.action="queryCreatorWeekRankingForAdmin.do";
	document.qForm.submit();
}

function cal(index){
	var p1 = $('#originalTotal_'+index).val();
	var p2 = $('#cpPoint_'+index).val();
	
	$('#tPrice_'+index).html(eval(p1)+eval(p2));
}

function updateCP(count,adminID,creatorID){
    var cpPoint = $("#cpPoint_"+count).val();
    var adminID = $("#adminID").val();
    var creatorID = $("#creatorID").val();
    if(cpPoint==''){
      alert('請填入CP值');
      return;
    }
	$.ajax({
	    url: 'updateCreatorWeekCP.do',
	    type: 'POST',
	    data: {"cpPoint":cpPoint,"adminID":adminID,"creatorID":creatorID},
	    error: function(xhr) {},
	    success: function(response) {
		    alert('更新成功');
		}
	});
}

</script>

</html>