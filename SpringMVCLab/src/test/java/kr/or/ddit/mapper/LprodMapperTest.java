package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.conf.SpringRootContextConfig;
import lombok.extern.slf4j.Slf4j;

@RootContextConfig
@Slf4j
class LprodMapperTest {

	@Autowired
	LprodMapper mapperProxy;

	@Test
	void testSelectLprodList() {
		log.info("mapper proxy: {}", mapperProxy);
		mapperProxy.selectLprodList();
	}

}
