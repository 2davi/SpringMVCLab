<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	파일 업로드 처리
	1. Front-end(FormUI)
		1) request body를 형성하기 위해 method(POST) 설정
		2) request body를 형성하기 위해 content-type 설정: enctype
		3) multipart content 형태로 전송됨 MIME: multipart/form-data
		4) Part 하나는 별도의 헤더를 가지고 있음.
			Content-Disposition: part name, file name;
			Content-Type: file-mime-type
			(헤더에 Content-Type이 없는 part는 문자열이겠거니 하고,(문자part로 인식)
			part name을 key로 두는 파라미터 맵으로 만들어줌.))
	2. Back-end (multipart request process) : multipart-config 설정 필요
		1)서블릿 스펙: 각 서블릿에 multipart-config 설정
			Part를 통해 하나의 파트를 캡슐화함.
			getPart(partName), Collection&lt;Part> getParts()
			문자기반 파트: getParameter... 그대로 사용.
			파일기반 파트: 서버사이드 특정 디렉토리에 저장(Part.write())
		2) Spring webMVC : DispatcherServlet에 multipart-config 설정.
			*** multipartResolver를 dispatcherServlet에 Bean 등록.
			원본 request를 wrapper request(MultipartHttpServletRequest)로 변경함.
			원본 Part 를 wrapper part로 변경함.
			
			MultipartFile 을 통해 하나의 파트를 캡슐화함.
			핸들러메소드 인자 @RequestPart MultipartFile, MultipartFile[], CommandObject
			파일기반 파트: 서버사이드 특정 디렉토리에 저장(MultipartFile.transferTo).
			문자기반 파트: @RequestParam'
</pre>
<form method="post" enctype="multipart/form-data">
<pre>
	<input type="text" name="uploader" placeholder="업로더"/>
	<span>${errors.uploader }</span>
	<input type="file" name="uploadFile" placeholder="업로드파일" />
	<span>${errors.uploadFile }</span>
	<button type="submit">업로드</button>
</pre>
</form>
<c:if test="${not empty saveName }">
업로드된 파일명 : ${saveName }
</c:if>
</body>
</html>