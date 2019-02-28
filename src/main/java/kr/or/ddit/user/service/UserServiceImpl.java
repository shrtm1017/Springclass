package kr.or.ddit.user.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
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
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		List<UserVo> userList = userDao.getAllUser(sqlSession);
		sqlSession.close();
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
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		UserVo userList = userDao.selectUser(sqlSession, vo);
		sqlSession.close();
		return userList;
	}



	@Override
	public Map<String,Object> selectUserPagingList(pageVo pageVo) {
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		
		resultMap.put("userList", userDao.selectUserPagingList(sqlSession,pageVo));
		resultMap.put("getUserCnt", userDao.getUserCnt(sqlSession));
		
		sqlSession.close();
		return resultMap;
	}







	@Override
	public int insertUser(UserVo vo) {
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		int insertUser = userDao.insertUser(sqlSession, vo);
		sqlSession.commit();
		sqlSession.close();
		
		return insertUser;
	}



	@Override
	public int deleteUser(String userId) {
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		int deleteUser = userDao.deleteUser(sqlSession, userId);
		sqlSession.commit();
		sqlSession.close();
		
		return deleteUser;
	}



	@Override
	public int updateUser(UserVo vo) {
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		int updateUser = userDao.updateUser(sqlSession, vo);
		sqlSession.commit();
		sqlSession.close();
		
		return updateUser;
	}



	@Override
	public int encryptPass() {
		UserVo vo = new UserVo();
		int encryptPass =0;
		SqlSessionFactory sessionFactory = MybatisSqlSessionFactoy.getSqlSessionFactory();
		SqlSession sqlSession = sessionFactory.openSession();
		List<UserVo> encrytPass = userDao.getAllUser(sqlSession);
		for (int i = 0; i < encrytPass.size(); i++) {
			String ChangePass = encrytPass.get(i).getPass();
			String Sha256Pass = KISA_SHA256.encrypt(ChangePass);
			vo.setPass(Sha256Pass);
			vo.setUserId(encrytPass.get(i).getUserId());
			encryptPass += userDao.encryptPass(sqlSession,vo);
		}
		sqlSession.commit();
		sqlSession.close();
		return encryptPass;
		
	}
	

}
