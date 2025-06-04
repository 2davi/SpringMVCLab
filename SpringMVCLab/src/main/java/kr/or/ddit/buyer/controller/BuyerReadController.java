package kr.or.ddit.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

/**
 * 	//	logical view name이 명시되지 않은 경우,
 * 	//	RequestToViewNameTranslator 가 동작하게 됨.
 * 	//	ex) request url이 lvn으로 반영됨.
 */
@Controller
@RequestMapping("/buyer")
public class BuyerReadController {
	
	private BuyerService service;
	@Autowired
	public void setService(BuyerService service) {
		this.service = service;
	}
	
	@GetMapping("buyerList.do")
	public String listHandler(Model model) {
		List<BuyerVO> buyerList = service.readBuyerList();
		model.addAttribute("buyerList", buyerList);
		return "buyer/buyerList";	//<--views JSP파일
	}
	
	@GetMapping("buyerDetail.do")
	public void detailHandler(
			@RequestParam String what
			, Model model
	) {
		BuyerVO buyer = service.readBuyer(what).get();
		model.addAttribute("buyer", buyer);
//		return "buyer/buyerDetail";	//<--views JSP파일
	}

}
