package kr.or.ddit.ranger.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ioc.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;
@RequestMapping("/ranger")
@Controller
public class RangerController {
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	
	/**
	* Method : getRangers
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :전체 레인저스 리스틀 조회
	*/
	//localhost/ranger/getRangers 라고 요청시
	// 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		List<String> rangers = rangerService.getRangers();
		//기존 : requset.setAttribute("rangers",rangers);
		model.addAttribute("rangers",rangers);
		return "/ranger/rangerList";
	}
	//http://localhost/ranger/getRanger?listIndex=파라매터요청시
	//아래 메소드에서 요청을 처리
	//파라미터를 해당 필드에 바인딩 시켜준다.
	@RequestMapping("/getRanger")//jsp의 웹 서블릿과 비슷함 이 url로 호출이 될경우 값으 전달할 경우
	//userVo vo = new userVo();
	//vo.setUserId(requset.getparameter("파라미터 아이디"))
	//이를 자동으로 바인딩 해주고 값을 넣어 준다.
	public String getRanger(RangerVo rangerVo,Model model) {
		
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		model.addAttribute("ranger",ranger);
		return "/ranger/ranger";
	}

//	@RequestMapping("/getRanger")
//	public String getRanger(HttpServletRequest req,Model model) {
//		int listIndex = Integer.parseInt(req.getParameter("listIndex"));
//		String ranger = rangerService.getRanger(listIndex);
//		model.addAttribute("ranger",ranger);
//		return "/ranger/ranger";
//	}
}
