package kr.or.ddit.case01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * 	컨트롤러를 bean으로 등록하고,
 * 	@Controller
 * 	해당 컨트롤러가 특정 요청을 수신할 수 있는 방법.
 * 	@RequestMapping(url,method,header,parameter)
 * 	*** 특정 요청에 대한 커맨트 핸들러를 메소드 기준으로 구현됨
 */
@Slf4j
@Controller //<--딴 bean등록 어노테이션은 상위컨테이너에 등록되는데,
			//왜째서 @Controller만 하위컨테이너에 등록되는 거지?
@RequestMapping("/case01")
public class Case01ReceiveRequestController {

	@RequestMapping(
			value = "handler01"
			, method = RequestMethod.GET)
	public void handler01() {log.info("/case01/handler01 요청 수신");}
	
	@RequestMapping(
			value="handler02")
	public void handler02() {log.info("/case01/handler02 요청 수신");}
	
	@RequestMapping(
			value="handler03"
			, headers = "content-type=application/json")
	public void handler03() {log.info("/case01/handler03 요청 수신");}
	
	@RequestMapping(
			value="handler04"
			, params = "p1=23"//p1이라는 key값에 value(23)이 들어있으면..!
			)
	public void handler04() {log.info("/case01/handler04 요청 수신");}
	
	@GetMapping("handler05")
	public void handler05_1() {log.info("/case01/handler05 Get요청 수신");}
	@PostMapping("handler05")
	public void handler05_2() {log.info("/case01/handler05 Post요청 수신");}
	@PutMapping("handler05")
	public void handler05_3() {log.info("/case01/handler05 Put요청 수신");}
	@DeleteMapping("handler05")
	public void handler05_4() {log.info("/case01/handler05 Delete요청 수신");}

	@PostConstruct
	public void init() {
		log.info("{} 빈 등록 완료", this.getClass().getSimpleName());
	}
}
