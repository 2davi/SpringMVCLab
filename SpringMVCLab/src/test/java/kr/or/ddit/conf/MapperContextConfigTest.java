package kr.or.ddit.conf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MapperContextConfigTest {

	@Autowired
	SqlSessionFactoryBean sqlSessionFactory;
	@Autowired
	MapperScannerConfigurer mapperScanner;
	
	
	@Test
	public void MapperContextConfigTest() {
		log.info("■■■■■■■■■■ SqlSessionFactory: {}", sqlSessionFactory);
		log.info("■■■■■■■■■■ MapperScannerConfigurer: {}", mapperScanner);
	}

}
