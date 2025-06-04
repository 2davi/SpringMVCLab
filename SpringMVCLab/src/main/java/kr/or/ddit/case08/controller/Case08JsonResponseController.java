package kr.or.ddit.case08.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/case08")
public class Case08JsonResponseController {

	// map to json; Map을 받아서 Json으로 변환시켜줌...
	// 20분 남겨두고 무슨 일인가
	// 지금은 model을 그냥 통으로 넘겨주고 있다.
	// ==>"한 개의 객체를 Marshalling해서 내보내는 핸들러"
	// 이걸 가능케하는
	// @ResponseBody = 
	//"핸들러의 리턴값을 Response의 Body에 담아서 보내겠다."
	/**
	 * ㅁㅊ
	 * @return
	 * 	Request Accept 헤더와 무관하게 마샬링이 처리됨.
	 * 	(있으면 더 안전한 게 맞지. 명확하니까^0^)
	 * 	어쩄든 ViewResolver를 전혀 사용하지 않고 있다.
	 * 	HandlerAdapter에 의해 response entity 가 처리되는 형태. <=== @ResponseBody
	 * 	마샬링의 대상이 되는 response entity 는
	 * 	핸들러 메소드의 리턴타입에 @ResponseBody 로 표현함.
	 */
	@GetMapping("json01")
	@ResponseBody
	public Map<String, Object> handler01() {
		Map<String, Object> original = new HashMap<>();
		original.put("prop1", "문자열");
		original.put("prop2", 23);
		original.put("prop3", new String[] {"a1", "b33"});
		return original;
	}
	
	// ViewResolver를 통해 GsonView.java를 사용하는 방법
	// Accept헤더가 존재해야만 제대로 json Marshalling을 한다.
	/**
	 * @param model
	 * 	Request Accept 헤더를 기준으로 content-type을 협상하는 방식
	 * 	{@link ContentNegotiatingViewResolver} 의 동작 방식에 따라
	 * 	{@link GsonView} 에서 Model을 대상으로 마샬링이 처리되는 방식
	 */
	@GetMapping("json02")
	public void handler02(Model model) {
		Map<String, Object> original = new HashMap<>();
		original.put("prop1", "문자열");
		original.put("prop2", 23);
		original.put("prop3", new String[] {"a1", "b33"});
		model.addAllAttributes(original);
	}
}
