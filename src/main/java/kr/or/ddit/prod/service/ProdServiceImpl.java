package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.model.ProdVo;
@Service("prodService")
public class ProdServiceImpl implements IProdService {
	@Resource(name="prodDao")
	private IProdDao ProdDao;
	public ProdServiceImpl(){
	}
			



	@Override
	public List<ProdVo> ProdgetAll() {
		return ProdDao.getAllProd();
	}



	@Override
	public List<ProdVo> selectProd(String prod) {
		return ProdDao.selectProd(prod);
	}

}
