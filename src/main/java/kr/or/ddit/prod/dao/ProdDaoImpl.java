package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.prod.model.ProdVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
@Repository("prodDao")
public class ProdDaoImpl implements IProdDao {

	// 전체 유저 조회
	/**
	 * Method : getAllUser 작성자 : PC03 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 조회
	 */

	@Override
	public List<ProdVo> getAllProd() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy
				.getSqlSessionFactory();

		SqlSession openSession = sqlSessionFactory.openSession();
		List<ProdVo> ProdList = openSession.selectList("prod.getAllprod");
		openSession.close();
		return ProdList;

	}

	@Override
	public List<ProdVo> selectProd(String vo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy
				.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<ProdVo> ProdList = openSession.selectList("prod.seletprod", vo);
		openSession.close();
		return ProdList;
	}

}
