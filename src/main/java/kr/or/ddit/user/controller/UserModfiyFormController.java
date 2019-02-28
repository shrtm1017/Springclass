package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.model.PartUtil;
@WebServlet("/userModifyForm")
@MultipartConfig(maxFileSize=5*1024*1024, maxRequestSize = 5*5*1024*1024)
public class UserModfiyFormController extends HttpServlet{
	UserServiceImpl userCheck = new UserServiceImpl();

	/**
	* Method : doGet
	* 작성자 : PC03
	* 변경이력 :
	* @param req
	* @param resp
	* @throws ServletException
	* @throws IOException
	* Method 설명 :사용자 등록 폼
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		UserVo userVo = userCheck.selectUser(userId);
		req.setAttribute("userVo", userVo);
		
		req.getRequestDispatcher("/user/userModifyForm.jsp").forward(req, resp);
	}


	/**
	* Method : doPost
	* 작성자 : PC03
	* 변경이력 :
	* @param req
	* @param resp
	* @throws ServletException
	* @throws IOException
	* Method 설명 : 사용자 정보를 등록한다.
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		
		
		String filename="";
		String realFilename="";
		req.setCharacterEncoding("utf-8");
		String userId = req.getParameter("userId");
		req.setAttribute("userId",userId);
		String userNm = req.getParameter("userNm");
		String alias= req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		String pass= req.getParameter("pass");
		UserVo vo = new UserVo(userId,userNm,alias,addr1,addr2,zipcode,pass);
		Part profile = req.getPart("profile");
		if(profile.getSize()>0){
			String contentDisposition = profile.getHeader("Content-Disposition");
			filename = PartUtil.getFileNameFromPart(contentDisposition);
			realFilename = "d:\\picture" + UUID.randomUUID().toString();
			profile.write(realFilename);
			vo.setFilename(filename);
			vo.setRealFilename(realFilename);
		}else if(profile.getSize()==0){
			String userId2 = req.getParameter("userId");
			UserVo userVo = userCheck.selectUser(userId2);
			vo.setFilename(userVo.getFilename());
			vo.setRealFilename(userVo.getRealFilename());
			
		}

		int updateCnt = userCheck.updateUser(vo);
		if(updateCnt== 1){
			resp.sendRedirect(req.getContextPath()+"/user?"+"userId="+userId);
//			req.getRequestDispatcher("/userPagingList").forward(req, resp);
		}
		//정상입력(실페)
		else{
			doGet(req, resp);
		}
	}
		
}

