package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.pageVo;

public interface IUserDao {
	/**
	* Method : getAllUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVo> getAllUser();
	/**
	* Method : selectUser
	* 작성자 : PC03
	* 변경이력 :
	* @param vo
	* @return
	* Method 설명 : 사용자 조 회
	*/
	public UserVo selectUser(String vo);
	
	/**
	* Method : selectUserPagingList
	* 작성자 : PC03
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	public int insertUser(UserVo vo);
	
	/**
	* Method : deleteUser
	* 작성자 : PC03
	* 변경이력 :
	* @param vo
	* @return
	* Method 설명 :사용자 삭제
	*/
	int deleteUser(String userId);
	public int updateUser(UserVo userId);
	
List<UserVo> selectUserPagingList(pageVo pageVo);
	
int getUserCnt();

int encryptPass(UserVo vo);


}
