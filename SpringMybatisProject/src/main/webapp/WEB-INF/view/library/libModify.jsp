<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="libModify" method="post">
<input type="hidden" value="${dto.noticeNo }" name="noticeNo" />
글번호 : ${dto.noticeNo }<br />
제목 : <input ytpe="text" value="${dto.noticeSub }" name="noticeSub"><br />
내용 : <input ytpe="text" value="${dto.noticeCon }" name="noticeCon"><br />
등록일 : ${dto.noticeDate }<br />
조회수 : ${dto.noticeCount }<br />
작성자 : ${dto.employeeId }<br />
<input type="submit" valu="게시글 수정" />
<input type="button" value="삭제" onclick="javascript:location.href='libDel?noticeNo=${dto.noticeNo }'"/>
</form>
</body>
</html>