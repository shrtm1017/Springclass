package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;


public interface IProdDao {
	/**
	* Method : getAllUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<ProdVo> getAllProd();
	public List<ProdVo> selectProd(String vo);
	

}
