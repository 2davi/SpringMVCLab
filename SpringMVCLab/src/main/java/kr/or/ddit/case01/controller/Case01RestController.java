package kr.or.ddit.case01.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 	/case01/rest/api로 발생하는 rest 요청에 대한 핸들러 매핑.
 * 	CRUD에 대한 요청 처리 가능해야함.
 * 	POST/PUT 요청의 경우, body 컨텐츠로 json을 수신함.
 * 
 * 	HandlerMapping 의 역할
 * 	1. 컨트롤러와 핸들러 메소드에 대한 정보를 수집하고, handler map을 형성함
 * 		- @Controller, @RequestMapping, @Get[ @Post...]Mapping 등의 어노테이션을 트레이싱함.
 * 		- 매핑 조건을 표현하는 속성들
 * 			)value: 매핑 url
 * 			)method: 매핑 request method (405)
 * 			)consumes: Request의 "Content-Type"헤더에 대한 매핑 (415)
 * 			)produces: Request의 "Accept"헤더에 대한 매핑 (406)
 * 	
 * 	2. Front Controller(DispatcherServlet)가 받은 요청을 처리할 수 있는 핸들러를 검색함.
 * 		-> 검색 실패한 경우, 4XX 계열의 상태코드로 응답이 전송됨.
 * 		-> 
 */

@Controller //스프링 컨테이너가 빈을 등록하는 조건으로 사용되고,
//handlerMappng에 의해 핸들러 메소드의 클래스로 수집됨
@RequestMapping(value = "/case01/rest/api"
				, produces = MediaType.APPLICATION_JSON_VALUE)
				//응답데이터는 JSON으로 나간다"Accept")
public class Case01RestController {
	
	@GetMapping	public void doGet() {}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
				//요청데이터는 JSON으로 받는다"Content-Type")
	public void doPost() {}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void doPut() {}
	
	@DeleteMapping
	public void doDelete() {}
	
	
	
//	//Get-다건조회
//	@GetMapping
//	public void getListHandler() {}
//	
//	//Get-단건조회
//	@GetMapping("{pk}")
//	public void getOneHandler() {}
//	
//	//Post
//	@PostMapping(
//			headers = "content-type=application/json"
//			)
//	public void postHandler() {}
//	
//	//Put
//	@PutMapping(
//			headers = "content-type=application/json"
//			)
//	public void putHandler() {}
//	
//	//Delete
//	@DeleteMapping("{pk}")
//	public void deleteHandler() {}
//	

}
