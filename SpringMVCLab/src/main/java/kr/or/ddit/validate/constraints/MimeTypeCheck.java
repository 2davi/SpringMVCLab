package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 	어노테이션에서 속성을 정의하는 방법 = 메소드 형태로 구현한다.
 * 	~~default 이하~~ 가 없으면 필수속성, 존재하면 옵셔널 속성
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MimeTypeCheckValidator.class)
public @interface MimeTypeCheck {
	
	String mainType();
	
    String message() default "파일이 비어있음 ^0^";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
