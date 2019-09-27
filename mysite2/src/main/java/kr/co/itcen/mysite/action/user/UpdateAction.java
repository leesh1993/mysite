package kr.co.itcen.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.UserDao;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();

		if(session == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
				
		if("".equals(password)) {
			new UserDao().update(authUser.getNo(), name, gender);
			
			UserVo userVo = new UserDao().get(authUser.getNo());		
			request.setAttribute("userVo", userVo);
			authUser.setName(name);

		} else {
			new UserDao().update(authUser.getNo(), name, password, gender);
			
			UserVo userVo = new UserDao().get(authUser.getNo());		
			request.setAttribute("userVo", userVo);	
			authUser.setName(name);
		}
		
	
		//WebUtils.forward(request, response, "/WEB-INF/views/user/updateform.jsp");
		WebUtils.redirect(request, response, request.getContextPath() + "/user?a=updateform");
	}

}
