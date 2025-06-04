package kr.or.ddit.case07.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;

@Data
public class DummyVO {
	
	@NotBlank(groups = InsertGroup.class)
	private String name;
	@Min(1)
	private int age;
	private String[] hobbies;
}
