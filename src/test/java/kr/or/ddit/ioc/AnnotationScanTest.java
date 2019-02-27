package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;
import kr.or.ddit.ranger.service.IRangerService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {
	private Logger logger = LoggerFactory.getLogger(AnnotationScanTest.class);
	//rangerDao 주입
	@Resource(name="rangerDaoImpl")
	private IRangerDao rangerDao;
	//rangerService 주입
	@Resource(name="rangerServiceImpl")
	private IRangerService rangerService;
	//두개의 스프링 빈이 정상적으로 생성 돠었는지 테스트
	@Test
	public void testAnnotationScanTest() {
		/***Given***/
		rangerDao = new RangerDaoImpl();
		/***When***/
		List<String> list= rangerDao.getRangers();
		List<String> service =rangerService.getRangers();
		for(String liststr : list)
			logger.debug(liststr);
		for(String listsevcitstr : service)
			logger.debug(listsevcitstr);

		/***Then***/
		assertNotNull(rangerDao);
		assertNotNull(rangerService);
	}

}
