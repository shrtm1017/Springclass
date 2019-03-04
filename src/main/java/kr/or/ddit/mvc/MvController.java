package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

}
