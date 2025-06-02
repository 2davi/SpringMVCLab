package kr.or.ddit.buyer.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.buyer.service.BuyerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RootContextConfig
class BuyerServiceImplTest {

	@Autowired
	BuyerService service;
	
	@Test
	void testReadBuyer() {
		fail("Not yet implemented");
	}

	@Test
	void testReadBuyerList() {
		service.readBuyerList();
	}

	@Test
	void testCreateBuyer() {
		fail("Not yet implemented");
	}

	@Test
	void testModifyBuyer() {
		fail("Not yet implemented");
	}

	@Test
	void testBuyerServiceImpl() {
		fail("Not yet implemented");
	}

}
