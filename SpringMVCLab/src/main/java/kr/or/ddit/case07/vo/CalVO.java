package kr.or.ddit.case07.vo;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CalVO {
	@NumberFormat@Min(1)
	private int op1;
	@NumberFormat@Min(1)
	private int op2;
	private int result;
}
