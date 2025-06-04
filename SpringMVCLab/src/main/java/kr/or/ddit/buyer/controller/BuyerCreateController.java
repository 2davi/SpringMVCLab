package kr.or.ddit.buyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("buyer/buyerCreate.do")
public class BuyerCreateController {
	
	static final String MODELNAME = "buyer";
	
	@Autowired
	private BuyerService service;
	
	
	@ModelAttribute(MODELNAME)
	public BuyerVO buyer() {
		return new BuyerVO();
	}

	
	@GetMapping
	public String formUI() {
		return "buyer/buyerForm";
	}
	
	@PostMapping
	public String submitted(
			@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) BuyerVO buyer
			, BindingResult errors
			, RedirectAttributes redirectAttributes
	) {
		if(errors.hasErrors()) {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(errorsName, errors);
			redirectAttributes.addFlashAttribute(MODELNAME, buyer);
			return "redirect:/buyer/buyerCreate.do";
		} else {
			service.createBuyer(buyer);
			return "redirect:/buyer/buyerList.do";
		}
	}
	
	
	/**
	 *	폼으로 입력받은 데이터 처리
	 */

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		BuyerVO buyer = new BuyerVO();
//		try { BeanUtils.populate(buyer, request.getParameterMap()); }
//		catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e); //간간히 등장하는 예외 전환
//		}
//		
//		///■□■ 3. BuyerVO가 검증 대상
//		///왜? BuyerVO가 모든 요청의 결과값을 가지고 있으니까^0^
//		///     └-> Command Object
//		
////		if(StringUtils.isBlank(buyer.getBuyerName())) {
/////			///검증의 대상::
/////			/// - not null 컬럼의 데이터값 존재하는지
/////			/// - 타입에 맞는 형식인지, 컬럼 길이에 맞는 값인지
/////			/// - 
////		}
//		
//		Map<String, String> errors = new HashMap<>();
//		boolean valid = validate(buyer, errors);
//		if(valid) {
//			service.createBuyer(buyer);
//			//PRG패턴
//			response.sendRedirect(request.getContextPath() + "/buyer/buyerList.do");
//		} else {
//			//// *** 
////			"/buyer/buyerForm.jsp"; ::forward
////			"/buyer/buyerInsert.do";::redirect
//			//setAttribute?
//			HttpSession session = request.getSession();
//			session.setAttribute("buyer", buyer);
//			session.setAttribute("errors", errors);
////			사용한 데이터는 세션에서 지워주어야 한다.
//			response.sendRedirect("/buyer/buyerInsert.do");
//		}
//		
//	}
}
