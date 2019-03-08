package kr.or.ddit.user.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.pageVo;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource(name="userDao")
	private IUserDao userDao;
	public UserServiceImpl(){
		
	}
			

	
	/**
	* Method : usergetAll
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :전체 사용자 정보 조회
	*/
	@Override
	public List<UserVo> usergetAll() {
		List<UserVo> userList = userDao.getAllUser();
		return userList;
	}



	/**
	* Method : selectUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :특정 사용자 조회
	*/
	@Override
	public UserVo selectUser(String vo) {
		UserVo userList = userDao.selectUser(vo);
		return userList;
	}



	@Override
	public Map<String,Object> selectUserPagingList(pageVo pageVo) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		
		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());
		
		return resultMap;
	}







	@Override
	public int insertUser(UserVo vo) {
		int insertUser = userDao.insertUser(vo);
		
		return insertUser;
	}



	@Override
	public int deleteUser(String userId) {
		int deleteUser = userDao.deleteUser(userId);
		
		return deleteUser;
	}



	@Override
	public int updateUser(UserVo vo) {
		int updateUser = userDao.updateUser(vo);
		
		return updateUser;
	}



	@Override
	public int encryptPass() {
		UserVo vo = new UserVo();
		int encryptPass =0;
		List<UserVo> encrytPass = userDao.getAllUser();
		for (int i = 0; i < encrytPass.size(); i++) {
			String ChangePass = encrytPass.get(i).getPass();
			String Sha256Pass = KISA_SHA256.encrypt(ChangePass);
			vo.setPass(Sha256Pass);
			vo.setUserId(encrytPass.get(i).getUserId());
			encryptPass += userDao.encryptPass(vo);
		}
		return encryptPass;
		
	}
	

}
