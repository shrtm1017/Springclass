package kr.or.ddit.ranger.service;

import java.util.List;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;

public class RangerServiceImpl implements IRangerService{
	IRangerDao rangerDao; 

	public RangerServiceImpl() {
	}
	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerDao=rangerDao;
	}


	@Override
	public List<String> getRangers() {
		rangerDao = new RangerDaoImpl();
		
		return rangerDao.getRangers();
	}
	public void setRangerDao(IRangerDao rangerDao) {
		this.rangerDao = rangerDao;
	}



}
