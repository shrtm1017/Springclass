package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {SpringJavaAnnotationScanConfig.class})
public class SpringJavaAnnotationScanConfigTest {
@Resource(name="rangerD")
private IRangerDao rangerDAO;
@Resource(name="rangerS")
private IRangerService rangerService;
private Logger logger = LoggerFactory.getLogger(SpringJavaAnnotationScanConfigTest.class);
	@Test
	public void testJavaAnnotationScan() {
		
		/***Given***/
		
		/***When***/
		logger.debug("rangerDAO"+rangerDAO.getRangers());
		logger.debug("rangerService"+rangerService.getRangers());
		/***Then***/
		assertNotNull(rangerDAO);
		assertNotNull(rangerService);
		
//		assertEquals(rangerDAO, rangerService.getRangerDao());
	}

}
