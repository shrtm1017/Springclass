package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.model.RangerVo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-conversion.xml")
public class StringDateConvertertTest {


	@Resource(name="rangerVo")
	private RangerVo rangerVo;

	@Test
	public void testRangerVo() {
		/***Given***/
		
		/***When***/
		String userId =rangerVo.getUserId();
		Date brith = rangerVo.getBrith();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String brithstr =sdf.format(brith);
		/***Then***/
		assertNotNull(rangerVo);
		assertEquals("brown",userId);
		assertEquals("08-08-2018",brithstr);
	}



}
