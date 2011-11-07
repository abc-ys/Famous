<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<table>
  <tr>
  <td>
    <a href="${pageContext.request.contextPath}/manageMusicCategory.do">音樂類別管理</a><br>
  </td>
  <tr>
  <td>
    <h4>訂單管理</h4>
    <a href="${pageContext.request.contextPath}/manageCashOrder.do">現金訂單</a><br>
    <a href="${pageContext.request.contextPath}/manageGSiMoneyOrder.do">GSiMoney訂單</a><br>
    <a href="">運費規則登錄</a><br>
  </td>
  <tr>
  <td>
    <h4>帳務管理</h4>
    <a href="${pageContext.request.contextPath}/billRegister.do">訂單發票登錄</a><br>
    <a href="">兌換金額管理</a><br>
  </td>
  <tr>
  <td>
    <h4>會員管理</h4>
    <a href="${pageContext.request.contextPath}/manageMember.do">查詢會員資料</a><br>
  </td>
  <tr>
  <td>
    <h4>廣告管理</h4>
    <a href="${pageContext.request.contextPath}/addAdBanner.do">新增廣告</a><br>
    <a href="${pageContext.request.contextPath}/queryAdBanner.do">查詢廣告內容</a><br>
    <a href="${pageContext.request.contextPath}/queryUploadAdBanner.do">查看上傳廣告內容</a><br>
  </td>
 <!--  <td>
    <h4>我的帳本</h4>
    <a href="incomeOutgo.do">GSiMoney收支表</a><br>
    <a href="prePayRecord.do">儲值記錄</a><br>
    <a href="payRecord.do">GSiMoney消費記錄</a><br>
    <a href="">現金消費記錄</a><br>
    <a href="saleRecord.do">銷售記錄</a><br>
    <a href="">兌換記錄</a><br>
    <a href="rewardRecord.do">贈送點數記錄</a><br>
  </td>   -->
</table>
</body>
</html>