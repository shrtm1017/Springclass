package kr.or.ddit.user.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;

public class userDaoTest extends LogicTestConfig{
@Resource(name="userDao")
private	IUserDao userDao;
	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVo>userlist=userDao.getAllUser();
		
		/***Then***/
		assertTrue(100<userlist.size());
	}

}
