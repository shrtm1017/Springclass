package kr.or.ddit.prod.service;

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
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.pageVo;

public class prodServiceTest extends LogicTestConfig{
	private SqlSession sqlSession;
	
	@Resource(name="prodService")
	private IProdService prodService;

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
	public void testprodList() {
		/*** Given ***/
		LprodVo vo = new LprodVo();
		vo.setLprod_gu("P101");
	List<ProdVo> List =	prodService.selectProd(vo.getLprod_gu());

		/*** Then ***/

		assertNotNull(List);
		assertEquals(10, List.size());


	}

}
