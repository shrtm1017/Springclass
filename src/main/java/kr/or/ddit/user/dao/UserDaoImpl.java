package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.pageVo;
@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	//전체 유저 조회
	/**
	* Method : getAllUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVo> getAllUser(){
		
		List<UserVo> userList =sqlSessionTemplate.selectList("user.getAllUser");
		return userList;
		
	}

	@Override
	public UserVo selectUser(String vo) {
		UserVo userVo =  sqlSessionTemplate.selectOne("user.selectUser",vo);
		return userVo;
	}
	/**
	* Method : selectUserPagingList
	* 작성자 : PC03
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Override
	public List<UserVo> selectUserPagingList(pageVo pageVo) {
		
		List<UserVo> userPageList =sqlSessionTemplate.selectList("user.selectUserPagingList" , pageVo);
		return userPageList;
	}

	/**
	* Method : getUserCnt
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :전체 사용자 수 조회
	*/
	@Override
	public int getUserCnt() {
		int userCnt =sqlSessionTemplate.selectOne("user.getUserCnt");
		return userCnt;
	}

	@Override
	public int insertUser(UserVo vo) {
		int userInsert =sqlSessionTemplate.insert("user.insertUser",vo);
		return userInsert;
	}

	/**
	* Method : deleteUser
	* 작성자 : PC03
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 :사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		int deleteUser= sqlSessionTemplate.delete("user.deleteUser",userId);
		return deleteUser;
	}

	@Override
	public int updateUser(UserVo vo) {
		int updateUser= sqlSessionTemplate.update("user.updateUser", vo);
		return updateUser;
	}

	@Override
	public int encryptPass(UserVo vo) {
		int encryptPass = sqlSessionTemplate.update("user.encrytPass",vo);
		return encryptPass;
	}
	

}
