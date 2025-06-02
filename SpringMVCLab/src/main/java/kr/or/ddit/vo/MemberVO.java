package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(of = "memId") //데이터베이스 설계 참고(PK를 기준삼겠다)
public class MemberVO implements Serializable{
	private String memId;
	private String memPassword;
	private String memName;
	@ToString.Exclude
	private transient String memRegno1; //toString 문자열에 주민번호를 포함? X
	@ToString.Exclude
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memHobby;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
	public String getMemId() {
		return memId;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public String getMemName() {
		return memName;
	}
	public String getMemRegno1() {
		return memRegno1;
	}
	public String getMemRegno2() {
		return memRegno2;
	}
	public String getMemBir() {
		return memBir;
	}
	public String getMemZip() {
		return memZip;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public String getMemHometel() {
		return memHometel;
	}
	public String getMemComtel() {
		return memComtel;
	}
	public String getMemHp() {
		return memHp;
	}
	public String getMemMail() {
		return memMail;
	}
	public String getMemJob() {
		return memJob;
	}
	public String getMemHobby() {
		return memHobby;
	}
	public String getMemMemorial() {
		return memMemorial;
	}
	public String getMemMemorialday() {
		return memMemorialday;
	}
	public Integer getMemMileage() {
		return memMileage;
	}
	public String getMemDelete() {
		return memDelete;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}
	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	public void setMemHometel(String memHometel) {
		this.memHometel = memHometel;
	}
	public void setMemComtel(String memComtel) {
		this.memComtel = memComtel;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}
	public void setMemHobby(String memHobby) {
		this.memHobby = memHobby;
	}
	public void setMemMemorial(String memMemorial) {
		this.memMemorial = memMemorial;
	}
	public void setMemMemorialday(String memMemorialday) {
		this.memMemorialday = memMemorialday;
	}
	public void setMemMileage(Integer memMileage) {
		this.memMileage = memMileage;
	}
	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}
	@Override
	public int hashCode() {
		return Objects.hash(memAdd1, memAdd2, memBir, memComtel, memDelete, memHobby, memHometel, memHp, memId, memJob,
				memMail, memMemorial, memMemorialday, memMileage, memName, memPassword, memRegno1, memRegno2, memZip);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(memAdd1, other.memAdd1) && Objects.equals(memAdd2, other.memAdd2)
				&& Objects.equals(memBir, other.memBir) && Objects.equals(memComtel, other.memComtel)
				&& Objects.equals(memDelete, other.memDelete) && Objects.equals(memHobby, other.memHobby)
				&& Objects.equals(memHometel, other.memHometel) && Objects.equals(memHp, other.memHp)
				&& Objects.equals(memId, other.memId) && Objects.equals(memJob, other.memJob)
				&& Objects.equals(memMail, other.memMail) && Objects.equals(memMemorial, other.memMemorial)
				&& Objects.equals(memMemorialday, other.memMemorialday) && Objects.equals(memMileage, other.memMileage)
				&& Objects.equals(memName, other.memName) && Objects.equals(memPassword, other.memPassword)
				&& Objects.equals(memRegno1, other.memRegno1) && Objects.equals(memRegno2, other.memRegno2)
				&& Objects.equals(memZip, other.memZip);
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPassword=" + memPassword + ", memName=" + memName + ", memRegno1="
				+ memRegno1 + ", memRegno2=" + memRegno2 + ", memBir=" + memBir + ", memZip=" + memZip + ", memAdd1="
				+ memAdd1 + ", memAdd2=" + memAdd2 + ", memHometel=" + memHometel + ", memComtel=" + memComtel
				+ ", memHp=" + memHp + ", memMail=" + memMail + ", memJob=" + memJob + ", memHobby=" + memHobby
				+ ", memMemorial=" + memMemorial + ", memMemorialday=" + memMemorialday + ", memMileage=" + memMileage
				+ ", memDelete=" + memDelete + "]";
	}
}
