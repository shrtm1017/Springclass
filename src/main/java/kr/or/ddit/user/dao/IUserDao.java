package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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
	public List<UserVo> getAllUser(SqlSession openSession);
	/**
	* Method : selectUser
	* 작성자 : PC03
	* 변경이력 :
	* @param vo
	* @return
	* Method 설명 : 사용자 조 회
	*/
	public UserVo selectUser(SqlSession openSession,String vo);
	
	/**
	* Method : selectUserPagingList
	* 작성자 : PC03
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	public int insertUser(SqlSession openSession,UserVo vo);
	
	/**
	* Method : deleteUser
	* 작성자 : PC03
	* 변경이력 :
	* @param vo
	* @return
	* Method 설명 :사용자 삭제
	*/
	int deleteUser(SqlSession openSession,String userId);
	public int updateUser(SqlSession openSession,UserVo userId);
	
List<UserVo> selectUserPagingList(SqlSession openSession,pageVo pageVo);
	
int getUserCnt(SqlSession openSession);

int encryptPass(SqlSession openSession,UserVo vo);


}
