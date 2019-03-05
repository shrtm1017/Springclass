package kr.or.ddit.prod.dao;

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
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.util.model.pageVo;

public class prodDaoTest extends LogicTestConfig{
	@Resource(name="prodDao")
	private IProdDao  prodDao;
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
	public void testLprodPageList() {
		LprodVo vo = new LprodVo();
		vo.setLprod_gu("P101");
	List<ProdVo> List =	prodDao.selectProd(vo.getLprod_gu());
	assertNotNull(List);
	
		
	}

}
