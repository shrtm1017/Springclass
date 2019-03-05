package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.util.model.pageVo;
@Service("lprodService")
public class LprodServiceImpl implements ILprodService {
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	public LprodServiceImpl(){
	}
			

	
	/**
	* Method : usergetAll
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :전체 사용자 정보 조회
	*/
	@Override
	public List<LprodVo> getAllLprod() {
		return lprodDao.getAllLprod();
	}



	/**
	* Method : selectUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :특정 사용자 조회
	*/
	@Override
	public LprodVo selectLprod(String vo) {
		// TODO Auto-generated method stub
		return lprodDao.selectLprod(vo);
	}



	@Override
	public Map<String, Object> lpordpageList(pageVo pageVo) {
		 Map<String, Object>resultList = new  HashMap<String, Object>();
		 resultList.put("lprodList", lprodDao.lpordpageList(pageVo));
		 resultList.put("lprodCnt", lprodDao.getLprodCnt());
		return resultList;
	}

}
