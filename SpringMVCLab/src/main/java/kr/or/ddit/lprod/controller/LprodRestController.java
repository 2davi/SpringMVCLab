package kr.or.ddit.lprod.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LprodRestController {
	private final LprodService service;
	
	@GetMapping("/ajax/lprod")
	@ResponseBody
	public List<LprodVO> lprodList(Model model) {
		List<LprodVO> lprodList = service.readLprodList();
		model.addAttribute("lprodList", lprodList);
		return lprodList;
	}
}
