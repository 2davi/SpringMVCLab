package kr.or.ddit.case06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller	//log에 매핑정보가 없다면, Bean으로 등록을 안 했다는 소리
@RequestMapping("/case06/calculate")
public class Case06CalculateController {

//	Handler Psudo Code
//	1. form으로 연결:  GET
//	2. form-data 처리: POST
//	3. 연산 결과: GET
	
	@GetMapping
	public String formUI(Model model) {
		log.info("result: {}", model.getAttribute("result"));
		return "case06/calForm"; //<--views JSP파일 
	}
	@PostMapping	//PRG패턴
	public String formData(
			RedirectAttributes redirectAttributes
			, @RequestParam int op1
			, @RequestParam int op2
	) {
		int result = op1 + op2;
		//먼저, FlashMap에 저장
		//REDIRECT하는 순간에 Model에 옮겨담고, FlashMap 비우기
		redirectAttributes.addFlashAttribute("result", result);
		return "redirect:/case06/calculate"; //<--Mapping 주소
	}
	@GetMapping("result")
	public String handler03(/*결과값 확인하려고 호출*/Model model) {
		log.info("result: {}", model.getAttribute("result"));
		return "case06/result";	//<--views JSP파일
	}
	
}
