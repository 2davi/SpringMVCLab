<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProdDetail.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/bootstrap-5.3.6-dist/css/bootstrap.min.css" />
</head>
<body>
<main class="container">
<h4>상품 상세 조회</h4>

<table class="table table-bordered">
	<tr><th>상품코드</th><td>${prod.prodId}</td></tr>
	<tr><th>상품명</th><td>${prod.prodName}</td></tr>
	<tr><th>상품분류</th><td>${prod.lprod.lprodName}</td></tr>
	<tr>
		<th>제조사</th>
		<td><table class="table">
			<thead>
				<tr>
					<td>제조사명</td>
					<td>소재지</td>
					<td>전화번호</td>
					<td>담당자</td>
				</tr>
			</thead>
			<tbody>
				<c:set value="${prod.buyer }" var="buyer" />
				<c:url value="/buyer/buyerDetail.do" var="detailURL">
					<c:param name="what" value="${buyer.buyerId }" />
				</c:url>
				<tr>
					<td>
						<a href="${detailURL }">${buyer.buyerName }</a>
					</td>
					<td>${buyer.buyerAdd1 } ${prod.buyer.buyerAdd2 }</td>
					<td>${buyer.buyerComtel }</td>
					<td>${buyer.buyerCharger }</td>
				</tr>
			</tbody>
		</td></table>
	</tr>
	<tr>
		<th>매입단가</th>
		<td>${prod.prodCost}</td>
	</tr>
	<tr><th>매출단가</th><td>${prod.prodPrice}</td></tr>
	<tr><th>할인판매단가</th><td>${prod.prodSale}</td></tr>
	<tr><th>개요(대충설명)</th><td>${prod.prodOutline}</td></tr>
	<tr><th>상세(자세한설명)</th><td>${prod.prodDetail}</td></tr>
	<tr><th>상품이미지</th><td>${prod.prodImg}</td></tr>
	<tr><th>전체재고량</th><td>${prod.prodTotalstock}</td></tr>
	<tr><th>입고일자</th><td>${prod.prodInsdate}</td></tr>
	<tr><th>적정재고</th><td>${prod.prodProperstock}</td></tr>
	<tr><th>크기</th><td>${prod.prodSize}</td></tr>
	<tr><th>색상</th><td>${prod.prodColor}</td></tr>
	<tr><th>배달유의사항</th><td>${prod.prodDelivery}</td></tr>
	<tr><th>판매단위</th><td>${prod.prodUnit}</td></tr>
	<tr><th>포장수량</th><td>${prod.prodQtyin}</td></tr>
	<tr><th>판매단위(수량)</th><td>${prod.prodQtysale}</td></tr>
	<tr><th>마일리지</th><td>${prod.prodMileage}</td></tr>
</table>
</main>
<script src="<c:url value='/resources/bootstrap-5.3.6-dist/js/bootstrap.bundle.min.js'/>"></script>
</body>
</html>