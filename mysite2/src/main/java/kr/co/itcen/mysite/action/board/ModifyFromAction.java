package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ModifyFromAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("contents", request.getParameter("contents"));
		request.setAttribute("titleNo", request.getParameter("titleNo"));		

		WebUtils.forward(request, response, "WEB-INF/views/board/modifyform.jsp");

	}

}
