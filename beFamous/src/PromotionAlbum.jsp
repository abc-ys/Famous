<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
專輯大力推
<table>
    <tr>
    <c:forEach var="album" items="${arAlbum}">
 		<td><img alt="" src="${album.cover}"  width="190"　height="80"></img><br><a href="queryAlbumData.do?albumid=${album.albumID}">album ${album.name}</a><br><a href="">artist ${album.creator.userName}</a><br>date${album.date}</td>
 	</c:forEach>
 	</tr>
</table>
</body>
</html>