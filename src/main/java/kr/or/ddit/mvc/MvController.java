package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.exception.NoFileException;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.validator.UserVoValidator;

@Controller
public class MvController {
	private Logger logger = LoggerFactory.getLogger(MvController.class);
	
	@RequestMapping("/mvc/view")
	public String view(){
		return "/mvc/view";
	}
	
	/**
	* Method : fileupload
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 : fileupload 처리 요청 테스트
	*/
	//파라미터 : userId(text),profile(file)
	@RequestMapping("/mvc/fileupload")
	public String fileupload(@RequestParam("userId") String userId,@RequestPart("profile")MultipartFile MultipartFile  ){
		logger.debug("userId " +userId);
		logger.debug("MultipartFile.getOriginalFilename" +MultipartFile.getOriginalFilename());
		logger.debug("MultipartFile.getName()" +MultipartFile.getName());
		logger.debug("MultipartFile.getSize()" +MultipartFile.getSize());
		String filename = MultipartFile.getOriginalFilename()+"_"+UUID.randomUUID().toString();
		File profile = new File("d:\\picture\\"+filename);
		try {
			MultipartFile.transferTo(profile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/mvc/view";
	}
	@RequestMapping("/textView")
	public String textView(){
		return "/mvc/textView";
		
		
	}
	//@@RequestParam 어노테이션을 적용하지 않아도
	//인스턴스명이랑 동일하면 바인딩
	//파라미터명 이랑 인스턴스명이랑 다를 경우 -->@@RequestParam
	
	//BindingResult 객체는 command객체(vo)에 바인딩 과정에서 발생한 결과를 담는
	//객체로 반드시 command객체 메소드 인자 뒤에 위치해야한다.
	//UserVo userVo,BindingResult result ,Model model :O
	//UserVo userVo,Model model,BindingResult result  :x 
	@RequestMapping("/textReq")
	public String textReq(UserVo userVo,BindingResult result ,Model model){
		//UserVoValidator 생성해줘야 실행함
		new UserVoValidator().validate(userVo, result);
		if(result.hasErrors()){
			logger.debug("has error");
			return "/mvc/textView";
		}
		logger.debug("userId"+userVo.getUserId());
		logger.debug("pass"+userVo.getPass());
		
		//pass : 8자리 이상
//		if(pass.length() <8){
//			model.addAttribute("passwordLengthMsg","비밀번호 8 자리 이상이여야합니다.");
//			
//		}
		return "/mvc/textView";
	}
	@RequestMapping("/textReqJsr303")
	public String textReqJsr303(@Valid UserVo userVo,BindingResult result){
		logger.debug("result.hasErrors :"+result.hasErrors());
		return "/mvc/textView";
	}
	@RequestMapping("/textReqValJsr303")
	public String textReqValJsr303(@Valid UserVo userVo,BindingResult result){
		logger.debug("textReqValJsr:"+result.hasErrors());
		return "/mvc/textView";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UserVoValidator());
	}
	/**
	* Method : throwArithmeticException
	* 작성자 : PC03
	* 변경이력 :
	* @return
	* Method 설명 :Arithmetic 예외 강제 발생
	*/
	@RequestMapping("/throwArith")
	public String throwArithmeticException(){
		
		if(1==1)
		throw new ArithmeticException();
		return "/mvc/textView";
	}
	
	@RequestMapping("/throwNoFileException")
	public String throwNoFileException() throws NoFileException{
		Resource resource =new ClassPathResource("kr/or/ddit/config/sptring/no-ex.xml");
		
		try {
			resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoFileException();
		}
		return "/mvc/textView";
	}

}
