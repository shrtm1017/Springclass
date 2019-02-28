package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.model.pageVo;

/**
 * Servlet implementation class userPageList
 */
@WebServlet("/userPagingList")
public class UserPageList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    private IUserService userService;   
    public UserPageList() {
    }

	public void init(ServletConfig config) throws ServletException {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//page, pageSize에 해당하는 파라미터 받기 ==> pageVo
		//단 파라미터가 없을 경우 page : 1, pageSize : 10;
		String pageStr = request.getParameter("page");
		String pagSizeeStr = request.getParameter("pageSize");
		int page =pageStr== null? 1 : Integer.parseInt(request.getParameter("page"));
		int pageSize =pagSizeeStr == null? 10 : Integer.parseInt(request.getParameter("pageSize"));
		
		pageVo pageVo = new pageVo(page,pageSize);
		//userService 객체를 이용 userPageingList 조회
		 Map<String,Object> resultMap = userService.selectUserPagingList(pageVo);
		 List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
		 int userCnt = (Integer)resultMap.get("getUserCnt");
		//request 객체에 조호된 결과를 속성으로 설정
		 request.setAttribute("userpageList", userList);
		request.setAttribute("userCnt",userCnt );
		request.setAttribute("pageSize",pageSize);
		request.setAttribute("page",page);
		//userPagingList를 화면으로 출력할 jsp로 위임(forward)
		request.getRequestDispatcher("/user/userPagingList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
