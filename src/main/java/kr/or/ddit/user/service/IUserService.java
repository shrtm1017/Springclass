package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.pageVo;

public interface IUserService {
	
	List<UserVo> usergetAll();
	UserVo selectUser(String userId);
	Map<String,Object> selectUserPagingList(pageVo pageVo);
	public int insertUser(UserVo vo);
	int deleteUser(String userId);
	public int updateUser(UserVo vo);
	int encryptPass();
}
