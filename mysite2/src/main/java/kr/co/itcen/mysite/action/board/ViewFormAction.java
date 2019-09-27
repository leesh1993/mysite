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

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			request.setAttribute("loginO", "loginO");
			request.setAttribute("authUserNo", authUser.getNo());
		}
			
		

		String boardUserNo = request.getParameter("boardUserNo");
		request.setAttribute("boardUserNo", boardUserNo);
		
		//해당 타이틀 번호의 데이터 가져오기
		String titleNo = request.getParameter("titleNo");
		BoardVo boardVo = new BoardDao().getBoard(Long.parseLong(titleNo));
		
		//조회수 증가
		BoardVo hitCount = new BoardDao().getBoardHit(Long.parseLong(titleNo));
		new BoardDao().updateHit(hitCount.getHit() + 1 ,Long.parseLong(titleNo));
		
		request.setAttribute("boardVo", boardVo);

		
		WebUtils.forward(request, response, "WEB-INF/views/board/viewform.jsp");
		
	}

}
