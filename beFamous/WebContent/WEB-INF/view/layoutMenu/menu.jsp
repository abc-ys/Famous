<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<table>
  <tr>
  <td>
    <h4>會員管理</h4>
    <a href="${pageContext.request.contextPath}/editMemberData.do">個人資料編輯</a><br>
    <a href="${pageContext.request.contextPath}/editMemberAccount.do">帳戶資料編輯</a><br>
  </td>
  <tr>
  <td>
    <h4>我的帳本</h4>
    <a href="${pageContext.request.contextPath}/incomeOutgo.do">GSiMoney收支表</a><br>
    <a href="${pageContext.request.contextPath}/prePayRecord.do">儲值記錄</a><br>
    <a href="${pageContext.request.contextPath}/payRecord.do">GSiMoney消費記錄</a><br>
    <a href="${pageContext.request.contextPath}/cashPay.do">現金消費記錄</a><br>
    <a href="${pageContext.request.contextPath}/saleRecord.do">銷售記錄</a><br>
    <a href="${pageContext.request.contextPath}/exchangeRecord.do">兌換記錄</a><br>
    <a href="${pageContext.request.contextPath}/rewardRecord.do">贈送點數記錄</a><br>
  </td>  
  <tr>
   <td>
    <h4>音樂管理</h4>
     <a href="${pageContext.request.contextPath}/addAlbum.do?creatorId=2">新增專輯</a><br>
    <a href="${pageContext.request.contextPath}/editAlbum.do?creatorId=2">編輯專輯</a><br>
  </td>
  <tr>
  <td>
    <h4>交友圈管理</h4>
    <a href="${pageContext.request.contextPath}/myFriendList.do">好友列表</a><br>
    <a href="${pageContext.request.contextPath}/myLikeCreatorList.do">喜愛創作人列表</a><br>
  </td>
  <tr>
  <td>
    <h4>其他管理</h4>
    <a href="${pageContext.request.contextPath}/forwardAddBanner.do">申請廣告刊登</a><br>
    <a href="${pageContext.request.contextPath}/addNews.do">訊息刊登</a><br>
    <a href="${pageContext.request.contextPath}/queryNews.do">查詢刊登訊息</a><br>
  </td>
  <tr>
  <!--
  <td>
    <h4>登記記憶卡</h4>
    <a href="">記憶卡清單</a><br>
    <a href="">下載管理區</a><br>
  </td> -->
</table>
</body>
</html>