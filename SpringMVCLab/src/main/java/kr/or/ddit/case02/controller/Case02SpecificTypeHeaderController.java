package kr.or.ddit.case02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 	"기존까지 컨트롤러가 일정부분 담당하던 검증의 영역이
 * 	스프링에서는 핸들러어댑터의 몫으로 넘어오게 되었다"
 * 										--Professor. Choi...
 * 	요청 헤더를 수신하고, 그걸 파싱하고, 핸드럴메소드를 실행하는 건??
 * 	HandlerAdapter에 의해 처리됨.
 * 	요청 헤더 수신: @RequestHeader(value(선생님은name), required, defaultValue)
 * 	파싱?? : 핸들러메소드 argument 타입으로 파싱 타입이 결정됨^0^
 * 	ex) @RequestHeader(name="myAge", required=false, defaultValue="34") int myAge
 * 
 * 	HandlerAdapter
 * 		: HandlerMapping 핸들러를 검색하면, HandlerAdapter가 POJO 기반의 핸들러를 실행하는 역할을 함.
 */
@Slf4j
@Controller
@RequestMapping("/case02")
public class Case02SpecificTypeHeaderController {
	
	@GetMapping("header08")
	public void header01(
		//my-age 헤더가 없을 땐 int myAge에 null값이 들어오려고 할 텐데.. 기본형이니까 에러나겠찌^0^
		//그래서 defaultValue가 필요하다우!!!!
		@RequestHeader(value = "my-age", required = false, defaultValue = "3")
		int myAge
	) {
		
	}
	
	/**
	 * 	"my-flag"라는 요청 헤더를 수신하려고 한다.
	 * 	옵셔널 헤더이고, 헤더값으로 TRUE/FALSE만 받는다.
	 * 	생략된 경우 기본으로 TRUE를 수신한다.
	 */
	@GetMapping("header09")
	public void header02(
			@RequestHeader(value = "my-flag", required = false, defaultValue = "true")
			boolean myFlag
	) {
		log.info("/case02/header09 수신, my-flag: {}", myFlag);
	}
}
