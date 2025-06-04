package kr.or.ddit.case07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.case07.vo.DummyVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 	*** case06에서의 조건 그대로 사용
 * 	문자로 전달된 name(필수), 숫자로 전달된 age(옵셔널)
 * 	문자들로 전달된 hobbies(옵셔널)
 * 
 * 	Command Object 를 이용한 form data 바인딩.
 * 	1. form UI 구성.
 * 	 1) controller 구현
 * 		- GetMapping 핸들러 메소드
 * 		- model attribute 를 전달해야 함.
 * 		- @ModelAndView 메소드를 구현, 모델 객체를 생성&&반환. 
 * 	 2) view 구현
 * 		- form 커스텀 태그 사용 ( @ModelAttribute )
 * 
 * 	2. form data 처리.
 * 	 1) PostMapping 핸들러 메소드
 * 		- 핸들러 메소드 argument 로 Command Object 사용 : 동시에 모델 속성으로도 저장됨(CoC 반영).
 * 		  @ModelAttribute 로 속성명 변경 가능.
 * 		- Command Object 검증
 * 		  @Valid, @Validated(group hint) 와 Errors(;BindingResult) 활용
 * 		  ex) public String handlerMethod( @Valid CommandObject co, BindingResult errors ) {...}
 * 			  주의! 검증 결과(Errors)는 검증 대상인 Command Object 의 바로 다음 인자로 받아야 함.
 * 		- 검증 실패 시 form UI로 redirection 이동.
 * 		- 검증 대상(co)과 검증 결과(errors)를 플래시속성으로 저장해야 함.
 * 			  Spring CustomTag 와 사용하기 위해서...
 * 			  주의! 검증 결과의 모델명은 BindingResult.MODEL_KEY_PREFIX + "co모델 속성명"
 */
@Slf4j
@Controller
@RequestMapping("/case07")
public class Case07CommandObjectController {
	
	@ModelAttribute("dummy")
	public DummyVO dummy() {
		log.info("■□■ dummy 메소드 실행 및 'dummy' 속성 생성");
		return new DummyVO();
	}
	
	
	@GetMapping("dummyForm")
	public String formUI(/** @ModelAttribute("dummy") DummyVO dummy */) {
		log.info("■□■ dummyForm 핸들러 메소드 실행");
		return "case07/form";
	}
	
	
	
	@RequestMapping("commandObject01")
	public String handler01(
			@ModelAttribute("dummy") DummyVO dummy
			/* , Model model */
	) {
		log.info("dummy vo: {}", dummy);
		/* model.addAttribute("dummy", dummy); */
		return "case07/view01";
	}
	
	//검증 구조를 구현한 handler02
	@RequestMapping("commandObject02")
	public String handler02(
			//VO의 annotation대로 검증도 하고 값도 넣어줘^0^
			@Validated(InsertGroup.class) @ModelAttribute("dummy") DummyVO dummy
			//위 검증의 결과를 받으려면, 그냥 파라미터로 호출해^ㅂ^
			, /**Errors*/BindingResult errors
			, RedirectAttributes redirectAttributes
	) {
		if(errors.hasErrors()) {			
			redirectAttributes.addFlashAttribute("dummy", dummy);
			/** 상수 + attribute등록이름 구조로 에러메세지가 저장되고,
			 * 이를 커스텀태그로 읽어내는 프로세스. */
			String errorsName = BindingResult.MODEL_KEY_PREFIX + "dummy";
			// 하기 싫었죠? 여기에다가 dummy를 넣으면 어떻게 찾아 errors를 넣어야지
			// 내가 고쳐줬다 ㅎ << 옆자리에서
			redirectAttributes.addFlashAttribute(errorsName, errors);
			log.error("검증 실패, {}", errors);
			errors.getAllErrors().forEach(oe -> {
				log.error("{}", oe);
			});
			return "redirect:/case07/dummyForm";
		} else {
			log.info("dummy vo: {}", dummy);
			return "case07/view01";
		}
	}
}
