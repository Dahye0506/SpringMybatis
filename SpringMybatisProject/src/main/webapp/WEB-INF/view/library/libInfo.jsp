<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../include/includeTags.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
글번호 : ${dto.noticeNo }<br />
제목 : ${dto.noticeSub }<br />
내용 : ${dto.noticeCon }<br />
등록일 : ${dto.noticeDate }<br />
조회수 : ${dto.noticeCount }<br />
작성자 : ${dto.employeeId }<br />
파일 : <!-- 출력 --><br/>
<c:forTokens items="${dto.noticeOrgFile }" delims="," var="fileName" varStatus="idx"  >

	<a href="fileDown?str=${dto.noticeFile.split(',')[idx.index] }&org=${fileName}">
</a>${fileName }<br/>
	<a href="fileDown?str=${dto.noticeFile.split(',')[idx.index] }">${fileName }</a><br/>
</c:forTokens>

<a href="libDetail?noticeNo=${dto.noticeNo }">수정</a>

<!-- 파일보기 -->


</body>
</html>