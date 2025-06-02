package kr.or.ddit.vo;

import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 	Domain Layer: 테이블의 스키마(엔터티 구조)를 반영함.
 * 	JavaBean규약에 따라 개발되어야 함. -> lombok 활용중.
 * 	
 * 
 */
@Data
@EqualsAndHashCode(of="mtType")
//@NoArgsConstructor //기본생성자
//@AllArgsConstructor //모든 필드를 갖는 생성자
public class MbtiVO {
	private Integer mtSort;
	private String mtType;
	private String mtTitle;
	private String mtContent;
	public Integer getMtSort() {
		return mtSort;
	}
	public String getMtType() {
		return mtType;
	}
	public String getMtTitle() {
		return mtTitle;
	}
	public String getMtContent() {
		return mtContent;
	}
	public void setMtSort(Integer mtSort) {
		this.mtSort = mtSort;
	}
	public void setMtType(String mtType) {
		this.mtType = mtType;
	}
	public void setMtTitle(String mtTitle) {
		this.mtTitle = mtTitle;
	}
	public void setMtContent(String mtContent) {
		this.mtContent = mtContent;
	}
	@Override
	public String toString() {
		return "MbtiVO [mtSort=" + mtSort + ", mtType=" + mtType + ", mtTitle=" + mtTitle + ", mtContent=" + mtContent
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(mtContent, mtSort, mtTitle, mtType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MbtiVO other = (MbtiVO) obj;
		return Objects.equals(mtContent, other.mtContent) && Objects.equals(mtSort, other.mtSort)
				&& Objects.equals(mtTitle, other.mtTitle) && Objects.equals(mtType, other.mtType);
	}
	
	///1단계(모든 필드를 할당하는 생성자 + private)
	private MbtiVO(Integer mtSort, String mtType, String mtTitle, String mtContent) {
		super();
		this.mtSort = mtSort;
		this.mtType = mtType;
		this.mtTitle = mtTitle;
		this.mtContent = mtContent;
	}
	public MbtiVO() {
		// TODO Auto-generated constructor stub
	}
	///2단계
	public static MbtiVOBuilder builder() {
		return new MbtiVOBuilder();
	}
	///
	public static class MbtiVOBuilder {
		private Integer mtSort;
		private String mtType;
		private String mtTitle;
		private String mtContent;
		
		public MbtiVO builder() {
			return new MbtiVO(null,null,null,null);
		}
		
		public MbtiVOBuilder mtSort(Integer mtSort){
			this.mtSort = mtSort;
			return this;
		}
		public MbtiVOBuilder mtType(String mtType){
			this.mtType = mtType;
			return this;
		}
		public MbtiVOBuilder mtTitle(String mtTitle){
			this.mtTitle = mtTitle;
			return this;
		}
		public MbtiVOBuilder mtContent(String mtContent){
			this.mtContent = mtContent;
			return this;
		}
		
		public MbtiVO build() {
			return new MbtiVO(mtSort, mtType, mtTitle, mtContent);
		}
	}
	
}
