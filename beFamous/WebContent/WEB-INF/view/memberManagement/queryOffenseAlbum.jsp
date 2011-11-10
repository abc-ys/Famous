<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
</head>
<body>
	<form name="fm" method="post">
	<h3>創作人被檢舉清單</h3>
			<table width="1000">
				<td>
					<div id="navi" class="navigation">
						<a href="#" title="queryOffenseAlbum" onclick="display('queryOffenseAlbum')">專輯</a> 
						<a href="#" title="queryOffenseSong" onclick="display('queryOffenseSong')">歌曲</a>
					</div>
				</td>
				<tr>
				<td>
					<div id="queryOffenseAlbum">
					<br>
						<table border="1" BorderColor="#000000" cellpadding="0"
								cellspacing="0">
							<tr>
								<td Width="80" Height="35" valign="top"><font size="2">序號</font></td>
								<td valign="top" Width="140"><font size="2">專輯名稱</font></td>
								<td valign="top" Width="80"><font size="2">被檢舉次數</font></td>
								<td valign="top" Width="140"><font size="2">專輯審核狀態</font></td>
								<td valign="top" Width="100"><font size="2">詳細清單</font></td>
							</tr>

							<c:forEach var="hm" items="${offenseAlbums}" varStatus="status">
								<tr>
									<td Height="35"><font size="2">${status.index+1}</font></td>
									<td><font size="2"><a href="javascript:void(0)"
											onclick="album('${hm[0].albumID}')">${hm[0].name}</a></font></td>
									<td><font size="2">${hm[1]}</font></td>
									<td><font size="2">${hm[0].status}</font></td>
									<td><input type="button"  value="詳細清單" onclick="offReason('${hm[0].albumID}')"></td>
								</tr>
							</c:forEach>
						</table>							
					</div>
					<div id="queryOffenseSong" style="display: none">
					<br>
						<table border="1" BorderColor="#000000" cellpadding="0"
							cellspacing="0">
							<tr>
								<td Width="80" Height="35" valign="top"><font size="2">序號</font></td>
								<td valign="top" Width="140"><font size="2">曲名</font></td>
								<td valign="top" Width="140"><font size="2">專輯名稱</font></td>
								<td valign="top" Width="80"><font size="2">被檢舉次數</font></td>
								<td valign="top" Width="140"><font size="2">專輯審核狀態</font></td>
								<td valign="top" Width="100"><font size="2">詳細清單</font></td>
							</tr>
							<c:forEach var="hm2" items="${offenseSongs}" varStatus="status2">
								<tr>
									<td Height="35"><font size="2">${status2.index+1}</font></td>
									<td><font size="2"><a href="javascript:void(0)">${hm2[0].name}</a></font></td>
									<td><font size="2"><a href="javascript:void(0)"
											onclick="album('${hm2[0].album.albumID}')">${hm2[0].album.name}</a></font></td>
									<td><font size="2">${hm2[1]}</font></td>
									<td><font size="2">${hm2[0].album.status}</font></td>
									<td><input type="button"  value="詳細清單" onclick="offReason('${hm2[0].songID}')"></td>			
								</tr>
							</c:forEach>
						</table>
					</div>
				</td>
			</table>
		</form>
</body>
<script>
function display(divname){
	if(divname=='queryOffenseAlbum'){
		$("#queryOffenseAlbum").toggle();
		$("#queryOffenseSong").hide();
	}
	if(divname=='queryOffenseSong'){
		$("#queryOffenseSong").toggle();
		$("#queryOffenseAlbum").hide();
	}
}

function album(albumId) {
	window.open("${pageContext.request.contextPath}/queryAlbumData.do?albumid="+albumId);
}
function offReason(id) {
	window.open("${pageContext.request.contextPath}/offenseReason.do?id="+id);
}
</script>
</html>