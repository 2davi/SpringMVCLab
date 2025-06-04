package kr.or.ddit.buyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

/**
 *  /buyer/buyerUpdate.do (GET, POST)
 */
@Controller
@RequestMapping("/buyer/buyerModify.do")
@RequiredArgsConstructor
public class BuyerModifyController {
	
	///FIELD INJECTION 방법
	///스프링에서는 공식적으로 생성자 주입 방법을 권장함@RequiredArgsConstructor
	private final BuyerService service;
	private ErrorsUtils errorsUtils;
	@Autowired(required = false)	//있으면|쓰이면 주입하기
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	static final String MODELNAME = "buyer";
	
	
	/**
	 * 등록 form 제공
	 */
	@GetMapping	
	public String editForm(
			@RequestParam String what
			, Model model
	) {
//----	수정 작업이기 때문에, 이미 저장되어있던 데이터를 받아와야 한다.
//----> SPRING CONTAINER는 Model로 저장소를 관리하기 때문에,
//----> 조건문은 "VO가 null냐"에서 "Model에 저장되어 있느냐"로 전환
//		HttpSession session = req.getSession();
//		BuyerVO buyer = (BuyerVO) session.getAttribute("buyer");
//		session.removeAttribute("buyer"); // flash attribute 방식
//		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
//		session.removeAttribute("errors");
//		
//		req.setAttribute("buyer", buyer);
//		req.setAttribute("errors", errors);


//----	@RequestParam이 대신 해줌.
//		String buyerId = req.getParameter("what");
//		if(StringUtils.isBlank(buyerId)) {
//			resp.sendError(400, "필수 파라미터 누락");
//			return;
//		}
		if(!model.containsAttribute(MODELNAME)) {
			BuyerVO buyer = service.readBuyer(what).get();
			model.addAttribute(MODELNAME, buyer);
		}
		
//----	Logical View Name을 반환하는 것으로 대체함.
//		String lvn = "buyer/buyerEdit";
//		new ViewResolverComposite().resolveView(lvn, req, resp);
		return "buyer/buyerEdit";
	}
	
	
	/**
	 *
	 * form 으로 입력받은 데이터 처리
	 */
	@PostMapping
	public String formProcess(
			String what
			, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) BuyerVO buyer
			, BindingResult errors
			, RedirectAttributes redirectAttributes
	) {

//----	파라미터 가져오기는 @RequestParam 으로 대체된다.
//		String what = req.getParameter("what");
		
//----	Command Object 가져오기는 @ModelAttribute 로 대체된다.
//		BuyerVO buyer = new BuyerVO();
//		try {
//			BeanUtils.populate(buyer, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}

//----	검증과정은 @Validated(group hint) 로 대체된다.
//		Map<String, String> errors = 
//				ValidateUtils.validate(buyer, UpdateGroup.class);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyBuyer(buyer);
			lvn = "redirect:/buyer/buyerDetail.do?what="+buyer.getBuyerId();
		}else {
//----		우리는 RedirectAttributes를 통해 간접적으로 session scope에 속성을 저장한다.
//			HttpSession session = req.getSession();
//			session.setAttribute("buyer", buyer);
//			session.setAttribute("errors", errors);
			redirectAttributes.addFlashAttribute(MODELNAME, buyer);
//----		스프링 커스텀태그를 ㅈ도 쓰기 싫었다. 내 입맛대로 만들 메소드 선언.
			MultiValueMap<String,String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn= "redirect:/buyer/buyerModify.do?what="+what;
		}
//----	Logical View Name을 반환하는 것으로 대체된다.
//		new ViewResolverComposite().resolveView(lvn, req, resp);
		return lvn;
	}


	
/** ErrorsUtils Bean으로 이동함^0^ */
//	private MultiValueMap<String, String> errorsToMap(BindingResult errors) {
//		MultiValueMap<String, String> customErrors =
//				new LinkedMultiValueMap<>();
//		List<ObjectError> allErrors = errors.getAllErrors();
//		for(ObjectError single: allErrors) {
//			if(single instanceof FieldError /*변수의 타입을 체크하겠다는 연산자 instanceof */) {
//				/*조건문 안으로 들어온 single은 검증에 실패했다는 에러문구를 갖고 있다. */
//				FieldError fe = (FieldError) single;
//				
//				//VO의 필드명(선언된 private변수명=컬럼명=프로퍼티명)
//				String fieldName = fe.getField();
//				String message = fe.getDefaultMessage();
//				customErrors.add(fieldName, message);
//			}
//		}
//		return customErrors;
//	}

}















