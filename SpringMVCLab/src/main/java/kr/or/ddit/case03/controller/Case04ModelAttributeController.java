package kr.or.ddit.case03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**	이 메소드를 실행하는 건 HandlerAdapter이다.
 * 	ANNOTATION으로 MODEL과lvn, MODELANDVIEW이 하는 일을
 * 	몽땅 처리하겠다.
 * 	
 * 	이 방식을 썼을 때의 장점::
 * 		- 여러 개의 핸들러에서 공통된 모델을 중복해서 전달할 때
 */
@Controller
@RequestMapping("/case04")
public class Case04ModelAttributeController {

	@ModelAttribute(/*KEY*/"modelAttr")
	//===model.addAttribute(KEY, VALUE);
	public String modelAttr() {
		return /*VALUE*/"MODEL INFO";
	}
	
	@GetMapping("model03")
	public String handler03() {
		return "case04/view01";
	}
	
	@GetMapping("model04")
	public String handler04() {
		return "case04/view01";
	}
}
