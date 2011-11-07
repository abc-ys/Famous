<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>


<p>修改創作人月榜</p>


<form action="" name="qForm" method="post">
創作人:<input type="text" name="creatorName"><br>


<input type="button" name="111" value="查詢" onclick="onSubmit()">


<c:if test="${arCreator!=null}">
查詢結果<br>
<table border="1">
<tr>
<td>排行</td>
<td>會員ID</td>
<td>創作人</td>
<td>月點閱術</td>
<td>被說讚數</td>
<td>粉絲數</td>
<td>月總分</td>
<td>月CP值</td>
<td>合計</td>
<td>修改</td>
</tr>
<c:forEach var="arCreator" varStatus="status" items="${arCreator}">
<tr>
<td>${status.count}</td>
<td>${arCreator.memberId}</td>
<td><a href="${pageContext.request.contextPath}/creatorProfile.do?creatorID=${arCreator.memberId}">${arCreator.userName}</a></td>
<td>0</td>
<td>0</td>
<td>0</td>
<td>0</td>
<td><input type="text" id="cpPoint_${status.count}" name="cpPoint_${status.count}" value="${arCreator.cp.modifyValue}""></td>
<td>0</td>
<td><input type="button" name="111" value="更新" onclick="updateCP('${status.count}');"></td>
</tr>
</c:forEach>
</table>
</c:if>

</form>
</body>

<script type="text/javascript">
function onSubmit(){
	document.qForm.action="${pageContext.request.contextPath}/queryCreatorWeekRankingForAdmin.do";
	document.qForm.submit();
}

function updateCP(count){
    var cpPoint = $("#cpPoint_"+count).val();
    var cpId    = $("#cpId_"+count).val();
    if(cpPoint==''){
      alert('請填入CP值');
      return;
    }
	$.ajax({
	    url: '${pageContext.request.contextPath}/updateCreatorMonthCP.do',
	    type: 'POST',
	    data: {cpPoint:cpPoint,cpId:cpId},
	    error: function(xhr) {},
	    success: function(response) {
		    alert('更新成功');
		}
	});
}

</script>

</html>