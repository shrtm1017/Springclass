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
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-fconversion.xml")
public class StringDateFConvertertTest {


	@Resource(name="rangerVo")
	private RangerVo rangerVo;

	@Test
	public void testRangerVo() {
		/***Given***/
		
		/***When***/
		String userId =rangerVo.getUserId();
		Date brith = rangerVo.getBrith();
		Date regDt= rangerVo.getRegDt();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String regDString = sdf2.format(regDt);
		String brithstr =sdf.format(brith);
		/***Then***/
		assertNotNull(rangerVo);
		assertEquals("brown",userId);
		assertEquals("08-08-2018",brithstr);
		assertEquals("2019-02-27",regDString);
	}



}
