package kr.or.ddit.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

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
		return "buyer/buyerList";
	}

}
