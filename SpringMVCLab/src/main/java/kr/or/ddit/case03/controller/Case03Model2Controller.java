package kr.or.ddit.case03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 	스프링 내부에서 logical view name의 사용방법
 * 	ViewResolver 구현체에 의해 결정됨
 * 	1. InternalResourceViewResolver
 * 		: jsp view로 응답을 전송하고, prefix와 suffix로 jsp 위치를 일괄 관리할 수 있음.
 * 		: thymeleaf view로  응답을 전송하고, prefix와 suffix로 thymeleaf 위치를 일괄 관리할 수 있음.
 * 		: mustache view로 응답을 전송하고, prefix와 suffix로 mustache 위치를 일괄 관리할 수 있음.
 * 
 * 	2. ContentNegotiatingViewResolver
 * 		: Request "Accept"헤더를 기반으로 응답 데이터의 MIME을 결정,
 * 		 그에 맞는 View(ex; GsonView)를 사용하기 위한 전략 객체.
 */
@Slf4j
@Controller//컨트롤러의 기능 구현 + 핸들러매핑에게 맾잉
@RequestMapping("/case03")//상위 컨테이너에게 널 알리고 싶어^0^
public class Case03Model2Controller {

	@RequestMapping("/handler01")
	public String handler01() {
		log.info("▒\n▒ case03/handler01 수신");
		String lvn = "case03/view01";
		return lvn;
	}
	
	@RequestMapping("/handler02")
	public ModelAndView handler02() {
		log.info("▒\n▒ case03/handler02 수신");
		String lvn = "case03/view02";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(lvn);
		return mav;
	}
}
