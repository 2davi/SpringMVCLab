<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="cal" 
	  action="${pageContext.request.contextPath }/case07/calculate">
	<form:input path="op1" type="number"/> + 
	<form:errors path="op1" cssClass="error"/>
	<form:input path="op2" type="number"/> =
	<form:errors path="op2" cssClass="error" />
	<form:input path="result" type="number" readonly="true" />
	<%-- <input type="number" name="result" value="${result }" readonly /><br/> --%>
	<form:button type="type">전송</form:button>
	<c:if test="${not empty cal}">
	연산결과: ${result }
	</c:if>
</form:form>
</body>
</html>