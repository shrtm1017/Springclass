package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.user.model.UserVo;

public interface IProdService {
	
	List<ProdVo> ProdgetAll();
	List<ProdVo> selectProd(String prod);
}
