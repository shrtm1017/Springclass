package kr.or.ddit.lprod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.util.model.pageVo;
@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao {

	// 전체 유저 조회
	/**
	 * Method : getAllUser 작성자 : PC03 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 조회
	 */

	@Override
	public List<LprodVo> getAllLprod() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy
				.getSqlSessionFactory();

		SqlSession openSession = sqlSessionFactory.openSession();
		List<LprodVo> LprodList = openSession.selectList("lprod.getAllLprod");
		openSession.close();
		return LprodList;

	}

	@Override
	public LprodVo selectLprod(String vo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy
				.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		LprodVo LprodVo = openSession.selectOne("lprod.seletLprod", vo);
		openSession.close();
		return LprodVo;
	}

	@Override
	public List<LprodVo> lpordpageList(pageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		 List<LprodVo> lprodVo = sqlSession.selectList("lprod.selectLpordPageList",pageVo);
		 sqlSession.close();
		return lprodVo;
	}

	@Override
	public int getLprodCnt() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int LprodCnt = sqlSession.selectOne("lprod.getLprodCnt");
		return LprodCnt;
	}

}
