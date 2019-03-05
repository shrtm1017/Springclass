package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class lprodControllerTest extends WebTestConfig{
	@Resource(name="lprodService")
	private ILprodService lprodService;


	@Test
	public void testlprodAllList() throws Exception {
		MvcResult mvcResult =mockMvc.perform(get("/lprod/lprodAllList")).andReturn();
		ModelAndView mav =mvcResult.getModelAndView();
		List<LprodVo> lprodList=(List<LprodVo>) mav.getModel().get("LprodAllList");
		
		assertEquals(19, lprodList.size());
		assertNotNull(lprodList);
	}
	@Test
	public void testlprodpageList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =mockMvc.perform(get("/lprod/lprodpageList")).andReturn();
		ModelAndView mav =mvcResult.getModelAndView();
		String viewName =mav.getViewName();
		List<LprodVo> userPagingList= (List<LprodVo>) mav.getModel().get("lprodList");//내가 지은 객체명
		int lprodCnt = (int) mav.getModelMap().get("lprodCnt");
		int page =(int) mav.getModel().get("page");
		int pageSize=(int) mav.getModelMap().get("pageSize");
		/***Then***/
		logger.debug("lprodList :"+userPagingList.size());
		assertEquals("/lprod/lprodpageList", viewName);
		assertEquals(1,page);
		assertEquals(10, pageSize);
		assertTrue(lprodCnt<100);
		
		assertNotNull(userPagingList);
//		assertTrue(userAllList.size()>100);//if문과 사용법이 동일하다.
	}

}
