package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.pageVo;

public class lprodServiceTest extends LogicTestConfig{
	private SqlSession sqlSession;
	
	@Resource(name="lprodService")
	private ILprodService lprodService;

	@Before
	public void setup() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy
				.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}

	@After
	public void tearDown() {
		sqlSession.close();
	}
	@Test
	public void testLprodPageList() {
		/*** Given ***/
		pageVo pageVo = new pageVo(1, 10);

		/*** When ***/
		Map<String, Object> resultMap = lprodService.lpordpageList(pageVo);

		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int lprodCnt = (int) resultMap.get("lprodCnt");


		/*** Then ***/

		// userList
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());

		// userCnt
		assertEquals(19, lprodCnt);

	}

}
