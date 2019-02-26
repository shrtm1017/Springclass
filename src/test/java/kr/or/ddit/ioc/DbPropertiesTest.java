package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {
	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);
	@Resource(name="dbProperties")
	private DbProperties DbPropertiesTest;
	@Test
	public void testPalceholder() {
		
		
		/***Given***/
		
		/***When***/
		String url = DbPropertiesTest.getUrl();
		logger.debug("url :"+url);
		String username = DbPropertiesTest.getUsername();
		logger.debug("username :"+username);
		String password = DbPropertiesTest.getPassword();
		logger.debug("password :"+password);
		String driverClassName = DbPropertiesTest.getDriverClassName();
		logger.debug("driverClassName :"+driverClassName);
		/***Then***/
		assertNotNull(url);
		//url
		assertNotNull(username);
		//username
		assertNotNull(password);
		//password
		assertNotNull(driverClassName);
		//driverClassName
	
	}

}
