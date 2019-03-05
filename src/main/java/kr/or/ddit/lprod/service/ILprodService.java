package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.util.model.pageVo;

public interface ILprodService {
	public List<LprodVo> getAllLprod();
	public LprodVo selectLprod(String vo);
	Map<String,Object> lpordpageList(pageVo pageVo);

}
