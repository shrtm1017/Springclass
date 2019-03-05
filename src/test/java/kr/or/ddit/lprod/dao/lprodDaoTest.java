package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.util.model.pageVo;

public class lprodDaoTest extends LogicTestConfig{
	@Resource(name="lprodDao")
	private ILprodDao  lprodDao;
	private SqlSession sqlSession;
	@Before
	public void setup(){
		SqlSessionFactory  sqlSessionFactory=MybatisSqlSessionFactoy.getSqlSessionFactory();
		sqlSession= sqlSessionFactory.openSession();
		
	}
	@After
	public void tearDaow(){
		sqlSession.close();
		
	}
	@Test
	public void testLprodAllList() {
		pageVo vo =new pageVo(1,10);
	List<LprodVo> List =	lprodDao.getAllLprod();
	assertNotNull(List);
		
	}
	@Test
	public void testLprodPageList() {
		pageVo vo =new pageVo(1,10);
	List<LprodVo> List =	lprodDao.lpordpageList(vo);
	assertNotNull(List);
	assertEquals(10,List.size());
	
		
	}

}
