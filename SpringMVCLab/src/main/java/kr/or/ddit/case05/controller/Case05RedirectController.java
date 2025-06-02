package kr.or.ddit.case05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 	PRG Pattern 
 */
@Slf4j
@Controller
@RequestMapping("/case05")
public class Case05RedirectController {
	

	@GetMapping("start02")
	public String start02(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modelInfo", "전달데이텅");
		return "redirect:/case05/dest02";
	}
	
	//	addFlashAttribute -> FlashMap 에 데이터를 저장.
	//	--> REDIRECT
	//	새 Model 생성 -> FlashMap에 저장된 flash attribute를 Model에 옮겨 담음.
	//	--> flash attribute 삭제 && View Layer 이동
	
	@GetMapping("dest02")
	public String dest02(Model model) {
		
		if(model.containsAttribute("modelInfo")) {
			log.info("꺼내 본 모델 속성: {}", (String)model.getAttribute("modelInfo"));
		}
		return "case05/view01";
		//이제 뷰레이어에서 attribute를 사용하고 나면,
		//Spring이 알아서 flash 시켜준다.
	}
	
}
