package kr.or.ddit.ranger.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.test.LogicTestConfig;
public class RangerDaoTest extends LogicTestConfig {
@Resource(name="rangerDao")
private IRangerDao rangerDao;
@Resource(name="datasource")
private DataSource dataSource;

//@Before
public void setup(){
	ResourceDatabasePopulator rdp =new ResourceDatabasePopulator();
	rdp.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
	rdp.setContinueOnError(false);
	DatabasePopulatorUtils.execute(rdp, dataSource);
}
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index= -1;
		/***When***/
		String ranger =rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("brown", ranger);
	}
	@Test
	public void testGetRanger_overflowIndex() {
		/***Given***/
		int index= 5;
		/***When***/
		String ranger =rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("james", ranger);
	}
	@Test
	public void testGetRanger() {
		/***Given***/
		int index= 2;
		/***When***/
		String ranger =rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("sally", ranger);
	}
	@Test
	public void testGetRangerDb(){
		/***Given***/
		
		/***When***/
		List<Map<String,String>> rangerList = rangerDao.getRangersDb();
		/***Then***/
		assertEquals(6, rangerList.size());
	}
	
	@Test
	public void testGetRangerId(){
		/***Given***/
		Map<String, String> ranger = rangerDao.getRanger("ryan");
		/***When***/
		
		/***Then***/
		assertNotNull(ranger);
		assertEquals("라이언",ranger.get("NAME"));
		
	}
	/**
	* Method : insertRanger
	* 작성자 : PC03
	* 변경이력 :
	* Method 설명 :신규 레인저 등록
	*/
	@Test
	public void insertRanger() {
		/***Given***/
		Map<String,String> ranger = new HashMap<String,String>();
		ranger.put("id", "ryan");
		ranger.put("name", "라이언");
		/***When***/
		int insertCnt = rangerDao.insertRanger(ranger);
		/***Then***/
		assertEquals(1,insertCnt);

	}
//	@Test
//	public void deleteRanger() {
//		/***Given***/
//		
//		/***When***/
//		int deleteCnt = rangerDao.deleteRanger("ryan");
//		/***Then***/
//		assertEquals(1, deleteCnt);
//	}
	@Test
	public void deleteRangerDept() {
		/***Given***/
		rangerDao.deleteRangerDept("brown");
		/***When***/
		int deleteCnt = rangerDao.deleteRanger("brown");
		/***Then***/
		assertEquals(1, deleteCnt);
	}

	

}
