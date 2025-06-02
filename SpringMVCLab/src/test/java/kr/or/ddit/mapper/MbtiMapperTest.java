package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.vo.MbtiVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RootContextConfig
class MbtiMapperTest {

	@Autowired
	MbtiMapper mapperProxy;
	
	@Test
	void testInjection() {
		log.info("mapper proxy: {}", mapperProxy);
	}
	
	@Test
	void testInsertMbti() {
		MbtiVO vo = MbtiVO.builder()
						  .mtContent("T0T")
						  .mtSort(64)
						  .mtTitle("eeee")
						  .mtType("eeee")
						  .build();
		
		mapperProxy.insertMbti(vo);
	}

	@Test
	void testSelectMbtiList() {
		List list = mapperProxy.selectMbtiList();
	}

	@Test
	void testSelectMbti() {
		mapperProxy.selectMbti("INTJ");
	}

	@Test
	void testUpdateMbti() {
		MbtiVO vo = mapperProxy.selectMbti("INTJ");

		mapperProxy.updateMbti(vo);
	}

	@Test
	void testDeleteMbti() {
		mapperProxy.deleteMbti("INTJ");
	}

}
