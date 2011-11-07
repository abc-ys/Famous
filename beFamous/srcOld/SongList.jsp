<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<tr>
<td>
<table border="1">
<tr>
<td>1</td>
<td></td>
<td>${song.name}</td>
<td>1</td>
<td><input type="button" name="like" value="like">
    <input type="button" name="p_Price" value="$${profile[1].songPrice.price}">
    <input type="button" name="price" value="$${song.songPrice.discountPrice}+${song.songPrice.discountBonus}">
</td>
</tr>

<tr>
<td>2</td>
<td></td>
<td>BBB </td>
<td>1</td>
<td><input type="button" name="like" value="like">
    <input type="button" name="p_Price" value="$20">
    <input type="button" name="price" value="$15+5">
</td>
</tr>

</table>
</td>

<td>
</td>

</tr>
</table>



</body>
</html>


</body>
</html>