package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class BoardReplyAction implements Action {

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
	
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
			
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(authUser.getNo());
		vo.setG_no(Long.parseLong(request.getParameter("g_no")));
		vo.setO_no(Long.parseLong(request.getParameter("o_no")));
		vo.setDepth(Long.parseLong(request.getParameter("depth")));
		
		new BoardDao().boardReply(vo);
		

		WebUtils.redirect(request, response, request.getContextPath() + "/board");

	}

}
