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
 * 	*** 최종적으로 HandlerAdapter 에게 Model과 View에 대한 정보를 전달하는 방법.
 * 	■□■ Model, ModelAndView, @ModelAttribute 사용 방법
 * 	■ model
 * 		: call by reference 형태로 전달하기 위해 핸들러메소드의 argument로 Model을 집어넣음.
 * 		: view name은 반환값의 형태로 전달. ?
 * 		: json으로 전달할 때는 view name이 필요가 없지. 그럴 땐 void처리해도 좋고, 그냥 return값을 무시한다.
 * 	■ model&view
 * 		: ModelAndView 라는 반환객체로 한 번에 전달.
 * 	■ @ModelAndView
 * 		: 주로 위의 두 가지 중 하나를 선택해서 병행하여 쓴다.
 * 		: 하나의 컨트롤러에 여러 핸들러가 있을 때 + 각 핸들러가 한 모델을 공통으로 사용할 때.
 * 		: model을 반환하는 메소드를 따로 하나 만든다.
 * 		: 해당 메소드의 반환타입에 @ModelAndView를 사용함.
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
