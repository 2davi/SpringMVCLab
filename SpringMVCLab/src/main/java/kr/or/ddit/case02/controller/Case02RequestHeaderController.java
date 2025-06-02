package kr.or.ddit.case02.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case02")
public class Case02RequestHeaderController {
	
	@GetMapping("header01")
	public void handler01(HttpServletRequest request) {
		log.info("case02/header01 수신, accept header: {}"
				, request.getHeader("accept"));
	}
	
	/** 사실, 우리한테 request가 통으로 필요하진 않았다.
	 *  그냥 헤더값만 누가 가져와주면 좋을 듯 ^0^ */
	@GetMapping("header02")
	public void handler02(@RequestHeader(value = "accept", required = false, defaultValue="*/*") String accept) {
		log.info("case02/header01 수신, accept header: {}"
				, accept);
	}
	
	/**CoC, 매핑값이 없으면 관례대로 accept헤더를 가져옴*/
	@GetMapping("header02_1")
	public void handler02_1(@RequestHeader String accept) {
		log.info("case02/header02_1 수신, accept header: {}"
				, accept);
	}
	
	/**CoC, 쩌리 헤더들은 이름 명시 해주어야 함 */
	@GetMapping("header03")
	public void handler03(@RequestHeader("accept-language") String acceptLanguage) {
		log.info("case02/header03 수신, accept-language header: {}"
				, acceptLanguage);
	}
	
	/** 하나의 헤더 타이틀에 여러개의 값이 들어올 수 있다. MVM */
	@GetMapping("header04")
	public void handler04(@RequestHeader MultiValueMap<String, String> headers) {
		log.info("case02/header04 수신, headers: {}"
				, headers);
	}
	
	@GetMapping("header05")
	public void handler05(@RequestHeader HttpHeaders headers) {
		log.info("case02/header05 수신, headers: {}"
				, headers);
	}
	
	@GetMapping("header06")
	public void handler06(@RequestHeader String cookie) {
		log.info("case02/header06 수신, Cookie: {}", cookie);
	}
	
	@GetMapping("header07")			  //이름이 변수명과 같으면 리플렉션으로 생략^0^
									  //required속성: true_꼭있어야해 (404), false_없어도되는데가져와봐
									  //			: default TRUE
	public void handler07(@CookieValue /* (value="dummyCookie") */(required=false) String dummyCookie) {
		log.info("case02/header07 수신, dummyCookie: {}", dummyCookie);
	}
}
