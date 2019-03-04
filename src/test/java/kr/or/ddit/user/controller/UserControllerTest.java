package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig{
private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	/**
	* Method : testUserAllList
	* 작성자 : PC03
	* 변경이력 :
	* Method 설명 : 사용자 전체 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav =mvcResult.getModelAndView();
		String viewName =mav.getViewName();
		List<UserVo> userAllList=(List<UserVo>) mav.getModel().get("userAllList");//내가 지은 객체명
		/***Then***/
		logger.debug("userAllList :"+userAllList.size());
		assertEquals("/user/userAllList", viewName);
		assertNotNull(userAllList);
		assertTrue(userAllList.size()>100);//if문과 사용법이 동일하다.
	}
	@Test
	public void testUserpageList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =mockMvc.perform(get("/user/userPagingList")).andReturn();
		ModelAndView mav =mvcResult.getModelAndView();
		String viewName =mav.getViewName();
		List<UserVo> userPagingList= (List<UserVo>) mav.getModel().get("userList");//내가 지은 객체명
		int userCnt = (int) mav.getModelMap().get("userCnt");
		int page =(int) mav.getModel().get("page");
		int pageSize=(int) mav.getModelMap().get("pageSize");
		/***Then***/
		logger.debug("userAllList :"+userPagingList.size());
		assertEquals("/user/userPagingList", viewName);
		assertEquals(1,page);
		assertEquals(10, pageSize);
		assertTrue(userCnt>100);
		
		assertNotNull(userPagingList);
//		assertTrue(userAllList.size()>100);//if문과 사용법이 동일하다.
	}

}
