<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form name="fm" method="post">
<br>
申請兌換
<p>
申請者:&nbsp${creator.userName}<p>
20111231止您可兌換的GSiMoney金額: 200元<p>
本次兌換GSiMoney金額:&nbsp<input type="text" name="gsiMoney">&nbsp元<br>
(匯款時將會扣除手續費30元)
<p>
<p>
付款資訊<p>
真實姓名/公司行號:&nbsp${creator.accountName}<br>
身分證字號/統一編號:&nbsp${creator.identityNO}<br>
通訊地址:&nbsp<input type="text" name="address" size="50" value="${creator.address}"><br>
行動電話:&nbsp<input type="text" name="tel" size="15" value="${creator.cellPhone}"><br>
其他電話:&nbsp<input type="text" name="tel2" size="15" value="${creator.tel}"><p>
若您為第一次請款，請於7個工作天內補齊身份證明文件<br>
(可採傳真或掃描圖檔寄至本公司email)<br>
傳真號碼:02-2657-9797<br>
email:ubn@mail.com<p>
銀行資訊<p>
帳戶名稱:&nbsp${creator.accountName}<br>
銀行名稱:&nbsp<input type="text" name="bank" size="15" value="${creator.bankName}"><br>
分行名稱:&nbsp<input type="text" name="branch" size="15" value="${creator.bankBranch}"><br>
銀行帳號:&nbsp<input type="text" name="code" size="15" value="${creator.accountNO}"><br>
<input type="checkbox" name="synUpdate" value="">將此處修正的資訊同步更新到付款資訊或銀行資訊<br>
<center><input type="submit" value="確定" onclick="apply()"/><input type="reset" value="取消" onclick="nO()"/></center>
<p>

</table>
</form>
</body>
<script>
function apply(){
     document.fm.action="${pageContext.request.contextPath}/incomeOutgo.do";
     document.fm.submit();
}
function nO(){
    document.fm.action="${pageContext.request.contextPath}/updateExchange.do";
    document.fm.submit();
}
</script>
</html>