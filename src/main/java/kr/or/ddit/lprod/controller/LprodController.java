package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.util.model.pageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	@Resource(name="lprodService")
	private ILprodService lprodService;
	@RequestMapping("/lprodAllList")
	public String LprodAllList(Model model){
		List<LprodVo> LprodAllList = lprodService.getAllLprod();
		model.addAttribute("LprodAllList", LprodAllList);
		return "/lprod/lprodAllList";
	}
	@RequestMapping("/lprodpageList")
	public String lprodpageList(pageVo pageVo ,Model model){
		Map<String,Object>lprodPageingList = lprodService.lpordpageList(pageVo);
		model.addAllAttributes(lprodPageingList);
		model.addAttribute("page",pageVo.getPage());
		model.addAttribute("pageSize",pageVo.getPageSize());
		
		return "/lprod/lprodpageList";
	}

}
