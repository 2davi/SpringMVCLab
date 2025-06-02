package kr.or.ddit.case05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 	start01을 거쳐서 dest01로 이동한 다음에 view로 갈 생각.
 */
@Slf4j
@Controller
@RequestMapping("/case05")
public class Case05ForwardingController {
	
	@RequestMapping("start01")
	public String start01(Model model) {
		model.addAttribute("modelInfo", "전달할 모델입니다^0^");
		return "forward:/case05/dest01";
	}
	
	@RequestMapping("dest01")
	public String dest01(@RequestAttribute String modelInfo) {
		log.info("전달된 모델: {}", modelInfo);
		return "case05/view01";
	}
}
