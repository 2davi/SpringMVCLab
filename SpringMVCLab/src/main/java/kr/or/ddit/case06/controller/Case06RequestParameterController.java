package kr.or.ddit.case06.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 	문자로 전달된 name(필수), 숫자로 전달된 age(옵셔널)
 * 	문자들로 전달된 hobbies(옵셔널)
 */
@Slf4j
@Controller
@RequestMapping("/case06")
public class Case06RequestParameterController {

	@GetMapping("parameter01")
	public String handler01(HttpServletRequest request) {
		String name = request.getParameter("name");
		if(StringUtils.isBlank(name)) { //(400)
			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
		}
		int age = Optional.ofNullable(request.getParameter("age"))
						  .map(Integer::parseInt)
						  .orElse(-1);
		String[] hobbies = request.getParameterValues("hobbies");
		log.info("name: {}", name);
		log.info("age: {}", age);
		log.info("hobbies: {}", Arrays.toString(hobbies));
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("hobbies", Arrays.toString(hobbies));
		return "case06/view01";
	}
	
	@GetMapping("parameter02")
	public String handler02(
			@RequestParam String name
			, @RequestParam(required=false, defaultValue="-1") int age
			, @RequestParam(required=false) String[] hobbies
			, Model model
	) {
//----	@RequestParam으로 아래 코드의 getter, 검증 과정이 해결됨. 
//		String name = request.getParameter("name");
//		if(StringUtils.isBlank(name)) { //(400)
//			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
//		}
//		int age = Optional.ofNullable(request.getParameter("age"))
//						  .map(Integer::parseInt)
//						  .orElse(-1);
//		String[] hobbies = request.getParameterValues("hobbies");
		log.info("name: {}", name);
		log.info("age: {}", age);
		log.info("hobbies: {}", Arrays.toString(hobbies));
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("hobbies", Arrays.toString(hobbies));
		return "case06/view01";
	}
	
	
	@RequestMapping("parameter03")
	public String handler03(
//----		그냥 Map을 통해 받으면 String[]로 받은 hobbies가 짤린다.
//			@RequestParam Map<String, String[]> parameters
			@RequestParam MultiValueMap<String, String> parameters
			, Model model
	) {
		log.info("parameters: {}", parameters);
//----	.addAllAttributes(COLLECTION); 
//		model.addAttribute("name", name);
//		model.addAttribute("age", age);
//		model.addAttribute("hobbies", Arrays.toString(hobbies));
		model.addAllAttributes(parameters);
		return "case06/view01";
	}
}
