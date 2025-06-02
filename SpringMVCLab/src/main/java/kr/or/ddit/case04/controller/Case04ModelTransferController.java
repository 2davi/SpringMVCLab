package kr.or.ddit.case04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 	/case04/model01(GET) 요청을 수신하고,
 * 	최종 응답은 "case04/view01"에서 전송함
 * 
 * 	맨 처음에는 서블릿 하나로 Model1이었다가, 책임을 쪼갬
 * 	그리고 MVC패턴
 * 	Layered Architecture
 * 	이번에는 전면부 Layer를 쪼갬.
 * 
 * 	■□■ Model, ModelAndView, @ModelAttribute 사용 방법
 */
@Controller
@RequestMapping("/case04")
public class Case04ModelTransferController {
	
	/* 	model은 call by reference로 사용하고,
	 * 	logical view name은 return타입으로 지정했다.
	 */
	@GetMapping("model01")
	public String handler01(Model model) {
		String modelAttr = "MODEL INFO";
		model.addAttribute("modelAttr", modelAttr);
		return "case04/view01";
	}
	
	
	/*	handler01과 똑같은 방식.
	 * 	다만 어떤 객체를 사용하느냐에 따라서
	 * 	동시에 return타입으로 내보내줄 건지,
	 * 	아니면 위에서처럼 CBR 방식을 쓸 건지
	 * 	달라지는 차이일 뿐^0^
	 * 
	 * 	스프링이 지원하는 방식이 너무 많아.
	 * 	그걸 다 외우려고 시도하는 건 멋쟁이 개발자
	 * 	HandlerAdapter가 어떤 역할을 하고, 우리는 무얼 걔한테 넘겨줘야 하는지를 아는 것이 중요하다.
	 */
	@GetMapping("model02")
	public ModelAndView handler02() {
		
		ModelAndView mav = new ModelAndView();
		
		String modelAttr = "MODEL INFO";
		mav.addObject("modelAttr", modelAttr);
		
		String lvn = "case04/view01";
		mav.setViewName(lvn);
		
		return mav;
	}
}
