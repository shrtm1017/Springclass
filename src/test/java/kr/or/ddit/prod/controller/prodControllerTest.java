package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class prodControllerTest extends WebTestConfig{
	@Resource(name="prodService")
	private IProdService prodService;


	@Test
	public void testprodAllList() throws Exception {
		MvcResult mvcResult =mockMvc.perform(get("/prod/selectProd").param("lprod_gu","P101")).andReturn();
		ModelAndView mav =mvcResult.getModelAndView();
		List<ProdVo> prodList=(List<ProdVo>) mav.getModel().get("selectProd");
		
		assertEquals(19, prodList.size());
		assertNotNull(prodList);
	}
	
}
