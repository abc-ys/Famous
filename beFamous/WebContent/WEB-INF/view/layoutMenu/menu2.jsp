<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<table>
  <tr>
  <td>
    <a href="${pageContext.request.contextPath}/manageMusicCategory.do?adminId=2">音樂類別管理</a><br>
  </td>
  <tr>
  <td>
    <h4>訂單管理</h4>
    <a href="${pageContext.request.contextPath}/manageCashOrder.do?adminId=2">現金訂單</a><br>
    <a href="${pageContext.request.contextPath}/manageGSiMoneyOrder.do?adminId=2">GSiMoney訂單</a><br>
    <!-- <a href="">運費規則登錄</a><br> -->
  </td>
  <tr>
  <td>
    <h4>帳務管理</h4>
    <a href="${pageContext.request.contextPath}/billRegister.do?adminId=2">訂單發票登錄</a><br>
    <a href="${pageContext.request.contextPath}/exchangeCash.do?adminId=2">兌換金額管理</a><br>
  </td>
  <tr>
  <td>
    <h4>商品管理</h4>
    <a href="${pageContext.request.contextPath}/addProductData.do">新增商品資料</a><br>
    <a href="${pageContext.request.contextPath}/manageProductCategory.do">商品類別管理</a><br>
    <a href="${pageContext.request.contextPath}/queryProduct.do">查詢商品資料</a><br>
  </td>
  <tr>
  <td>
    <h4>檢舉內容管理</h4>
    <a href="${pageContext.request.contextPath}/offenseArea.do?adminId=2">查詢檢舉內容</a><br>
    <a href="${pageContext.request.contextPath}/systemAutoHidden.do?adminId=2">查詢系統自動隱藏內容</a><br>
    <a href="${pageContext.request.contextPath}/hiddened.do?adminId=2">查詢已被隱藏內容</a><br>
  </td>
  <tr>
  <td>
    <h4>會員管理</h4>
    <a href="${pageContext.request.contextPath}/manageMember.do?adminID=1">查詢會員資料</a><br>
  </td>
  <tr> 
  <td>
    <h4>廣告管理</h4>
    <a href="${pageContext.request.contextPath}/addAdBanner.do?adminID=1">新增廣告</a><br>
    <a href="${pageContext.request.contextPath}/queryAdBanner.do?adminID=1">查詢廣告內容</a><br>
    <a href="${pageContext.request.contextPath}/queryUploadAdBanner.do?adminID=1">查看上傳廣告內容</a><br>
  </td>
  <tr> 
  <td>
    <h4>關鍵字管理</h4>
    <a href="${pageContext.request.contextPath}/manageKeyWord.do?adminID=1">排除字詞組設定</a><br>
  </td>
  <tr> 
  <td>
    <h4>音樂管理</h4>
    <a href="${pageContext.request.contextPath}/queryAlbum.do?adminID=1">查詢專輯</a><br>
    <a href="${pageContext.request.contextPath}/querySong.do?adminID=1">查詢歌曲</a><br>
  </td>
  <tr> 
  <td>
    <h4>訊息管理</h4>
    <a href="${pageContext.request.contextPath}/addManagerNews.do?adminId=1">新增最新消息</a><br>
    <a href="${pageContext.request.contextPath}/queryManagerNews.do?adminId=1">查詢最新消息</a><br>
  </td>
  <tr> 
  <td>
    <h4>線上客服</h4>
    <a href="${pageContext.request.contextPath}/manageQuestion.do?adminId=1">問題管理</a><br>
  </td>
</table>
</body>
</html>