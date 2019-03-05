package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.pageVo;

@RequestMapping("/user")
@Controller
public class UserController {
	@Resource(name = "userService")
	private IUserService IUserService;

	/**
	 * Method : userAllList 작성자 : PC03 변경이력 :
	 * 
	 * @param model
	 * @return Method 설명 : 유저 전체리스트
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model) {
		List<UserVo> userAllList = IUserService.usergetAll();
		model.addAttribute("userAllList", userAllList);

		return "/user/userAllList";
	}

	/**
	 * Method : userPagingList 작성자 : PC03 변경이력 :
	 * 
	 * @param pageVo
	 * @param model
	 * @return Method 설명 : 사용자 페이징 리스트
	 */
	@RequestMapping("/userPagingList")
	// public String userPagingList(@RequestParam(name="page",
	// defaultValue="1")int page,@RequestParam(name="pageSize",
	// defaultValue="10")int pageSize ,Model model){
	public String userPagingList(pageVo pageVo, Model model) {

		Map<String, Object> resultMap = IUserService.selectUserPagingList(pageVo);

		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());

		return "/user/userPagingList";
	}

	/**
	 * Method : user 작성자 : PC03 변경이력 :
	 * 
	 * @param userId
	 * @param model
	 * @return Method 설명 :USER 설명
	 */
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public String user(@RequestParam("userId") String userId, Model model) {
		UserVo userVo = IUserService.selectUser(userId);
		model.addAttribute("userVo", userVo);

		return "/user/user";
	}

	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse resp, HttpServletRequest req, @RequestParam("userId") String userId)
			throws IOException {
		// 1.사용자 아이디 파라미터 확인
		// 2.해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVo userVo = IUserService.selectUser(userId);
		// 3-1.realFilename이 존재할 경우
		// 3-1-1. 해당 경로의 파일을 FileInputStream 으로 읽는다
		FileInputStream fis;
		if (userVo != null && userVo.getRealFilename() != null)
			fis = new FileInputStream(new File(userVo.getRealFilename()));

		// 3-2-1./upload/noimg.png(application.getRealPath())
		else {
			ServletContext application = req.getServletContext();
			String noimgpath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgpath));
		}
		// 4.FileInputStream을 response객체와 outputStream 객체에 write
		ServletOutputStream sos = resp.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while ((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}
		sos.close();
		fis.close();

	}

	/**
	 * Method : userForm 작성자 : PC03 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 등록 폼 요청
	 */
	@RequestMapping(path = "/userForm", method = RequestMethod.GET)
	public String userForm() {
		return "/user/userForm";
	}

	/**
	* Method : userForm
	* 작성자 : PC03
	* 변경이력 :
	* @param userVo
	* @param profile
	* @param session
	* @param model
	* @return
	* @throws IllegalStateException
	* @throws IOException
	* Method 설명 : 유저 등록 부분
	*/
	@RequestMapping(path = "/userForm", method =RequestMethod.POST)
	public String userForm(UserVo userVo, @RequestPart("profile") MultipartFile profile, HttpSession session,
			Model model) throws IllegalStateException, IOException {
		// 파라매터로 uservo 바인딩됨
		// 1.사용자 아이디 중복체크
		UserVo duplicateUserVo = IUserService.selectUser(userVo.getUserId());

		// 2-1. 중복체크 통과 : 사용자 정보를 db에 입력
		if (duplicateUserVo == null) {
			// Part profilePart =req.getPart("profile");
			// @RequestPart("profile")MultipartFile profile로 대체
			String realFilename ="";
			String filename ="";
			if (profile.getSize() > 0) {

				// String contentDisposition
				// =profilePart.getHeader("Content-Disposition");
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				// 사용한 uuid--realFilename)
				// 디스크에 기록 (d:\picture\ + realFilename)
				filename = profile.getOriginalFilename();
				profile.transferTo(new File(realFilename));

			}
			userVo.setFilename(filename);
			userVo.setRealFilename(realFilename);
			
			//사용자 비밀번호  암호화
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt =0;
			try{
				insertCnt = IUserService.insertUser(userVo);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			if (insertCnt == 1) {
				session.setAttribute("msg", "정상등록 되었습니다.");
				return "redirect:/user/userPagingList"; // contextpath작업 필요

			} else {
				return "/user/userForm";
			}
		}
		// 중복 체크 실패
		else {
			model.addAttribute("msg", "중복체크에 실패했습니다.");
			return "/user/userForm";

		}

	}
	@RequestMapping(path ="/userModifyForm", method =RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId") String userId,Model model) {
		UserVo  selectUser = IUserService.selectUser(userId);
		model.addAttribute("userVo",selectUser);
		return "/user/userModifyForm";
	}
	@RequestMapping(path ="/userModifyForm", method =RequestMethod.POST)
	public String userUpdate(UserVo userVo, @RequestPart("profile") MultipartFile profile,Model model,HttpSession session,RedirectAttributes ra,HttpServletRequest req) throws IllegalStateException, IOException {
		String filename="";
		String realFilename="";
		if(profile.getSize() >0){
			realFilename ="D:\\picture\\"+ UUID.randomUUID().toString();
			filename =profile.getOriginalFilename();
			profile.transferTo(new File(realFilename));
			userVo.setFilename(filename);
			userVo.setRealFilename(realFilename);
		}else {
			userVo.setFilename(userVo.getFilename());
			userVo.setRealFilename(userVo.getRealFilename());
		}
		
		int updateCnt = 0;
		//비밀번호 수정 요청
		//사용자가 값을 입력하지 않은경우 => 기존비밀번호유지
		if(userVo.getPass().equals("")){
			UserVo userVoForPass=IUserService.selectUser(userVo.getUserId());
					userVo.setPass(userVoForPass.getPass());
			
			//사용자가 비밀번호를 신규 등록한 경유
		}else{
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			
		}
		
		  updateCnt= IUserService.updateUser(userVo);
		if(updateCnt==1){
//			session.setAttribute("msg", "정상등록 되었습니다.");
			//1.url로 작성
			//"redirect:/user/user?userId="+userVo.getUserId();
			//2.model객체 속성 : 저장된 속성을 파라미터로 변환
			//model.addAttribute("userId",userVo.getUserId());
			//return "redirect:/user/user"; 1번과 2번 동일
			//3.RedirectAttribute("userId",userVo.getUserId());
			//return "redirect:/user/user";
//			return "redirect:/user/userModifyForm?userId="+userVo.getUserId();
			ra.addAttribute("userId",userVo.getUserId());
			ra.addFlashAttribute("msg","정상 등록 되었습니다.");
//			model.addAttribute("userId",userVo.getUserId());
			return "redirect:/user/user";
		}else
		return "/user/userModifyForm";
	}


}
