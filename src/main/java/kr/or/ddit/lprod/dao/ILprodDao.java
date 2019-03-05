package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.util.model.pageVo;


public interface ILprodDao {
	/**
	* Method : getAllUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<LprodVo> getAllLprod();
	public LprodVo selectLprod(String vo);
	public List<LprodVo> lpordpageList(pageVo pageVo);
	int getLprodCnt();
	

}
