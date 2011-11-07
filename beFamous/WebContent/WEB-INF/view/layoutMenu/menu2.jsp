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
    <!-- <a href="">運費規則登錄</a><br> -->
  </td>
  <tr>
  <td>
    <h4>帳務管理</h4>
    <a href="${pageContext.request.contextPath}/billRegister.do">訂單發票登錄</a><br>
    <a href="${pageContext.request.contextPath}/exchangeCash.do">兌換金額管理</a><br>
  </td>
  <tr>
  <td>
    <h4>商品管理</h4>
    <a href="${pageContext.request.contextPath}/addProductData.do">新增商品資料</a><br>
    <a href="${pageContext.request.contextPath}/manageProductCategory.do">商品類別管理</a><br>
    <a href="${pageContext.request.contextPath}/queryProductData.do">查詢商品資料</a><br>
  </td>
  <tr>
  <td>
    <h4>檢舉內容管理</h4>
    <a href="${pageContext.request.contextPath}/offenseArea.do">查詢檢舉內容</a><br>
    <a href="${pageContext.request.contextPath}/systemAutoHidden.do">查詢系統自動隱藏內容</a><br>
    <a href="${pageContext.request.contextPath}/hiddened.do">查詢已被隱藏內容</a><br>
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
  <tr> 
  <td>
    <h4>關鍵字管理</h4>
    <a href="${pageContext.request.contextPath}/addAdBanner.do">排除字詞組設定</a><br>
  </td>
</table>
</body>
</html>