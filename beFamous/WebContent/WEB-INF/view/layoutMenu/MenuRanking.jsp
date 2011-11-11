<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

管理排行榜<br>
<a href="${pageContext.request.contextPath}/incomeOutgo.do">修改統計百分比</a><br>
<a href="${pageContext.request.contextPath}/queryAlbumWeekRankingForAdmin.do">修改專輯週榜</a><br>
<a href="${pageContext.request.contextPath}/querySongWeekRankingForAdmin.do">修改歌曲週榜</a><br>
<a href="${pageContext.request.contextPath}/queryCreatorWeekRankingForAdmin.do">修改創作人週榜</a><br>
<a href="${pageContext.request.contextPath}/queryAlbumMonthRankingForAdmin.do">修改專輯月榜</a><br>
<a href="${pageContext.request.contextPath}/querySongMonthRankingForAdmin.do">修改歌曲月榜</a><br>
<a href="${pageContext.request.contextPath}/queryCreatorMonthRankingForAdmin.do">修改創作人月榜</a><br>

編輯推薦管理<br>
<a href="${pageContext.request.contextPath}/forwardRecommendActivity.do">新增推薦專輯</a><br>
<a href="${pageContext.request.contextPath}/queryRecommendActivity.do">推薦專輯列表</a><br>

行銷活動管理<br>
<a href="${pageContext.request.contextPath}/forwardPromotionActivity.do">新增活動</a><br>
<a href="${pageContext.request.contextPath}/queryPromotionActivity.do">活動列表</a><br>
</body>
</html>