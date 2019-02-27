package kr.or.ddit.ranger.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;
@Service("rangerService")
public class RangerServiceImpl implements IRangerService{
	//RangerServiceImpl  맨 앞글자만 소문자로 바꿔써주면 기본 조건
	@Resource(name="rangerDao")
	IRangerDao rangerDao; 

	public RangerServiceImpl() {
	}
	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerDao=rangerDao;
	}


	@Override
	public List<String> getRangers() {
		
		return rangerDao.getRangers();
	}
	public void setRangerDao(IRangerDao rangerDao) {
		this.rangerDao = rangerDao;
	}
	@Override
	public IRangerDao getRangerDao() {
		return this.rangerDao;
	}
	@Override
	public String getRanger(int index) {
		return rangerDao.getRanger(index);
	}



}
