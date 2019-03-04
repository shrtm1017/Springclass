package kr.or.ddit.user;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.pageVo;
@RequestMapping("/user")
@Controller
public class UserController {
	@Resource(name="userService")
	private IUserService IUserService;
	
	/**
	* Method : userAllList
	* 작성자 : PC03
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 유저 전체리스트
	*/
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		List<UserVo> userAllList =IUserService.usergetAll();
		model.addAttribute("userAllList",userAllList);
		
		return "/user/userAllList";
	}
	/**
	* Method : userPagingList
	* 작성자 : PC03
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트
	*/
	@RequestMapping("/userPagingList")
//	public String userPagingList(@RequestParam(name="page", defaultValue="1")int page,@RequestParam(name="pageSize", defaultValue="10")int pageSize ,Model model){
		public String userPagingList(pageVo pageVo,Model model){
	
		

				Map<String,Object> resultMap = IUserService.selectUserPagingList(pageVo);
				
				model.addAllAttributes(resultMap);
				model.addAttribute("pageSize",pageVo.getPageSize());
				model.addAttribute("page",pageVo.getPage());
				
				return "/user/userPagingList";
	}
	
	
	/**
	* Method : user
	* 작성자 : PC03
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 :USER 설명
	*/
	@RequestMapping(path="/user",method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId,Model model){
		UserVo userVo=IUserService.selectUser(userId);
		model.addAttribute("userVo",userVo);
		
		
		return "/user/user";
	}
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse resp,HttpServletRequest req,@RequestParam("userId")String userId) throws IOException{
		//1.사용자 아이디 파라미터 확인
		//2.해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVo userVo = IUserService.selectUser(userId);
		//3-1.realFilename이 존재할 경우
		//3-1-1. 해당 경로의 파일을 FileInputStream 으로 읽는다
		FileInputStream fis;
		if(userVo !=null&&userVo.getRealFilename() !=null)
		fis =new FileInputStream(new File(userVo.getRealFilename()));
		
		//3-2-1./upload/noimg.png(application.getRealPath())
		else{
			ServletContext application = req.getServletContext();
			String noimgpath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgpath));
		}
		//4.FileInputStream을 response객체와 outputStream 객체에 write
		ServletOutputStream sos =resp.getOutputStream();
		byte[] buff = new byte[512];
		int len =0;
		while((len =fis.read(buff)) > -1){
			sos.write(buff);
		}
		sos.close();
		fis.close();
		
	}

}
