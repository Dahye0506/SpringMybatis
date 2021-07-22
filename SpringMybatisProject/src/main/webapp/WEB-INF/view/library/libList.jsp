<%@page import="org.apache.velocity.runtime.directive.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ Include file="../include/includeTags.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>libList.jsp</title>
</head>
<body>
<table border="1">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>등록일</td>
		<td>조회수</td>
	</tr>
	<c:forEach items="${list }" var="dto" varStatus="cnt">
	<tr>
		<td>${cnt.Count }</td>
		<td><a href="libraryInfo?noticeNo=${dto.noticeNo }">${dto.noticeSub }</a></td>
		<td>${dto.noticeDate }</td>
		<td>${dto.noticeCount }</td>
	</tr>
	</c:forEach>

</table>

<a href="libRegist">등록</a>

</body>
</html>