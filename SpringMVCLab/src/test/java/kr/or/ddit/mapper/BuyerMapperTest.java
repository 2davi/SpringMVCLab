package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RootContextConfig
class BuyerMapperTest {

	@Autowired
	BuyerMapper mapper;

	@Test
	void testInsert() {
		BuyerVO buyer = new BuyerVO();
		buyer.setBuyerName("강감따뜻");
		buyer.setLprodGu("P101");
		buyer.setBuyerBank("하나은행");
		buyer.setBuyerBankname("강감뭉근");
		assertEquals(1, mapper.insertBuyer(buyer));
		
	}
	
	
	@Test
	void testSelectBuyerList() {
		List<BuyerVO> list = mapper.selectBuyerList();
		list.forEach(buyer -> {
			log.info("제조사 {} | 분류명 {} | 소재지 {} | 전화번호 {} | 담당자 {} | 거래은행 {} | 거래품목수 {}"
				, buyer.getBuyerName()
				, buyer.getLprod().getLprodName()
				, buyer.getBuyerAdd1()
				, buyer.getBuyerComtel()
				, buyer.getBuyerCharger()
				, buyer.getBuyerBank()
				, buyer.getProdList().size());
		});
	}
	
	
	@Test
	void testSelectBuyer() {
		// P10101을 조회하고,
		// 제조사명과 제조사주소, 제조사분류명, 거래품목명, 구매가 를 로그로 출력
		BuyerVO buyer = mapper.selectBuyer("P10101");
		LprodVO lprod = buyer.getLprod();
		buyer.getProdList().forEach( prod -> {
			log.info("제조사: {} {} {}, 거래품목명: {}, 구매가: {}"
					,buyer.getBuyerName()
					,buyer.getBuyerAdd1()
					,lprod.getLprodGu()
					,prod.getProdName()
					,prod.getProdCost()
					);
		});
		
		   log.info("제조사명 : {}, 제조사 주소: {}, 제조사 분류명 : {}",buyer.getBuyerName(),buyer.getBuyerAdd2(), buyer.getLprod().getLprodName());
		      buyer.getProdList()
		         .forEach( p -> log.info("거래 품목명 : {}, 구매가 : {}, 거래처아이디 : {} ",p.getProdName(), p.getProdCost(), p.getBuyerId()));
		
		
		
		
	}

}
