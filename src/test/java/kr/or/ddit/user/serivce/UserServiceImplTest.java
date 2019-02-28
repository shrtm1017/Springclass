package kr.or.ddit.user.serivce;


import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactoy;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.model.pageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest extends LogicTestConfig{
	private SqlSession sqlSession;
	
	@Resource(name="userService")
	private IUserService userService;

	@Before
	public void setup() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactoy
				.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		userService.deleteUser("b");
	}

	@After
	public void tearDown() {
		sqlSession.close();
	}

	// @Test
	// public void testUserDaoImpl() {
	// /***Given***/
	// IUserService dao = new UserServiceImpl();
	// /***When***/
	// List<UserVo> userList = dao.usergetAll();
	// for(UserVo userVo : userList)
	// System.out.println(userVo);
	// /***Then***/
	// assertEquals(5, userList.size());
	// assertNotNull(userList);
	// }

	@Test
	public void testUserServiceImpl() {
		/*** Given ***/
		IUserService dao = new UserServiceImpl();
		/*** When ***/
		UserVo userVo = new UserVo();
		userVo.setUserId("brown");
		UserVo user = dao.selectUser(userVo.getUserId());

		/*** Then ***/
		assertEquals("brown", user.getUserId());
		assertNotNull(user);
	}

	@Test
	public void testSelectUserPagingList() {
		userService = new UserServiceImpl();
		/*** Given ***/
		pageVo pageVo = new pageVo(1, 10);

		/*** When ***/
		Map<String, Object> resultMap = userService
				.selectUserPagingList(pageVo);

		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int userCnt = (int) resultMap.get("getUserCnt");

		for (UserVo user : userList) {
			System.out.println(user);
		}
		System.out.println(userCnt);

		/*** Then ***/

		// userList
		assertNotNull(userList);
		assertEquals(10, userList.size());

		// userCnt
		assertEquals(105, userCnt);

	}

	@Test
	public void testUserUpdate() {
		/*** Given ***/
		/*** When ***/
		UserVo vo = new UserVo();
		vo.setUserId("aaa");
		vo.setUserNm("a");
		vo.setZipcode("1");
		vo.setAddr1("aa");
		vo.setAddr2("bb");
		vo.setAlias("aaaaa");
		vo.setPass("bbbb");
		int userUpdate = userService.updateUser(vo);

		/*** Then ***/
		assertEquals(1, userUpdate);

	}

	@Test
	public void encryptPass() {
		
		int encryptPass = userService.encryptPass();
		logger.debug("a"+"encryptPass");
		assertEquals(114, encryptPass);

	}

}
