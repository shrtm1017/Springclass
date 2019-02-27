package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.model.RangerVo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-type.xml")
public class PropertyEditorTest {
	@Resource(name="rangerVo")
	private RangerVo rangerVo;

	@Test
	public void testRangerVo() {
		/***Given***/
		
		/***When***/
		String userId =rangerVo.getUserId();
		Date brith = rangerVo.getBrith();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String brithstr =sdf.format(brith);
		/***Then***/
		assertNotNull(rangerVo);
		assertEquals("brown",userId);
		assertEquals("2018-08-08",brithstr);
	}

}
