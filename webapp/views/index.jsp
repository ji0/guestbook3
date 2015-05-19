

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.sds.icto.guestbook.vo.GuestBookVO, java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="/guestbook3/insert" method="post">


		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right> <input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br>

	<c:forEach items='${list }' var="vo" varStatus="status">
	
		<table width=510 border=1>
			<br>
			<tr>
				<td>[ ${status.count } ]</td>
				<td>${vo.name }</td>
				<td>${vo.reg_date}</td>
				<td><a href="/guestbook3/delete?no=${vo.no}">삭제</a></td>
			</tr>
			<tr>
				<td colspan=4>${vo.message }</td>
			</tr>
			<br>
		</table>
	</c:forEach>


</body>
</html>