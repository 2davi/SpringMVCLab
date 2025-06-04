package kr.or.ddit.validate.utils;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component	//메소드에 static을 붙이든가, Bean으로 등록하든가
public class ErrorsUtils {
	
	public MultiValueMap<String, String> errorsToMap(BindingResult errors) {
		MultiValueMap<String, String> customErrors =
				new LinkedMultiValueMap<>();
		List<ObjectError> allErrors = errors.getAllErrors();
		for(ObjectError single: allErrors) {
			if(single instanceof FieldError /*변수의 타입을 체크하겠다는 연산자 instanceof */) {
				/*조건문 안으로 들어온 single은 검증에 실패했다는 에러문구를 갖고 있다. */
				FieldError fe = (FieldError) single;
				
				//VO의 필드명(선언된 private변수명=컬럼명=프로퍼티명)
				String fieldName = fe.getField();
				String message = fe.getDefaultMessage();
				customErrors.add(fieldName, message);
			}
		}
		return customErrors;
	}
}
