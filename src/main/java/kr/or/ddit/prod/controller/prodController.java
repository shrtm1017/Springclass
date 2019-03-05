package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class prodController {
	@Resource(name = "prodService")
	private IProdService prodService;

	@RequestMapping("/prodAllList")
	public String prodList(Model model) {
		List<ProdVo> prodList = prodService.ProdgetAll();
	model.addAttribute("prodList",prodList);
		return "/prod/prod";
	}
	@RequestMapping("/selectProd")
	public String selectProd(@RequestParam("lprod_gu")String lprod_gu,Model model){
		List<ProdVo> selectprod =prodService.selectProd(lprod_gu);
		model.addAttribute("selectProd", selectprod);
		return "/prod/prod";
	}

}
