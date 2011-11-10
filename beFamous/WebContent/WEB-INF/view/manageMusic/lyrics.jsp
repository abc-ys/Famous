<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
${song.name}<p>
作曲人:&nbsp${song.composer}&nbsp&nbsp作詞人:&nbsp${song.lyricist}&nbsp&nbsp製作人:&nbsp${song.producer}<p>
${song.lyrics}
</body>
</html>