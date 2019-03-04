package kr.or.ddit.user;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.pageVo;
@RequestMapping("/user")
@Controller
public class UserController {
	@Resource(name="userService")
	private IUserService IUserService;
	
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		List<UserVo> userAllList =IUserService.usergetAll();
		model.addAttribute("userAllList",userAllList);
		
		return "/user/userAllList";
	}
	@RequestMapping("/userPagingList")
//	public String userPagingList(@RequestParam(name="page", defaultValue="1")int page,@RequestParam(name="pageSize", defaultValue="10")int pageSize ,Model model){
		public String userPagingList(pageVo pageVo,Model model){
	
		

				Map<String,Object> resultMap = IUserService.selectUserPagingList(pageVo);
				
				model.addAllAttributes(resultMap);
				model.addAttribute("pageSize",pageVo.getPageSize());
				model.addAttribute("page",pageVo.getPage());
				
				return "/user/userPagingList";
	}

}
