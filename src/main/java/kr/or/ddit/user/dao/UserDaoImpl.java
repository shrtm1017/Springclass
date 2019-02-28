package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.pageVo;
@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	//전체 유저 조회
	/**
	* Method : getAllUser
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVo> getAllUser(SqlSession openSession){
		
		List<UserVo> userList =openSession.selectList("user.getAllUser");
		return userList;
		
	}

	@Override
	public UserVo selectUser(SqlSession openSession,String vo) {
		UserVo userVo =  openSession.selectOne("user.selectUser",vo);
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
	public List<UserVo> selectUserPagingList(SqlSession openSession,pageVo pageVo) {
		
		List<UserVo> userPageList =openSession.selectList("user.selectUserPagingList" , pageVo);
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
	public int getUserCnt(SqlSession openSession) {
		int userCnt =openSession.selectOne("user.getUserCnt");
		return userCnt;
	}

	@Override
	public int insertUser(SqlSession openSession,UserVo vo) {
		int userInsert =openSession.insert("user.insertUser",vo);
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
	public int deleteUser(SqlSession openSession,String userId) {
		int deleteUser= openSession.delete("user.deleteUser",userId);
		return deleteUser;
	}

	@Override
	public int updateUser(SqlSession openSession, UserVo vo) {
		int updateUser= openSession.update("user.updateUser", vo);
		return updateUser;
	}

	@Override
	public int encryptPass(SqlSession openSession, UserVo vo) {
		int encryptPass = openSession.update("user.encrytPass",vo);
		return encryptPass;
	}
	

}
