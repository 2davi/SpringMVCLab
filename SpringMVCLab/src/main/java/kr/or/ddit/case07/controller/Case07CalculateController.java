package kr.or.ddit.case07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.case07.vo.CalVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 	피연산자 두 개를 입력받고, 더하기 연산의 결과를 생성함.
 * 	두 개의 피연산자는 모두 양의 정수. (양의 정수만 취급)
 * 	커맨드 오브젝트와 Spring의 검증 모델(BindingResult 사용)을 사용함.
 * 	form UI는 form 커스텀 태그 사용.
 * 
 */
@Slf4j
@Controller
@RequestMapping("/case07/calculate")
public class Case07CalculateController {
	static final String MODELNAME = "cal";
	
	@ModelAttribute(MODELNAME)
	public CalVO cal() {
		return new CalVO();
	}
	
	@GetMapping
	public String formUI() {
		
		return "case07/calculate";
	}
	@PostMapping
	public String executeCalculate(
			@Valid @ModelAttribute(MODELNAME) CalVO cal
			, BindingResult errors
			, RedirectAttributes redirectAttributes
	) {
		if(errors.hasErrors()) {
			//Error를 받아서 속성에 등록시켜주고
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(errorsName, errors);
		} else {
			cal.setResult(cal.getOp1() + cal.getOp2());
		}
		//redirect:컨트롤러매핑주소
		redirectAttributes.addFlashAttribute(MODELNAME, cal);
		return "redirect:/case07/calculate";
	}
}
