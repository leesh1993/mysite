package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			request.setAttribute("user", "logO");
			request.setAttribute("userNo", authUser.getNo());
		}
		
		
		//페이징 처리===============================================================
		int cCount = 0;
		if(request.getParameter("cCount") == null || cCount < 0) {
			cCount = 1;
		} else {
			cCount = Integer.parseInt(request.getParameter("cCount"));
		}

		Paging p = new Paging();
		p.makeLastPageNum();
		p.makeBlock(cCount);
		request.setAttribute("p", p);
		request.setAttribute("cCount", cCount);

		// =====================================================================
			
		List<BoardVo> list = new BoardDao().getList(cCount);
		request.setAttribute("list", list);	
		request.setAttribute("what", "list");
		
		WebUtils.forward(request, response, "WEB-INF/views/board/listform.jsp");
	}

}
