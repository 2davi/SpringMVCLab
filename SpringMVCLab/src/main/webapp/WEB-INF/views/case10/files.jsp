<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.uploaded{
		background-color: blue;
	}
</style>
</head>
<body>
<form method="POST" enctype="multipart/form-data">
	<input type="text" name="uploader" value="한글이름" hidden />
	<input type="file" name="uploadFile" />
	<button type="submit">업로드</button>
	<span>${errors.uploadFile }</span>
</form>


<ul>
		
		<c:forEach items="${fileNames }" var="file">
			<c:url value="/case10/files/${file}" var="detail" />
			<li class="<c:if test="${saveName eq file}">uploaded</c:if>">
<!-- 							   ${saveName eq file ? 'uploaded' : 'normal' } -->
				<a href="${detail }">${file }</a>
				<button class="del" data-name="${file }">삭제</button>
			</li>
		</c:forEach>
</ul>
<script>
document.addEventListener("DOMContentLoaded", async () => {
	$(".del").on("click", event => {
		const name = event.target.dataset.name;
		if(!confirm("싸울래?")) return;
		const resp = await axios.delete(`<c:url value="/case10/files/\${name}" />`);
		const {success, target} = resp.data; //<--기;억 안남;
		if(success) {
			$(event.target).closest("li") //.parents("li:first")
						   .remove();
		}
	});
});
</script>
</body>
</html>