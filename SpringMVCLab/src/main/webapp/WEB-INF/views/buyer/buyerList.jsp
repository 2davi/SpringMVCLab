<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>제조사목록</title>
<script>
document.addEventListener("DOMContentLoaded", () => {
	let prodList = 
	console.log("체킁")
	console.log(prodList);
});
</script>
</head>
<body>
<main class="container">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>제조사명</th>
				<th>분류명</th>
				<th>소재지</th>
				<th>전화번호</th>
				<th>담당자</th>
				<th>거래은행</th>
				<th>거래품목수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty buyerList}">
				<tr>
					<td colspan="7">아직 거래처 없음 ㅗ^ㅂ^ㅗ</td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${buyerList}" var="buyer">
					<c:url value="/buyer/buyerDetail.do" var="detailUrl">
						<c:param name="what" value="${buyer.buyerId }" />
					</c:url>
					<c:url value="/buyer/buyerDetail.do" var="detailUrl2">
						<c:param name="what" value="${buyer.buyerId }" />
					</c:url>
					<tr>
						<td>
						<a href="${detailUrl }">${buyer.buyerName }</a>
						</td>
						<td>${buyer.lprod.lprodName }</td>
						<td>${buyer.buyerAdd1 }</td>
						<td>${buyer.buyerComtel }</td>
						<td>${buyer.buyerCharger }</td>
						<td>${buyer.buyerBank }</td>
<%-- 					<c:set scope="page" var="cnt" value="0" />
					<c:forEach items="${buyer.prodList }" var="prod">
						<c:set scope="page" var="cnt" value="${cnt + 1 }" />
					</c:forEach>

						<td>${cnt}</td> --%>
						<td>${buyer.prodList.size() }</td>
					</tr>
				</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</main>
</body>
</html>