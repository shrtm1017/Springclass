package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig{
private static final String USER_INSERT_TEST_ID = "sallyTest";
private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
@Resource(name="userService")
private IUserService userService;


@Before
public void initData(){
	userService.deleteUser(USER_INSERT_TEST_ID);
	
}
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
	@Test
	public void testUser() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =mockMvc.perform(get("/user/user").param("userId","brown")).andReturn();
		ModelAndView mav =mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo user= (UserVo) mav.getModel().get("userVo");//내가 지은 객체명
		/***Then***/
		logger.debug("user:"+user);
		assertEquals("/user/user", viewName);
		assertNotNull(user);
		
//		assertTrue(userAllList.size()>100);//if문과 사용법이 동일하다.
	}
	//@Test
	//public void testProfileImg
@Test
public void testUserForm() throws Exception{
	/***Given***/
	
	/***When***/
	MvcResult mvcResult=mockMvc.perform(get("/user/userForm")).andReturn();
	ModelAndView mav = mvcResult.getModelAndView();
	String viewName = mav.getViewName();
	/***Then***/
	assertEquals("/user/userForm", viewName);
	
	
}
@Test
public void testUserForm_Post_success() throws Exception {
	/***Given***/
	File profileFile = new File("D:\\picture\\sally.png");
	FileInputStream fis = new FileInputStream(profileFile);
	MockMultipartFile file = new MockMultipartFile("profile", "brown.png", "image/png",fis);
	MvcResult mvcResult =mockMvc.perform(fileUpload("/user/userForm").file(file).param("userId", "sallyTest").param("userNm","sallyTest").param("zipcode", "11111").param("addr1","대전시 중구 대흥로").param("addr2", "12번지").param("alias","병아리").param("pass","testpass")).andExpect(view().name("redirect:/user/userPagingList"))
			.andReturn();
	//vo대신해서 패럼으로 값을 가져옴
	/***When***/
	HttpSession httpSession = mvcResult.getRequest().getSession();
	
	

	/***Then***/
	assertEquals("정상등록 되었습니다.", httpSession.getAttribute("msg"));
}
 /**
* Method : testUserForm_post_fail_duplicateUser
* 작성자 : PC03
* 변경이력 :
* @throws Exception
* Method 설명 : 중복체크  테스트
*/
@Test
public void testUserForm_post_fail_duplicateUser() throws Exception {
	/***Given***/
	File profileFile = new File("D:\\picture\\sally.png");
	FileInputStream fis = new FileInputStream(profileFile);
	MockMultipartFile file = new MockMultipartFile("profile", "brown.png", "image/png",fis);
	MvcResult mvcResult =mockMvc.perform(fileUpload("/user/userForm").file(file).param("userId", "sally").param("userNm","sally테스트").param("zipcode", "11111").param("addr1","대전시 중구 대흥로").param("addr2", "12번지").param("alias","병아리").param("pass","testpass")).andExpect(view().name("/user/userForm"))
			.andReturn();
	//vo대신해서 패럼으로 값을 가져옴
	/***When***/
	HttpSession httpSession = mvcResult.getRequest().getSession();
	
	
	ModelAndView mav =mvcResult .getModelAndView();
	String msg =(String) mav.getModel().get("msg");
	/***Then***/
	assertEquals("중복체크에 실패했습니다.",msg);
}
/**
* Method : testUserForm_Post_fail_insertError
* 작성자 : PC03
* 변경이력 :
* @throws Exception
* Method 설명 : 사용자 등록 (zipcode 사이즈 에러 sql에러 )테스트
*/
@Test
public void testUserForm_Post_fail_insertError() throws Exception {
	/***Given***/
	File profileFile = new File("D:\\picture\\sally.png");
	FileInputStream fis = new FileInputStream(profileFile);
	MockMultipartFile file = new MockMultipartFile("profile", "brown.png", "image/png",fis);
	MvcResult mvcResult =mockMvc.perform(fileUpload("/user/userForm")
			.file(file).param("userId", "sallyTest").
			param("userNm","sallyTest").param("zipcode", "1111111111").
			param("addr1","대전시 중구 대흥로").param("addr2", "12번지").
			param("alias","병아리").param("pass","testpass"))
			.andExpect(view().name("/user/userForm"))
			.andReturn();
	//vo대신해서 패럼으로 값을 가져옴
	/***When***/
	/***Then***/
}
@Test
public void testUserForm_Post_update() throws Exception {
	/***Given***/
	File profileFile = new File("D:\\picture\\sally.png");
	FileInputStream fis = new FileInputStream(profileFile);
	MockMultipartFile file = new MockMultipartFile("profile", "brown.png", "image/png",fis);
	MvcResult mvcResult =mockMvc.perform(fileUpload("/user/userModifyForm").file(file).param("userId", "moon2").param("userNm","sallyTest").param("zipcode", "11111").param("addr1","대전시 중구 대흥로").param("addr2", "12번지").param("alias","병아리").param("pass","testpass")).andExpect(view().name("/user/userModifyForm"))
			.andReturn();
	//vo대신해서 패럼으로 값을 가져옴
	/***When***/
	HttpSession httpSession = mvcResult.getRequest().getSession();
	
	

	/***Then***/
	assertEquals("정상등록 되었습니다.", httpSession.getAttribute("msg"));
}

}
