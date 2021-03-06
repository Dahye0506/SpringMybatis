<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="goodsUpdate" name="frm" method="post" modelAttribute="goodsCommand" enctype="multipart/form-data">
<input type="hidden" name="prodNum" value="${goodsCommand.prodNum }"/>
<input type="hidden" name="prodName" value="${goodsCommand.prodName }"/>
상품번호 : ${goodsCommand.prodNum }<br />
상품명 : ${goodsCommand.prodName }<br />
가격 : <input type="number" name="prodPrice" min="0" step="1" value="${goodsCommand.prodPrice }"/>
      <form:errors path="prodPrice"/><br />
규격 : <input type="number" name="prodCapacity" min="0" value="${goodsCommand.prodCapacity }"/>
	  <form:errors path="prodCapacity"/><br />
공급처 : <input type="text" name="prodSupplyer" value="${goodsCommand.prodSupplyer }"/>
	  <form:errors path="prodSupplyer"/> <br />
배송비 : <input type="number" name="prodDelFee" min="0" step="100" value="${goodsCommand.prodDelFee }"/>
		<form:errors path="prodDelFee"/><br />
추천 여부 :
	<input type="radio" name="recommend" value="Y" 
	<c:if test="${goodsCommand.recommend == 'Y'}">checked</c:if> /> 추천
	
	<input type="radio" name="recommend" value="N" 
	<c:if test="${goodsCommand.recommend == 'N'}">checked</c:if> /> 비추천
		<br />
카테고리 : <select name="ctgr">
			<option value="wear" 
			<c:if test="${goodsCommand.ctgr == 'wear'}">selected</c:if> >의류</option>
			<option value="cosmetic" 
			<c:if test="${goodsCommand.ctgr == 'cosmetic'}">selected</c:if>>화장품</option>
			<option value="food"
			<c:if test="${goodsCommand.ctgr == 'food'}">selected</c:if>>음식</option>
			<option value="car"
			<c:if test="${dto.ctgr == 'car'}">selected</c:if>>자동차 용품</option>
		</select> <br /> 
상세내용 : <textarea rows="5" cols="50" name="prodDetail">${goodsCommand.prodDetail }</textarea>
		<form:errors path="prodDetail"/><br />
파일<br />
<c:forTokens items="${goodsCommand.prodImage }" delims="," var="prodImage">
<p>
	<span id="fileName">${prodImage }</span>
	<input type="button" id="btn" value="삭제" onclick="fileDel(this)"/>
	</p>
</c:forTokens>
파일추가 : 
<input type="file" name="prodImage" multiple="multiple" />

<!-- delims는 이미지를 한번에 불어오면 에러발생 그래서 이미지마다 ,로 구분 -->
<input type="hidden" id="fileDel1" name="fileDel1" />
<input type="submit" value="수정하기" />
<input type="button" value="삭제하기" onclick="javascript:location.href='goodsDel?prodNum=${goodsCommand.prodNum }'" />
<input type="button" value="리스트" />
</form:form>


<!-- 이미지 삭제버튼 스크립트 -->
<script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.2.1.js"></script>
<script type="text/javascript">
function fileDel(btn){
	var delFile = $("#fileDel1").val()
	if($(btn).attr("value") == "삭제"){
		$(btn).attr("value","삭제취소");
		$("#fileDel1").val($(btn).parent().children("#fileName").text().trim() + "," + delFile)
	}else{
		$(btn).attr("value","삭제");
		fileName = $(btn).parent().children("#fileName").text().trim()+",";
		$("#fileDel1").val(delFile.replace(fileName,""));
	}
}



</script>
</body>
</html>