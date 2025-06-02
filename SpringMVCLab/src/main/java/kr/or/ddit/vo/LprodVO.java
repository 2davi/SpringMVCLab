package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="lrpodGu")
public class LprodVO implements Serializable {
	private Integer lprodId;
	private String lprodGu;
	private String lprodName;
}
