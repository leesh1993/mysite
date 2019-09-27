package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String modifiedTitle = request.getParameter("modifiedTitle");
		String modifiedContents = request.getParameter("modifiedContents");
		String modifiedTitleNo = request.getParameter("modifiedTitleNo");
	
		new BoardDao().modify(Long.parseLong(modifiedTitleNo),modifiedTitle, modifiedContents);

		WebUtils.forward(request, response, "/board?a=default");
	}

}
