package kr.or.ddit.case08.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.case08.vo.NativeJavaVO;

/**
 *	RestFul URI
 *	명사는 URI를 + 동사는 Method를 표현.
 *	(명사는 그대로 두고 동사만 바꿔가며 CRUD를 표현)
 *	 /case08/dummy		+ GET
 *	 /case08/dummy/pk1	+ GET	<<-경로변수
 *	 /case08/dummy		+ POST
 *	 /case08/dummy/pk1	+ DELETE
 *
 *	->> @PathVariable vs @RequestParam(default)
 */
/////@Controller
/////@ResponseBody 
@RestController	/////<+=
@RequestMapping("/case08/dummy")
public class Case08RestAPIController {
/**이 컨트롤러에서 만든 모든 핸들러는 json응답을 보낸다.*/
	
	private Map<String, NativeJavaVO> dummyDB;
	
	{	//생성자의 역할을 대신해주는 코드블럭
		
		dummyDB = new HashMap<>();
		NativeJavaVO njv1 = new NativeJavaVO();
		dummyDB.put("pk1", njv1);
		njv1.setProp1("문자열");
		njv1.setProp2(32);
		njv1.setProp3(new String[] {"v1", "v2", "v3", "v4"});
		njv1.setProp4(true);
		njv1.setProp5(3.1415926535);
		NativeJavaVO njv2 = new NativeJavaVO();
		dummyDB.put("pk2", njv2);
		njv2.setProp1("TEXT");
		njv2.setProp2(388);
		njv2.setProp3(new String[] {"c1", "c2", "c3", "c4"});
		njv2.setProp4(false);
		njv2.setProp5(1.5854927);
	}
	
	
	@GetMapping
	public List<NativeJavaVO> restGet() {
//		List<NativeJavaVO> list = (List<NativeJavaVO>)dummyDB.values();
//		return list;
		return new ArrayList<>(dummyDB.values());
//		return "case08/view01";
	}
	
	@GetMapping("{key}")//경로변수에는 이름이 있어야행
	public NativeJavaVO restGet(@PathVariable String key) {
/** 	return dummyDB.get(key); 	없는데도 있는 척 해,, */
		
		NativeJavaVO target = dummyDB.get(key);
		if(target==null) 
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		
		return target;
	}
	
	   @PostMapping
	   public NativeJavaVO create(@RequestBody NativeJavaVO njv) {
	      String newPk = "pk" + dummyDB.size()+10;
	      njv.setCode(newPk);
	      dummyDB.put(newPk, njv);
	      return njv;
	   }
	   
	   @PutMapping("{key}")
	   public Map<String, Object> modify(@PathVariable String key, @RequestBody NativeJavaVO njv) {
	      dummyDB.put(key, njv);
	      return Map.of("Success", true, "target", njv);
	   }

	
	@DeleteMapping("{key}")
	public Map<String, Object> restDelete(@PathVariable String key) {
		NativeJavaVO target = dummyDB.remove(key);
		return Map.of("success", true, "target", target);
//		return "redirect:/case08/dummy/..."
//----	DELETE 이후에 redirection을 해봤자 DELETE로 돌아온다.
//		유일하게 POST만 GET으로 넘어감. 그래서 PRG라고 특수한 패턴 가능.
	}
	
}
