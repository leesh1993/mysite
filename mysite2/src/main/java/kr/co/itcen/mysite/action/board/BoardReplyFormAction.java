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

public class BoardReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("write", "reply");
	
		request.setAttribute("g_no",request.getParameter("g_no"));
		request.setAttribute("o_no",request.getParameter("o_no"));
		request.setAttribute("depth",request.getParameter("depth"));
		
		WebUtils.forward(request, response, "WEB-INF/views/board/writeform.jsp");
	}

}
